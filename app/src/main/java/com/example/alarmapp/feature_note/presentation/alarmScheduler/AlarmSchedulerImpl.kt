package com.example.alarmapp.feature_note.presentation.alarmScheduler

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.components.pow
import java.time.LocalDate
import java.time.LocalTime

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java) as AlarmManager

    companion object {
        const val PUT_EXTRA_INTENT_KEY = "time extra key"
    }

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(alarm: Alarm) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(PUT_EXTRA_INTENT_KEY, alarm.time)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val hours = alarm.time.hours.let {
            if (!alarm.time.isMorning) {
                if (it == 12) it
                else it + 12
            }
            else {
                if (it == 12) 0
                else it
            }
        }
        val minutes = alarm.time.minutes

        val currentHours = LocalTime.now().hour
        val currentMinutes = LocalTime.now().minute
        val secondsInCurrentMinutes = LocalTime.now().second

        if (alarm.isActive && alarm.days != 0) {
            var triggerTime = 0
            var remainingDays = 0
            for (i in 1..8) {
                var dayNumber = LocalDate.now().plusDays(i.toLong()).dayOfWeek.ordinal
                if (((alarm.days / (10 pow dayNumber)) % 10) == dayNumber + 1) {
                    if (remainingDays == 0) {
                        if (currentHours < hours) {
                            val remainingHours = hours - currentHours
                            val remainingMinutes = minutes - currentMinutes
                            triggerTime = (remainingHours * 60) + remainingMinutes
                            triggerTime *= 60
                            triggerTime -= secondsInCurrentMinutes
                            break
                        } else if (currentHours == hours) {
                            if (currentMinutes < minutes) {
                                val remainingMinutes = minutes - currentMinutes
                                triggerTime = (remainingMinutes * 60) - secondsInCurrentMinutes
                                break
                            }
                        }
                    } else {
                        if (currentHours > hours) {
                            val remainingHours = currentHours - hours
                            val remainingMinutes = currentMinutes - minutes
                            triggerTime = ((remainingDays * 24) * 60) - ((remainingHours * 60) + remainingMinutes)
                            triggerTime *= 60
                            triggerTime -= secondsInCurrentMinutes
                            break
                        } else if (currentHours == hours) {
                            val remainingMinutes = minutes - currentMinutes
                            triggerTime = ((remainingDays * 24) * 60) + remainingMinutes
                            triggerTime *= 60
                            triggerTime -= secondsInCurrentMinutes
                            break
                        } else {
                            val remainingHours = hours - currentHours
                            val remainingMinutes = minutes - currentMinutes
                            triggerTime = ((remainingDays * 24) * 60) + (remainingHours * 60) + remainingMinutes
                            triggerTime *= 60
                            triggerTime -= secondsInCurrentMinutes
                            break
                        }
                    }
                }
                remainingDays++
            }
            /*Log.d("younes", "triggerTime -> ${fromSToTime(triggerTime)}" +
                    " /// hours -> $hours, currentHours -> $currentHours") */

            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + ( triggerTime * 1000),
                pendingIntent
            )
        } else {
            cancel(alarm)
        }
    }

    fun localTimeNowInHoursAndMinutes(): LocalTime {
        return LocalTime.of(LocalTime.now().hour, LocalTime.now().minute)
    }

    fun fromSToTime(seconds: Int):String {
        var _seconds = seconds
        val day = _seconds / (24*3600)
        _seconds %= (24 * 3600)
        val hours = _seconds / 3600
        _seconds %= (3600)
        val minutes = _seconds / 60
        _seconds %= 60
        val seconds = _seconds
        return "$day->$hours hours:$minutes minutes:$seconds seconds"
    }

    override fun cancel(alarm: Alarm) {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id.hashCode(),
            Intent(context, AlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}



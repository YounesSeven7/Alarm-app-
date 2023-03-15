package com.example.alarmapp.feature_note.data.data_base.converter

import android.util.Log
import androidx.room.TypeConverter
import com.example.alarmapp.feature_note.domain.model.Time



class TimeConverter {

    @TypeConverter
    fun toTime(timeInMinutes: Int): Time {
        //Log.d("younes", "$timeInMinutes")
        val time = if (timeInMinutes >= 0) {
            Time(
                hours = timeInMinutes / 60,
                minutes = timeInMinutes % 60,
                isMorning = true
            )
        }
        else {
            Time(
                hours = (timeInMinutes / 60) * -1,
                minutes = (timeInMinutes % 60) * -1,
                isMorning = false
            )
        }
        return time
    }

    @TypeConverter
    fun fromTime(time: Time): Int {
        var timeInMinutes = time.hours * 60 + time.minutes
        timeInMinutes = if (!time.isMorning) timeInMinutes * -1 else timeInMinutes
        return timeInMinutes
    }



}
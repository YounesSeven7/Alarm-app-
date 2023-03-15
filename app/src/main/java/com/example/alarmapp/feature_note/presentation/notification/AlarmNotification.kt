package com.example.alarmapp.feature_note.presentation.notification

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.app.NotificationCompat
import com.example.alarmapp.R
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.MainActivity
import com.example.alarmapp.feature_note.presentation.util.timeFormat
import dagger.hilt.android.HiltAndroidApp
import java.time.Clock
import java.time.LocalDate
import javax.inject.Inject

class AlarmNotification (
    private val context: Context
) {

    companion object {
        const val title = "Clock"
        const val alarmNotificationId = 100
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    fun getNotification(time: Time): Notification {
        val icon = R.drawable.baseline_dark_alarm_24

        val contentActionPendingIntent = PendingIntent.getActivity(
            context,
            1,
            Intent(context, MainActivity::class.java),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )


        return NotificationCompat.Builder(context, AlarmNotificationChannel.CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(timeFormat(time))
            .setContentIntent(contentActionPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setSound(null)
            .build()
    }
}
package com.example.alarmapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import com.example.alarmapp.feature_note.presentation.notification.AlarmNotificationChannel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlarmApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createAlarmNotificationChannel()
    }

    fun createAlarmNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AlarmNotificationChannel.CHANNEL_ID,
                AlarmNotificationChannel.NAME,
                AlarmNotificationChannel.IMPORTANCE,
            ).apply {
                description = AlarmNotificationChannel.DESCRIPTION

            }

            channel.setSound(null, null)

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
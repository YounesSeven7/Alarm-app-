package com.example.alarmapp.feature_note.presentation.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


object AlarmNotificationChannel {

    const val NAME = "alarm notifications channel"
    const val DESCRIPTION = "This channel"
    const val IMPORTANCE = NotificationManager.IMPORTANCE_HIGH
    const val CHANNEL_ID = "alarm notification id"




}
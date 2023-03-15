package com.example.alarmapp.feature_note.presentation.alarmScheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.notification.AlarmNotification
import com.example.alarmapp.feature_note.presentation.services.AlarmService
import com.example.alarmapp.feature_note.presentation.util.getSerializable


class AlarmReceiver: BroadcastReceiver() {

    companion object {
        const val PUT_EXTRA_INTENT_KEY = "time extra key"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val time = intent?.getSerializable(
            key = AlarmSchedulerImpl.PUT_EXTRA_INTENT_KEY,
            m_class = Time::class.java
        )
        ContextCompat.startForegroundService(
            context, Intent(context, AlarmService::class.java).apply {
                putExtra(
                    PUT_EXTRA_INTENT_KEY,
                    time
                )
            }
        )
    }

}
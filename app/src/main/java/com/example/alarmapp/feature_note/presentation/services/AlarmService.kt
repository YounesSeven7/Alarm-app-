package com.example.alarmapp.feature_note.presentation.services

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.alarmapp.R
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmReceiver
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmSchedulerImpl
import com.example.alarmapp.feature_note.presentation.notification.AlarmNotification
import com.example.alarmapp.feature_note.presentation.util.getSerializable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    @Inject
    lateinit var player: ExoPlayer
    @Inject
    lateinit var alarmNotification: AlarmNotification



    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    override fun onCreate() {
        super.onCreate()
        val uri = Uri.parse("android.resource://$packageName/${R.raw.clock_alarm}")


        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getSerializable(
            key = AlarmReceiver.PUT_EXTRA_INTENT_KEY,
            m_class = Time::class.java
        )

        val notification = alarmNotification.getNotification(
            time!!
        )
        startForeground(
            AlarmNotification.alarmNotificationId,
            notification
        )
        //player.play()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.pause()
    }

}
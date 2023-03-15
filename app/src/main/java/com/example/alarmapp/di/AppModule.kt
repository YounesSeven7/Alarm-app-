package com.example.alarmapp.di


import android.app.Application
import android.app.Notification
import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.example.alarmapp.feature_note.data.data_base.AlarmDatabase
import com.example.alarmapp.feature_note.data.repository.AlarmRepositoryImpl
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository
import com.example.alarmapp.feature_note.domain.use_case.AddAlarm
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
import com.example.alarmapp.feature_note.domain.use_case.DeleteAlarm
import com.example.alarmapp.feature_note.domain.use_case.GetAlarm
import com.example.alarmapp.feature_note.domain.use_case.GetAlarms
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmScheduler
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmSchedulerImpl
import com.example.alarmapp.feature_note.presentation.notification.AlarmNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAlarmDatabase(app: Application): AlarmDatabase {
        return Room.databaseBuilder(
            app,
            AlarmDatabase::class.java,
            AlarmDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlarm(db: AlarmDatabase): AlarmRepository {
        return AlarmRepositoryImpl(db.alarmDao)
    }

    @Provides
    @Singleton
    fun provideAlarmUseCases(repository: AlarmRepository): AlarmUseCases {
        return AlarmUseCases(
            addAlarm = AddAlarm(repository),
            getAlarms = GetAlarms(repository),
            getAlarm = GetAlarm(repository),
            deleteAlarm = DeleteAlarm(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAlarmSchedule(app: Application): AlarmScheduler {
        return AlarmSchedulerImpl(app)
    }

    @Singleton
    @Provides
    fun provideAudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @Singleton
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ) = ExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }


    @Provides
    @Singleton
    fun provideAlarmNotification(app: Application): AlarmNotification {
         return AlarmNotification(app)
    }

}
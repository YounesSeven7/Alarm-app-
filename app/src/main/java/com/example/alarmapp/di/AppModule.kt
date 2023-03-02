package com.example.alarmapp.di


import android.app.Application
import androidx.room.Room
import com.example.alarmapp.feature_note.data.data_base.AlarmDatabase
import com.example.alarmapp.feature_note.data.repository.AlarmRepositoryImpl
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository
import com.example.alarmapp.feature_note.domain.use_case.AddAlarm
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
import com.example.alarmapp.feature_note.domain.use_case.DeleteAlarm
import com.example.alarmapp.feature_note.domain.use_case.GetAlarm
import com.example.alarmapp.feature_note.domain.use_case.GetAlarms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

}
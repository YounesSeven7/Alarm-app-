package com.example.alarmapp.feature_note.data.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alarmapp.feature_note.data.data_base.AlarmDao
import com.example.alarmapp.feature_note.domain.model.Alarm

@Database(
    entities = [Alarm::class],
    version = 1
)
abstract class AlarmDatabase: RoomDatabase() {
    abstract val alarmDao: AlarmDao

    companion object {
        const val DATABASE_NAME = "ALARM_DB"
    }
}
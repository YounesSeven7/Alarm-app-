package com.example.alarmapp.feature_note.data.repository

import com.example.alarmapp.feature_note.data.data_base.AlarmDao
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow

class AlarmRepositoryImpl(private val dao: AlarmDao): AlarmRepository {
    override fun getAlarm(): Flow<List<Alarm>> {
        return dao.getAlarms()
    }

    override suspend fun getAlarm(id: Int): Alarm? {
        return dao.getAlarm(id)
    }

    override suspend fun insertAlarm(alarm: Alarm) {
        return dao.insertAlarm(alarm)
    }

    override suspend fun deleteAlarm(alarm: Alarm) {
        return dao.deleteAlarm(alarm)
    }
}
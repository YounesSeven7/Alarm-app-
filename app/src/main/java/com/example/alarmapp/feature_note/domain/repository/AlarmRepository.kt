package com.example.alarmapp.feature_note.domain.repository

import com.example.alarmapp.feature_note.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    fun getAlarm(): Flow<List<Alarm>>

    suspend fun insertAlarm(alarm: Alarm)

    suspend fun deleteAlarm(alarm: Alarm)

}

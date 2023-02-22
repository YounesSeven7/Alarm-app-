package com.example.alarmapp.feature_note.domain.use_case

import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow

class GetAlarms(private val repository: AlarmRepository) {
    operator fun invoke(): Flow<List<Alarm>> {
        return repository.getAlarm()
    }
}
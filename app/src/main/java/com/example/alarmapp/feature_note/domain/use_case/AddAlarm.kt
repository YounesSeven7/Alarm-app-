package com.example.alarmapp.feature_note.domain.use_case

import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository

class AddAlarm(private val repository: AlarmRepository) {
    suspend operator fun invoke(alarm: Alarm) {
        repository.insertAlarm(alarm)
    }
}
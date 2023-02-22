package com.example.alarmapp.feature_note.domain.use_case

import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.repository.AlarmRepository

class DeleteAlarm(private val repository: AlarmRepository) {
     suspend operator fun invoke(alarm: Alarm) {
        repository.deleteAlarm(alarm)
    }
}
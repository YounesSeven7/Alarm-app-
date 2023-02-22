package com.example.alarmapp.feature_note.presentation.alarm_list_screen

import com.example.alarmapp.feature_note.domain.model.Alarm

data class AlarmState(
    val alarms: List<Alarm> = emptyList(),
    // TODO -> add order type
)

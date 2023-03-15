package com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.event

import com.example.alarmapp.feature_note.domain.model.Alarm

data class AlarmState(
    val alarms: List<Alarm> = emptyList(),
    // TODO -> add order type
)

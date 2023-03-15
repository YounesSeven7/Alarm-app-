package com.example.alarmapp.feature_note.domain.use_case

data class AlarmUseCases(
    val addAlarm: AddAlarm,
    val getAlarms: GetAlarms,
    val getAlarm: GetAlarm,
    val deleteAlarm: DeleteAlarm
)

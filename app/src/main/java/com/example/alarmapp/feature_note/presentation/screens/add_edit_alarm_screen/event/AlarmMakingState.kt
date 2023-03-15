package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.event

import com.example.alarmapp.feature_note.domain.model.Time


data class AlarmMakingState(
    val alarmNameTextFieldState: AlarmNameTextFieldState,
    val time: Time,
    val alarmDays: Int
    //val settingItemState: SettingItemState
)

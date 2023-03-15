package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.event

import androidx.compose.ui.focus.FocusState
import com.example.alarmapp.feature_note.domain.model.Alarm

sealed class AddEditAlarmScreenEvent() {

    data class SaveAlarm(val alarm: Alarm): AddEditAlarmScreenEvent()
}

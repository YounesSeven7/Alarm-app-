package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.event

import androidx.compose.ui.focus.FocusState

sealed class AddEditAlarmScreenEvent() {
    data class EnterAlarmName(val value: String): AddEditAlarmScreenEvent()
    data class ChangeAlarmNameFocus(val focusState: FocusState): AddEditAlarmScreenEvent()
    object SaveAlarm: AddEditAlarmScreenEvent()
}

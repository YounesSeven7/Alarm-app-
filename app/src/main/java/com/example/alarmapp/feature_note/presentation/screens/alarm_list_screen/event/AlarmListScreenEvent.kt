package com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.event

import androidx.navigation.NavController
import com.example.alarmapp.feature_note.domain.model.Alarm

sealed class AlarmListScreenEvent {
    data class AddScreen(val navController: NavController): AlarmListScreenEvent()
    data class EditScreen(val navController: NavController, val alarmId: Int): AlarmListScreenEvent()
    data class ActivatingState(val alarm: Alarm): AlarmListScreenEvent()
    data class DeleteAlarms(val alarms: List<Alarm>): AlarmListScreenEvent()
    // TODO -> change sort type
    object SettingsScreen: AlarmListScreenEvent()
}

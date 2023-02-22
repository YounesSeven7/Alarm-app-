package com.example.alarmapp.feature_note.presentation.alarm_list_screen

import androidx.navigation.NavController
import com.example.alarmapp.feature_note.domain.model.Alarm

sealed class AlarmListScreenEvent {
    data class AddScreen(val navController: NavController): AlarmListScreenEvent()
    data class EditScreen(val navController: NavController, val alarm: Alarm): AlarmListScreenEvent()
    // ToDo -> change sort type
    object SettingsScreen: AlarmListScreenEvent()
}

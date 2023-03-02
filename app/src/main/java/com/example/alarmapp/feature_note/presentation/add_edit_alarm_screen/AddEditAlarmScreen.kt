package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences.AlarmPreferences
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.time_picker_components.TimePicker
import com.example.alarmapp.feature_note.presentation.alarm_list_screen.components.pow


@Composable
fun AddEditAlarmScreen(
    navController: NavController,
    // alarmID: Int,
    viewModel: AddEditAlarmViewModel = hiltViewModel()
) {
    val alarmDays = viewModel.alarmDays
    val alarmText = viewModel.alarmText
    val isHintVisible = viewModel.isHintVisible
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TimePicker()
        AlarmPreferences(
            alarmDays = alarmDays.value,
            onCheckDayIcon = { dayNumber, isChecked->
                if (isChecked) {
                    alarmDays.value += (10 pow (dayNumber - 1)) * dayNumber
                } else {
                    alarmDays.value -= (10 pow (dayNumber - 1)) * dayNumber
                }
            },
            alarmText = alarmText.value,
            isHintVisible = isHintVisible.value,
            onFocusChange = { isFocus ->
                isHintVisible.value = !isFocus
            },
            onValueChange = {
                alarmText.value = it
            }
        )
    }
}
package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences.AlarmPreferences
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.time_picker_components.TimePicker
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.event.AddEditAlarmScreenEvent
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.components.pow


@Composable
fun AddEditAlarmScreen(
    navController: NavController,
    // alarmID: Int,
    viewModel: AddEditAlarmViewModel = hiltViewModel()
) {
    val alarmDays = viewModel.alarmDays
    val alarmNameTextFieldState = viewModel.alarmNameTextFieldState
    val time = viewModel.timeState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TimePicker(
            hours = time.value.hours,
            hoursListener = {hours ->
                time.value = time.value.copy(hours = hours)
            },
            minutes = time.value.minutes,
            minutesListener = {minutes ->
                time.value = time.value.copy(minutes = minutes)
            },
            isMorning = time.value.isMorning,
            partOfTimeListener = {isMorning ->
                time.value = time.value.copy(isMorning = isMorning)
            }
        )
        AlarmPreferences(
            alarmDays = alarmDays.value,
            onCheckDayIcon = { dayNumber, isChecked->
                if (isChecked) {
                    alarmDays.value += (10 pow (dayNumber - 1)) * dayNumber
                } else {
                    alarmDays.value -= (10 pow (dayNumber - 1)) * dayNumber
                }
            },
            alarmText = alarmNameTextFieldState.value.text,
            isHintVisible = alarmNameTextFieldState.value.isHintVisible,
            onValueChange = {
               alarmNameTextFieldState.value = alarmNameTextFieldState.value.copy(
                   text = it,
                   isHintVisible = it.isEmpty()
               )
            }
        )
        Buttons(
            saveOnClick = {
                viewModel.onEvent(
                    AddEditAlarmScreenEvent.SaveAlarm(
                        Alarm(
                            name = alarmNameTextFieldState.value.text,
                            time = time.value,
                            days = alarmDays.value
                        )
                    )
                )
                navController.navigateUp()
            },
            cancelOnClick = {
                navController.navigateUp()
            }
        )
    }
}

@Composable
fun Buttons(
    saveOnClick: () -> Unit,
    cancelOnClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CancelButton(
            cancelOnClick = cancelOnClick
        )
        SaveButton(
            saveOnClick = saveOnClick
        )
    }
}

@Composable
fun SaveButton(saveOnClick: () -> Unit) {
    TextButton(onClick = saveOnClick) {
        Text(
            text = "Save",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CancelButton(cancelOnClick: () -> Unit) {
    TextButton(onClick = cancelOnClick) {
        Text(
            text = "Cancel",
            color = MaterialTheme.colorScheme.primary
        )
    }
}
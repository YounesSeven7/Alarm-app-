package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
import com.example.alarmapp.feature_note.presentation.MainActivity
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.event.AlarmNameTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.event.AddEditAlarmScreenEvent
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmScheduler
import com.example.alarmapp.feature_note.presentation.alarmScheduler.AlarmSchedulerImpl
import com.example.alarmapp.feature_note.presentation.notification.AlarmNotificationChannel
import javax.inject.Inject

@HiltViewModel
class AddEditAlarmViewModel @Inject constructor(
    private val alarmUseCases: AlarmUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    val alarmDays = mutableStateOf(0)
    val alarmNameTextFieldState = mutableStateOf(
        AlarmNameTextFieldState(
            text = "",
            hint = "Alarm name",
            isHintVisible = true
        )
    )
    val timeState = mutableStateOf(
        Time(
            hours = 1,
            minutes = 0,
            isMorning = true
        )
    )
    private var isActive = false
    private var currentAlarmId = -1

    init {
        getAlarmItem()
    }

    fun onEvent(event: AddEditAlarmScreenEvent) {
        when (event) {
            is AddEditAlarmScreenEvent.SaveAlarm -> {
                viewModelScope.launch {
                    alarmUseCases.addAlarm(
                        event.alarm.copy(
                            id = if (currentAlarmId == -1) null else currentAlarmId
                        )
                    )
                }
            }
        }
    }

    private fun getAlarmItem() {
        savedStateHandle.get<Int>(MainActivity.ALARM_ID_KEY)?.let { alarmId ->
            if (alarmId != -1) {
                currentAlarmId = alarmId
                viewModelScope.launch {
                    alarmUseCases.getAlarm(currentAlarmId)?.also { alarm ->
                        alarmDays.value = alarm.days
                        alarmNameTextFieldState.value = alarmNameTextFieldState.value.copy(
                            text = alarm.name,
                            isHintVisible = alarm.name.isEmpty()
                        )
                        timeState.value = timeState.value.copy(
                            hours = alarm.time.hours,
                            minutes = alarm.time.minutes,
                            isMorning = alarm.time.isMorning
                        )
                        this@AddEditAlarmViewModel.isActive = alarm.isActive
                    }
                }
            }
        }
    }


}
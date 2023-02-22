package com.example.alarmapp.feature_note.presentation.alarm_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
import com.example.alarmapp.feature_note.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmUseCase: AlarmUseCases
): ViewModel() {

    private val _state = mutableStateOf(AlarmState())
    val state: State<AlarmState> = _state

    private var alarmJob: Job? = null

    init {
        getAlarms()
    }

    fun onEvent(event: AlarmListScreenEvent) {
        when(event) {
            is AlarmListScreenEvent.AddScreen -> {
                event.navController.navigate(MainActivity.ALARM_ADD_EDIT_SCREEN)
            }
            is AlarmListScreenEvent.EditScreen -> {
                event.navController.navigate(
                    MainActivity.ALARM_ADD_EDIT_SCREEN +
                    "?${MainActivity.ALARM_ID_KEY}=${event.alarm.id}"
                )
            }
            // TODO -> change sorting type.
            is AlarmListScreenEvent.SettingsScreen -> {
                // TODO -> add settings screen.
            }
        }
    }

    private fun getAlarms() {
        alarmJob?.cancel()
        alarmJob = alarmUseCase.getAlarms()
            .onEach { alarms ->
                _state.value = state.value.copy(alarms = alarms)
            }
            .launchIn(viewModelScope)
    }
}

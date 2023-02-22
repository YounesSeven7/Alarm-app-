package com.example.alarmapp.feature_note.presentation.alarm_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
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

    private fun getAlarms() {
        alarmJob?.cancel()
        alarmJob = alarmUseCase.getAlarms()
            .onEach { alarms ->
                _state.value = state.value.copy(alarms = alarms)
            }
            .launchIn(viewModelScope)
    }
}

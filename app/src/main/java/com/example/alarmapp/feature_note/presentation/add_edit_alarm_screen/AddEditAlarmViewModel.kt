package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.use_case.AlarmUseCases
import com.example.alarmapp.feature_note.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditAlarmViewModel @Inject constructor(
    private val alarmUseCases: AlarmUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    val alarmDays = mutableStateOf(0)
    val alarmText = mutableStateOf("")
    val isHintVisible = mutableStateOf(true)

    init {
        getAlarmItem()?.let { alarm->
            setValues(alarm)
        }
    }

    private fun getAlarmItem(): Alarm? {
        savedStateHandle.get<Int>(MainActivity.ALARM_ID_KEY)?.let { alarmId ->
            if (alarmId != -1) {
                viewModelScope.launch {
                    alarmUseCases.getAlarm(alarmId)?.also {alarm ->

                    }
                }
            }
        }
        return null
    }

    private fun setValues(alarm: Alarm) {

    }
}
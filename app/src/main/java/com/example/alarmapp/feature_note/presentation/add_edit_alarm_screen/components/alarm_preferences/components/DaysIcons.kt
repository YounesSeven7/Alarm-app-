package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.alarmapp.feature_note.domain.model.Alarm
import kotlin.math.pow

@Composable
fun DaysIcons(
    //days: Int,
    onCheck: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()

    ) {
        for (dayNumber in 1..7) {
            DayIcon(
                dayNumber = dayNumber,
                onCheck = onCheck
            )
        }
    }
}

infix fun Int.pow(other: Int): Int {
    return this.toDouble().pow(other.toDouble()).toInt()
}

@Composable
fun DayIcon(
    dayNumber: Int,
    onCheck: (Int, Boolean) -> Unit
) {
    val isChecked = remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = {
            onCheck(dayNumber, !isChecked.value)
            isChecked.value = !isChecked.value
        }
    ) {
        Text(text = Alarm.daysFirstLetterList[dayNumber - 1].toString())
    }
}
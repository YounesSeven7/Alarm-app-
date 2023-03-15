package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.time_picker_components

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.feature_note.domain.model.Time
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.time_picker_components.library.ListItemPicker
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.time_picker_components.library.NumberPicker


@Composable
fun TimePicker(
    hours: Int,
    hoursListener: (Int) -> Unit,
    minutes: Int,
    minutesListener: (Int) -> Unit,
    isMorning: Boolean,
    partOfTimeListener: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        HoursPicker(
            modifier = Modifier.weight(1f),
            hours = hours,
            hoursListener = hoursListener
            )
        Text(
            text = ":",
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        MinutesPicker(
            modifier = Modifier.weight(1f),
            minutes = minutes,
            minutesListener = minutesListener
        )
        DayTimePart(
            modifier = Modifier.weight(1f),
            isMorning = isMorning,
            partOfTimeListener = partOfTimeListener)
    }
}

@Composable
fun HoursPicker(modifier: Modifier, hours: Int, hoursListener: (Int) -> Unit) {
    NumberPicker(
        value = hours,
        onValueChange = {
            hoursListener(it)
        },
        range = 1..12,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}

@Composable
fun MinutesPicker(modifier: Modifier, minutes: Int, minutesListener: (Int) -> Unit) {
    NumberPicker(
        value = minutes,
        onValueChange = {
            minutesListener(it)
        },
        range = 0..59,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}

@Composable
fun DayTimePart(modifier: Modifier, isMorning: Boolean, partOfTimeListener: (Boolean) -> Unit) {
    val timePartList = listOf(Time.AM, Time.PM)
    var timePart = if (isMorning) Time.AM else Time.PM
    ListItemPicker(
        value = timePart,
        onValueChange = {
            if (it == Time.AM) partOfTimeListener(true) else partOfTimeListener(false)
        },
        list = timePartList,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}


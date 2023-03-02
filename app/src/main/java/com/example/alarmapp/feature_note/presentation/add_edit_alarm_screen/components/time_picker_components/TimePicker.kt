package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.time_picker_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.time_picker_components.library.ListItemPicker
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.time_picker_components.library.NumberPicker


@Composable
fun TimePicker() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        HoursPicker(modifier = Modifier.weight(1f))
        Text(
            text = ":",
            fontSize = 36.sp
        )
        MinutesPicker(modifier = Modifier.weight(1f))
        DayTimePart(modifier = Modifier.weight(1f))
    }
}

@Composable
fun HoursPicker(modifier: Modifier) {
    var hours by remember { mutableStateOf(2) }
    NumberPicker(
        value = hours,
        onValueChange = { hours = it },
        range = 1..12,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp
        ),
        modifier = modifier
    )
}

@Composable
fun MinutesPicker(modifier: Modifier) {
    var minutes by remember { mutableStateOf(1) }
    NumberPicker(
        value = minutes,
        onValueChange = { minutes = it },
        range = 0..59,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp
        ),
        modifier = modifier
    )
}

@Composable
fun DayTimePart(modifier: Modifier) {
    val timePartList = listOf(Time.AM, Time.PM)
    var timePart by remember { mutableStateOf(Time.AM) }
    ListItemPicker(
        value = timePart,
        onValueChange = { timePart = it },
        list = timePartList,
        dividersColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 36.sp,

        ),
    )
}
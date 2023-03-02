package com.example.alarmapp.feature_note.presentation.alarm_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.model.Time
import kotlin.math.pow

@Composable
fun AlarmItem(
    alarm: Alarm,
    onSwitchClick: (Boolean) -> Unit,
    cardModifier: Modifier = Modifier
) {
    Card(
        modifier = cardModifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeText(
                time = alarm.time,
                isActive = alarm.isActive
            )
            LeftSection(
                days = alarm.days,
                isActive = alarm.isActive,
                onSwitchClick = onSwitchClick,
            )
        }
    }
}


@Composable
fun TimeText(
    time: Time = Time(12, 34, true),
    isActive: Boolean,
) {
    val color: Color = if (isActive) Color.Black else Color.Gray
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 32.sp)) {
                append("${time.hours}:${time.minutes} ")
            }
            if (time.isMorning)
                append("AM")
            else
                append("PM")
        },
        color = color,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun LeftSection(
    days: Int,
    isActive: Boolean,
    onSwitchClick: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        DaysText(days = days)
        Spacer(modifier = Modifier.padding(end = 12.dp))
        SwitchButton(isActive = isActive, onSwitchClick = onSwitchClick)
    }
}

@Composable
fun DaysText(days: Int) {
    if (days == 7654321) {
        Text(
            text = "Every day",
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        Row {
            for (i in 1..7) {
                Column {
                    var color = if ((days / (10 pow (i-1))) % 10 == i) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Gray
                    }
                    Text(
                        text = "${Alarm.daysFirstLetterList[i-1]}",
                        color = color,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 1.dp)
                    )
                }
            }
        }
    }
}

infix fun Int.pow(other: Int): Int {
    return this.toDouble().pow(other.toDouble()).toInt()
}

@Composable
fun SwitchButton(
    isActive: Boolean,
    onSwitchClick: (Boolean) -> Unit
) {
    Switch(
        checked = isActive,
        onCheckedChange = { onSwitchClick(!isActive) }
    )
}



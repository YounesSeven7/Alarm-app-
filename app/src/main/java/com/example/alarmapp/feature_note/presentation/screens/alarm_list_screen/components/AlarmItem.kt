package com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.domain.model.Time
import java.util.Locale
import kotlin.math.pow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlarmItem(
    alarm: Alarm,
    onSwitchClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LeftSection(
                    time = alarm.time,
                    isAlarmActive = alarm.isActive
                )
                RightSection(
                    days = alarm.days,
                    isActive = alarm.isActive,
                    onSwitchClick = onSwitchClick,
                )
            }
        }

    }
}

@Composable
fun LeftSection(
    time: Time,
    isAlarmActive: Boolean
) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        //SelectItemRadioButton()
        TimeText(
            time = time,
            isAlarmActive = isAlarmActive
        )

    }
}

@Composable
fun TimeText(
    time: Time,
    isAlarmActive: Boolean,
) {
    val color: Color = if (isAlarmActive) MaterialTheme.colorScheme.onSurface else Color.Gray
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 32.sp)) {
                append("${time.hours}:${String.format(Locale.US,"%02d", time.minutes)} ")
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
fun SelectItemRadioButton() {
    val isSelected = remember {
        mutableStateOf(false)
    }
    val scale by animateFloatAsState(
        targetValue = if (isSelected.value) 1f else 0f,
        label = "",
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 100
        )
    )
    val size by animateDpAsState(
        targetValue = if (isSelected.value) 24.dp else 0.dp,
        label = "",
        animationSpec = tween(
            durationMillis = 500
        )
    )
    androidx.compose.material3.RadioButton(
        selected = isSelected.value,
        onClick = { isSelected.value = !isSelected.value },
        modifier = Modifier
            .size(size)
            .scale(scale)
    )
    LaunchedEffect(key1 = null) {
        isSelected.value = true
    }
}

@Composable
fun RightSection(
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var color: Color?
                    var pointColor: Color?
                    if ((days / (10 pow (i-1))) % 10 == i) {
                        color = MaterialTheme.colorScheme.primary
                        pointColor = MaterialTheme.colorScheme.primary
                    } else {
                        color = Color.Gray
                        pointColor = Color.Transparent
                    }
                    Canvas(
                        modifier = Modifier.width(3.dp)
                    ) {
                        drawCircle(
                            color = pointColor,
                            radius = (1.5).dp.toPx()
                        )
                    }
                    Text(
                        text = "${Alarm.daysFirstLetterList[i-1]}",
                        color = color,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(
                            horizontal = 1.dp,
                            vertical = 1.dp
                        )
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
        onCheckedChange = {
            onSwitchClick(!isActive)
        }
    )
}
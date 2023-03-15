package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences.components

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.example.alarmapp.feature_note.domain.model.Alarm
import kotlin.math.pow

@Composable
fun DaysIcons(
    alarmDays: Int,
    onCheck: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()


    ) {
        for (i in 1..7) {
            DayIcon(
                day = (alarmDays /(10 pow (i-1))) % 10,
                dayNumber = i,
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
    day: Int,
    dayNumber: Int,
    onCheck: (Int, Boolean) -> Unit
) {
    val isChecked = day != 0
    val circleRadius by animateFloatAsState(
        targetValue = if (isChecked) 20.dp.dpToPx() else 0f,
        label = "",
        animationSpec = tween(durationMillis = 250)
    )
    val borderColor = MaterialTheme.colorScheme.primary
    IconButton(
        onClick = {
            onCheck(dayNumber, !isChecked)
        },
        modifier = Modifier
            .scale(0.7f)
            .drawBehind {
                drawCircle(
                    color = borderColor,
                    radius = circleRadius,
                    style = Stroke(2.dp.toPx())
                )
            }
    ) {
        Text(
            text = Alarm.daysFirstLetterList[dayNumber - 1].toString(),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
            )
    }
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }
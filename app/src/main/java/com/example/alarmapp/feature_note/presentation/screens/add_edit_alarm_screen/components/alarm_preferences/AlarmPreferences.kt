package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences.components.AlarmNameTextField
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences.components.DaysIcons
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.components.pow

@Composable
fun AlarmPreferences(
    alarmDays: Int,
    onCheckDayIcon: (Int, Boolean) -> Unit,
    alarmText: String = "",
    alarmHint: String = "Alarm name",
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AlarmDaysText(
                alarmDays = alarmDays,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            DaysIcons(
                alarmDays = alarmDays,
                onCheck = onCheckDayIcon,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            AlarmNameTextField(
                text = alarmText,
                hint = alarmHint,
                isHintVisible = isHintVisible,
                onValueChange = onValueChange,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AlarmDaysText(
    alarmDays: Int,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = getAlarmDaysText(alarmDays),
        transitionSpec = {
            fadeIn(animationSpec = tween(220, delayMillis = 90)) with
                    fadeOut(animationSpec = tween(90))
        }
    ) {alarmDaysText->
        Text(
            text = alarmDaysText,
            modifier = modifier
                .animateContentSize()
        )
    }
}


fun getAlarmDaysText(alarmDays: Int): String {
    var alarmDaysText = ""
    when (alarmDays) {
        0 -> {
            alarmDaysText = "No day"
        }
        Alarm.allDays -> {
            alarmDaysText = "Every day"
        }
        else -> {
            alarmDaysText += "Every "
            for (i in 1..7) {
                if (((alarmDays / (10 pow (i-1))) % 10) == i) {
                    alarmDaysText += Alarm.daysNamesList[i -1] + ", "
                }
            }
            // TODO I don't know the time complexity of sub string method, so maybe I am going to change it.
            alarmDaysText = alarmDaysText.substring(0, alarmDaysText.length - 2)
        }
    }
    return alarmDaysText
}
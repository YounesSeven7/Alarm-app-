package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences.components.AlarmNameTextField
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences.components.DaysIcons
import com.example.alarmapp.feature_note.presentation.alarm_list_screen.components.pow

@Composable
fun AlarmPreferences(
    alarmDays: Int,
    onCheckDayIcon: (Int, Boolean) -> Unit,
    alarmText: String = "",
    alarmHint: String = "Alarm name",
    isHintVisible: Boolean,
    onFocusChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AlarmDaysText(
                alarmDays = alarmDays,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            DaysIcons(
                //days = alarmDays,
                onCheck = onCheckDayIcon,
                modifier = Modifier.padding(top = 16.dp)
            )
            AlarmNameTextField(
                text = alarmText,
                hint = alarmHint,
                isHintVisible = isHintVisible,
                onFocusChange = onFocusChange,
                onValueChange = onValueChange,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
fun AlarmDaysText(
    alarmDays: Int,
    modifier: Modifier = Modifier
) {

    Text(
        text = getAlarmDaysText(alarmDays),
        modifier = modifier
        )
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
            // TODO I don't know the time complexity of sub string so maybe I am going to change it.
            alarmDaysText = alarmDaysText.substring(0, alarmDaysText.length - 2)
        }
    }
    return alarmDaysText
}






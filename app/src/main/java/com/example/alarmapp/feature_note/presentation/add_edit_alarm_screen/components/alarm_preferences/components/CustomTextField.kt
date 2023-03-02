package com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.components.alarm_preferences.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlarmNameTextField(
    text: String,
    hint: String,

    isHintVisible: Boolean = true,
    onFocusChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it.isFocused)
                    }
            )
            if (isHintVisible) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = Color.DarkGray
                )
            }
        }

        if (isHintVisible) {
            Divider(
                color = Color.Black,
                thickness = 1.dp
            )
        } else {
            Divider(
                color = Color.Black,
                thickness = 3.dp
            )
        }

    }
}

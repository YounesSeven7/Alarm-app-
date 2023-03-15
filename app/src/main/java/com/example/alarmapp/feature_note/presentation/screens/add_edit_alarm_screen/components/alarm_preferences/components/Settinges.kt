package com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.components.alarm_preferences.components

import android.widget.Switch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun Settings(

) {

}

@Composable
fun SettingItem(
    itemName: String,
    itemCurrentValue: String,
    itemIsChecked: Boolean,
    itemOnCheckChange: (Boolean) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween){
        Column {
            Text(text = itemName, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface)
            Text(text = itemCurrentValue, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
        }
       SwitchButton(
           isChecked = itemIsChecked,
           onCheckedChange = itemOnCheckChange
       )
    }
}

@Composable
fun SwitchButton(
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = isChecked,
        onCheckedChange = onCheckedChange
    )
}
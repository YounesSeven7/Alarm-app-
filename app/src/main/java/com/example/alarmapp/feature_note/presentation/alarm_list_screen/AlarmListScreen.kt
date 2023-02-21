package com.example.alarmapp.feature_note.presentation.alarm_list_screen

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.More
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alarmapp.feature_note.domain.model.Alarm
import com.example.alarmapp.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AlarmListScreen (
    navController: NavController
){
    Scaffold(
        topBar = { TopAppBar() },

    ) {
        

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = { Text(
            text = "Alarm",
            color = MaterialTheme.colorScheme.primary,
            style = Typography.titleLarge
            ) },
        actions = {
            AddIcon()
            MoreIcon()
        }
    )
}

@Composable
fun AddIcon() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

@Composable
fun MoreIcon() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.More, contentDescription = null)
    }
}
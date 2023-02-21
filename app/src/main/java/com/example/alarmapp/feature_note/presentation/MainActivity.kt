package com.example.alarmapp.feature_note.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.alarmapp.feature_note.presentation.alarm_list_screen.AlarmListScreen
import com.example.alarmapp.ui.theme.AlarmAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    companion object {
        const val ALARM_LIST_SCREEN = "ALARM_LIST_SCREEN"
        const val ALARM_ADD_EDIT_SCREEN = "ALARM_ADD_EDIT_SCREEN"
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ALARM_LIST_SCREEN) {
                    composable(route = ALARM_LIST_SCREEN) {
                        AlarmListScreen(navController = navController)
                    }
                    composable(route = ALARM_ADD_EDIT_SCREEN) {

                    }
                }
            }

        }
    }
}

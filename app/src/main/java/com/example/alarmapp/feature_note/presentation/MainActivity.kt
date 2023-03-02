package com.example.alarmapp.feature_note.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.alarmapp.feature_note.presentation.add_edit_alarm_screen.AddEditAlarmScreen
import com.example.alarmapp.feature_note.presentation.alarm_list_screen.AlarmListScreen
import com.example.alarmapp.ui.theme.AlarmAppTheme
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val ALARM_LIST_SCREEN = "ALARM_LIST_SCREEN"
        const val ALARM_ADD_EDIT_SCREEN = "ALARM_ADD_EDIT_SCREEN"
        const val ALARM_ID_KEY = "ALARM_ID"

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
                    composable(
                        route = "$ALARM_ADD_EDIT_SCREEN?$ALARM_ID_KEY={$ALARM_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = ALARM_ID_KEY) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val alarmId = it.arguments?.getInt(ALARM_ID_KEY) ?: -1
                        AddEditAlarmScreen(navController)
                    }
                }
            }
        }
    }
}

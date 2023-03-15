package com.example.alarmapp.feature_note.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.alarmapp.feature_note.presentation.screens.add_edit_alarm_screen.AddEditAlarmScreen
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.AlarmListScreen
import com.example.alarmapp.ui.theme.AlarmAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.security.Permissions


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
                NavHostScreens(navController = navController)
            }
        }
    }

    @Composable
    fun NavHostScreens(navController: NavHostController) {
        NavHost(navController = navController , startDestination = ALARM_LIST_SCREEN) {
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


    /*override fun onStart() {
        super.onStart()
        Log.d("younes1", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("younes1", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("younes1", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("younes1", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("younes1", "onDestroy")
    }*/
}



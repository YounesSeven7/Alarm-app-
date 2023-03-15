package com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.components.AlarmItem
import com.example.alarmapp.feature_note.presentation.screens.alarm_list_screen.event.AlarmListScreenEvent
import com.example.alarmapp.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AlarmListScreen(
    navController: NavController,
    viewModel: AlarmViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    val postNotificationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted)
                addIconOnClick(viewModel, navController)
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                addIconOnClick = {
                    if (!checkPostNotificationPermission(context)) {
                        postNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        addIconOnClick(viewModel, navController)
                    }
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(state.alarms) { alarm ->
                AlarmItem(
                    alarm = alarm,
                    onSwitchClick = { isActive ->
                        viewModel.onEvent(
                            AlarmListScreenEvent.ActivatingState(
                            alarm = alarm.copy(isActive = isActive)
                            )
                        )
                    },
                    modifier = Modifier.combinedClickable(
                        onClick = {
                            viewModel.onEvent(
                                AlarmListScreenEvent.EditScreen(
                                    navController = navController,
                                    alarmId = alarm.id!!
                                )
                            )
                        },
                        onLongClick = {

                        }
                    )
                )
            }
        }
    }

}

fun addIconOnClick(viewModel: AlarmViewModel, navController: NavController) {
    viewModel.onEvent(AlarmListScreenEvent.AddScreen(navController))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    addIconOnClick: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = "Alarm",
                color = MaterialTheme.colorScheme.primary,
                style = Typography.titleLarge
            )
        },
        actions = {
            AddIcon(addIconOnClick)
            MoreIcon{ showMenu = !showMenu }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Edit") },
                    onClick = {  }
                )
                // TODO -> settings
            }
        }
    )
}

@Composable
fun AddIcon(
    addIconOnClick: () -> Unit
) {
    IconButton(onClick = {
        addIconOnClick()
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun MoreIcon(moreIconOnClick: () -> Unit) {
    IconButton(onClick = { moreIconOnClick() }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}



fun checkPostNotificationPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

@Composable
fun PostNotificationRequest() {

}
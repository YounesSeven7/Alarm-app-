package com.example.alarmapp.feature_note.presentation.alarmScheduler

import com.example.alarmapp.feature_note.domain.model.Alarm

interface AlarmScheduler {

    fun schedule(alarm: Alarm)

    fun cancel(item: Alarm)

}
package com.example.alarmapp.feature_note.domain.model

data class
Time(
    val hours: Int,
    val minutes: Int,
    val isMorning: Boolean
) {
    companion object {
        const val AM = "AM"
        const val PM = "PM"
    }
}

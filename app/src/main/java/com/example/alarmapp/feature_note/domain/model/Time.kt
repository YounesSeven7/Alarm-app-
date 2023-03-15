package com.example.alarmapp.feature_note.domain.model

import java.io.Serializable

data class Time(
    var hours: Int,
    var minutes: Int,
    var isMorning: Boolean
): Serializable {
    companion object {
        const val AM = "AM"
        const val PM = "PM"
    }
}

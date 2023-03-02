package com.example.alarmapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val time: Time,
    val days: Int = allDays,
    val isActive: Boolean
) {
    companion object {
        val daysFirstLetterList = listOf('S', 'M', 'T', 'W', 'T', 'F', 'S')
        val daysNamesList = listOf("Sun" , "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        const val allDays = 7604321
    }
}

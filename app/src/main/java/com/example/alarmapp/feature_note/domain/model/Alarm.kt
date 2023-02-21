package com.example.alarmapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val isActive: Boolean
)

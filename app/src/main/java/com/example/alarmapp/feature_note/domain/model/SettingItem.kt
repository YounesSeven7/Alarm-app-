package com.example.alarmapp.feature_note.domain.model

data class SettingItem(
    val itemName: String,
    val itemCurrentValue: String,
    val itemIsChecked: Boolean,
    val itemOnCheckChange: (Boolean) -> Unit
)

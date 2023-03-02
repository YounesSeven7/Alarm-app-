package com.example.alarmapp.feature_note.data.data_base.converter

import androidx.room.TypeConverter
import com.example.alarmapp.feature_note.domain.model.Time



class TimeConverter {

    @TypeConverter
    fun toTime(timeInMinutes: Int): Time {
        return Time(
            hours = timeInMinutes / 60,
            minutes = timeInMinutes % 60,
            isMorning = timeInMinutes >= 0
        )
    }

    @TypeConverter
    fun fromTime(time: Time): Int {
        val timeInMinutes = time.hours * 60 + time.minutes
        timeInMinutes.let {
            if (!time.isMorning)
                it * -1
        }
        return timeInMinutes
    }



}
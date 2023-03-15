package com.example.alarmapp.feature_note.presentation.util


import java.time.LocalDate
import com.example.alarmapp.feature_note.domain.model.Time
import java.time.LocalTime
import java.util.Locale

fun timeFormat(time: Time):String {
    return String.format(Locale.US, "%02d:%02d %s",
        time.hours,
        time.minutes,
        if (time.isMorning) Time.AM else Time.PM
    )
}

/*
// s -> 6, m -> 0, t -> 1, w -> 2, t -> 3, f -> 4 s -> 5
// s -> 0, m -> 1, t -> 2, w -> 3, t -> 4, f -> 5 s -> 6
fun LocalDate.now(): LocalDate? {
    return LocalDate.now().plusDays(1)
}
*/


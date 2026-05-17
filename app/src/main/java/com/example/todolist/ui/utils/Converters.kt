package com.example.todolist.ui.utils

import androidx.room.TypeConverter
import java.time.LocalDate

// конвертация даты для Room из LocalDate в Long и обратно

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }
    @TypeConverter
    fun localDateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}
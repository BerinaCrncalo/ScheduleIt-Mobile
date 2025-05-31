package com.example.scheduleit.data.converter

import androidx.room.TypeConverter
import java.time.format.DateTimeFormatter
import java.util.Date

class DateTimeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
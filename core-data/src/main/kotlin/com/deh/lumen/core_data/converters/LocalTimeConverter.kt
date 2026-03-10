package com.deh.lumen.core_data.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun fromLocalTime(value: LocalTime): String {
        return value.toString() // ISO-8601: "21:00"
    }

    @TypeConverter
    fun toLocalTime(value: String): LocalTime {
        return LocalTime.parse(value)
    }
}
package com.deh.lumen.core_data.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.toString() // ISO-8601: "2026-02-20"
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }
}
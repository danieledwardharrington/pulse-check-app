package com.deh.lumen.core_data.converters

import androidx.room.TypeConverter
import com.deh.lumen.core_data.entity.enum.FocusArea
import kotlinx.serialization.json.Json

class FocusAreaConverter {
    @TypeConverter
    fun fromFocusAreaList(value: List<FocusArea>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toFocusAreaList(value: String): List<FocusArea> {
        return Json.decodeFromString(value)
    }
}
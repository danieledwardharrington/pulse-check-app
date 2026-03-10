package com.deh.lumen.core_data.converters

import androidx.room.TypeConverter
import com.deh.lumen.core_data.entity.enum.UserIntention

class UserIntentionConverter {
    @TypeConverter
    fun fromUserIntention(value: UserIntention): String {
        return value.name
    }

    @TypeConverter
    fun toUserIntention(value: String): UserIntention {
        return UserIntention.valueOf(value)
    }
}
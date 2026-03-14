package com.deh.lumen.checkin.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.deh.lumen.checkin.R
import com.deh.lumen.core_data.entity.enum.MoodLevel
import com.deh.lumen.core_ui.theme.LumenTheme

enum class MoodUI(
    val moodNameRes: Int,
    val moodEmoji: String
) {
    ROUGH(
        moodNameRes = R.string.mood_rough,
        moodEmoji = "\u26C8\uFE0F"

    ),
    LOW(
        moodNameRes = R.string.mood_low,
        moodEmoji = "\uD83C\uDF27"
    ),
    OKAY(
        moodNameRes = R.string.mood_okay,
        moodEmoji = "\uD83C\uDF25\uFE0F"
    ),
    GOOD(
        moodNameRes = R.string.mood_good,
        moodEmoji = "\uD83C\uDF24\uFE0F"
    ),
    GREAT(
        moodNameRes = R.string.mood_great,
        moodEmoji = "\u2600\uFE0F"
    );

    val moodColor: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            ROUGH -> LumenTheme.extendedColors.moodStrugglingSubtle
            LOW -> LumenTheme.extendedColors.moodLowSubtle
            OKAY -> LumenTheme.extendedColors.moodOkaySubtle
            GOOD -> LumenTheme.extendedColors.moodGoodSubtle
            GREAT -> LumenTheme.extendedColors.moodGreatSubtle
        }
}

fun MoodUI.toMoodLevel(): MoodLevel {
    return when (this) {
        MoodUI.ROUGH -> MoodLevel.ROUGH
        MoodUI.LOW -> MoodLevel.LOW
        MoodUI.OKAY -> MoodLevel.OKAY
        MoodUI.GOOD -> MoodLevel.GOOD
        MoodUI.GREAT -> MoodLevel.GREAT
    }
}
package com.deh.lumen.checkin.models

import com.deh.lumen.checkin.R

enum class Mood(
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
    )
}
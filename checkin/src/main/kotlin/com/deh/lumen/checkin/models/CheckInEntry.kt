package com.deh.lumen.checkin.models

import com.deh.lumen.core_data.QuestionAnswerPair
import com.deh.lumen.core_data.entity.enum.MoodLevel

data class CheckInEntry(
    val selectedMoodUI: MoodUI?,
    val moodLevel: MoodLevel? = selectedMoodUI?.toMoodLevel(),
    val questionsAnswers: List<QuestionAnswerPair>,
    val aiReflection: String?
)
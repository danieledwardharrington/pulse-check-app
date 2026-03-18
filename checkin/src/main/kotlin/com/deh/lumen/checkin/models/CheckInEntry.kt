package com.deh.lumen.checkin.models

import com.deh.lumen.core_data.QuestionAnswerPair
import com.deh.lumen.core_data.entity.enum.MoodLevel
import com.deh.lumen.core_data.models.SafetyStatus

data class CheckInEntry(
    val selectedMoodUI: MoodUI?,
    val moodLevel: MoodLevel? = selectedMoodUI?.toMoodLevel(),
    val questionsAnswers: List<QuestionAnswerPair>,
    val editedQuestionsAnswers: List<QuestionAnswerPair>? = null,
    val aiReflection: String?,
    val safetyStatus: SafetyStatus? = null
)
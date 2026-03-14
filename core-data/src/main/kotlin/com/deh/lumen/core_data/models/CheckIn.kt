package com.deh.lumen.core_data.models

import com.deh.lumen.core_data.QuestionAnswerPair
import com.deh.lumen.core_data.entity.CheckInEntity
import com.deh.lumen.core_data.entity.enum.MoodLevel
import com.deh.lumen.core_data.entity.enum.SafetyFlag
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class CheckIn(
    val id: String,
    val date: LocalDate,
    val moodScore: Int,
    val moodLevel: MoodLevel,
    val questionAnswerPairs: List<QuestionAnswerPair>,
    val editedAnswerPairs: List<QuestionAnswerPair>? = null,
    val aiReflection: String,
    val safetyFlag: SafetyFlag?,
    val submittedAt: LocalDateTime,
    val editedAt: LocalDateTime? = null
)

fun CheckIn.toCheckInEntity(): CheckInEntity {
    return CheckInEntity(
        id = id,
        date = date,
        moodScore = moodScore,
        moodLevel = moodLevel,
        questionAnswerPairs = questionAnswerPairs,
        editedAnswerPairs = editedAnswerPairs,
        aiReflection = aiReflection,
        safetyFlag = safetyFlag,
        submittedAt = submittedAt,
        editedAt = editedAt
    )
}

package com.deh.lumen.core_data

import kotlinx.serialization.Serializable

@Serializable
data class QuestionAnswerPair(
    val question: String,
    val answer: String
)

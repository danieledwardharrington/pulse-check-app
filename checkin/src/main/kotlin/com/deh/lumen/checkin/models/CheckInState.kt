package com.deh.lumen.checkin.models

data class CheckInState(
    val checkInEntry: CheckInEntry = CheckInEntry(
        selectedMoodUI = null,
        questionsAnswers = emptyList(),
        aiReflection = null
    ),
    val shouldShowQuestions: Boolean = checkInEntry.selectedMoodUI != null,
    val continueButtonEnabled: Boolean = checkInEntry.selectedMoodUI != null &&
            checkInEntry.questionsAnswers.all { it.question.isNotEmpty() && it.answer.isNotEmpty() },
    val showLoading: Boolean = false,
    val hasCheckedInToday: Boolean = false,
    val surfaceSafetyInfo: Boolean = false
)

package com.deh.lumen.checkin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deh.lumen.checkin.models.CheckInState
import com.deh.lumen.checkin.models.MoodUI
import com.deh.lumen.checkin.usecase.GenerateQuestionsUseCase
import com.deh.lumen.checkin.usecase.GenerateReflectionUseCase
import com.deh.lumen.checkin.usecase.HasCheckedInUseCase
import com.deh.lumen.checkin.usecase.SaveCheckInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor(
    private val generateQuestionsUseCase: GenerateQuestionsUseCase,
    private val hasCheckedInUseCase: HasCheckedInUseCase,
    private val saveCheckInUseCase: SaveCheckInUseCase,
    private val generateReflectionUseCase: GenerateReflectionUseCase
) : ViewModel() {
    private val _checkInState = MutableStateFlow(CheckInState())
    val checkInState: StateFlow<CheckInState> = _checkInState.asStateFlow()

    fun onMoodSelected(moodUI: MoodUI) {
        if (_checkInState.value.checkInEntry.selectedMoodUI != moodUI) {
            _checkInState.update {
                it.copy(
                    checkInEntry = it
                        .checkInEntry
                        .copy(selectedMoodUI = moodUI)
                )
            }
        }
    }

    fun onFirstResponseChanged(response: String) {
        _checkInState.update {
            it.copy(
                checkInEntry = it
                    .checkInEntry
                    .copy(
                        questionsAnswers = listOf(
                            it.checkInEntry.questionsAnswers[0].copy(
                                answer = response
                            ),
                            it.checkInEntry.questionsAnswers[1]
                        )
                    )
            )
        }
    }

    fun onSecondResponseChanged(response: String) {
        _checkInState.update {
            it.copy(
                checkInEntry = it
                    .checkInEntry
                    .copy(
                        questionsAnswers = listOf(
                            it.checkInEntry.questionsAnswers[0],
                            it.checkInEntry.questionsAnswers[1].copy(
                                answer = response
                            )
                        )
                    )
            )
        }
    }

    private fun updateQuestions(
        firstQuestion: String,
        secondQuestion: String
    ) {
        _checkInState.update {
            it.copy(
                checkInEntry = it
                    .checkInEntry
                    .copy(
                        questionsAnswers = listOf(
                            it.checkInEntry.questionsAnswers[0].copy(
                                question = firstQuestion
                            ),
                            it.checkInEntry.questionsAnswers[1].copy(
                                question = secondQuestion
                            )
                        )
                    )
            )
        }
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            // TODO: Check safety and save check in object
        }
    }

    private fun checkSafety() {
        // TODO: Evaluate responses for safety information
    }
}
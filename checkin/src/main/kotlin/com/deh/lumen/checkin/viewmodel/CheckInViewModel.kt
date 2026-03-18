package com.deh.lumen.checkin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deh.lumen.checkin.models.CheckInState
import com.deh.lumen.checkin.models.MoodUI
import com.deh.lumen.checkin.models.toMoodLevel
import com.deh.lumen.checkin.usecase.GenerateQuestionsUseCase
import com.deh.lumen.checkin.usecase.GenerateReflectionUseCase
import com.deh.lumen.checkin.usecase.HasCheckedInUseCase
import com.deh.lumen.checkin.usecase.SafetyUseCase
import com.deh.lumen.checkin.usecase.SaveCheckInUseCase
import com.deh.lumen.core_data.QuestionAnswerPair
import com.deh.lumen.core_data.entity.enum.MoodLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor(
    private val generateQuestionsUseCase: GenerateQuestionsUseCase,
    private val hasCheckedInUseCase: HasCheckedInUseCase,
    private val saveCheckInUseCase: SaveCheckInUseCase,
    private val generateReflectionUseCase: GenerateReflectionUseCase,
    private val safetyUseCase: SafetyUseCase
) : ViewModel() {
    private val _checkInState = MutableStateFlow(CheckInState())
    val checkInState: StateFlow<CheckInState> = _checkInState.asStateFlow()

    init {
        _checkInState.update { it.copy(showLoading = true) }
        viewModelScope.launch {
            _checkInState.update {
                it.copy(
                    hasCheckedInToday = hasCheckedInUseCase()
                )
            }
        }.invokeOnCompletion {
            _checkInState.update {
                it.copy(showLoading = false)
            }
        }
    }

    fun onMoodSelected(moodUI: MoodUI) {
        if (_checkInState.value.checkInEntry.selectedMoodUI != moodUI) {
            _checkInState.update {
                it.copy(
                    checkInEntry = it
                        .checkInEntry
                        .copy(selectedMoodUI = moodUI),
                    showLoading = true
                )
            }
        }

        viewModelScope.launch {
            generateQuestionsUseCase(moodUI.toMoodLevel())
                .fold(
                    onSuccess = {
                        updateQuestions(it.first, it.second)
                    },
                    onFailure = { error ->
                        Timber.e("Error generating questions: $error")
                    }
                )
        }.invokeOnCompletion {
            _checkInState.update {
                it.copy(showLoading = false)
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
                    ),
                continueButtonEnabled = response.isNotEmpty() && it.checkInEntry.questionsAnswers[1].answer.isNotEmpty()
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
                    ),
                continueButtonEnabled = response.isNotEmpty() && it.checkInEntry.questionsAnswers[0].answer.isNotEmpty()
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
            val result = saveCheckInUseCase(checkInState.value.checkInEntry)

            result.fold(
                onSuccess = { savedEntity ->
                    launch {
                        safetyUseCase(
                            entryId = savedEntity.id,
                            answers = savedEntity.questionAnswerPairs
                        ).onSuccess { status ->
                            if (status.flagged) {
                                _checkInState.update {
                                    it
                                        .copy(
                                            surfaceSafetyInfo = true,
                                            checkInEntry = it
                                                .checkInEntry
                                                .copy(safetyStatus = status)
                                        )
                                }
                            }
                        }
                    }.invokeOnCompletion { throwable ->
                        if (throwable != null) {
                            Timber.e("Safety classification failed, no safety status for this entry: $throwable")
                        }
                    }
                    generateReflectionUseCase(
                        moodLevel = savedEntity.moodLevel,
                        answers = savedEntity.questionAnswerPairs
                    ).fold(
                        onSuccess = { reflection ->
                            _checkInState.update {
                                it.copy(checkInEntry = checkInState.value.checkInEntry.copy(aiReflection = reflection))
                            }
                        },
                        onFailure = {
                            Timber.e("Error generating reflection: $it")
                        }
                    )
                },
                onFailure = { error ->
                    Timber.e("Error saving user check in: $error")
                }
            )
        }
    }

    private fun dismissSafetyInfo() {
        _checkInState.update {
            it.copy(surfaceSafetyInfo = false)
        }
    }
}
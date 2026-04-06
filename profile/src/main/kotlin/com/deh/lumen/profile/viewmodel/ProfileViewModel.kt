package com.deh.lumen.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deh.lumen.core_data.repository.CheckInRepository
import com.deh.lumen.core_data.repository.UserRepository
import com.deh.lumen.core_data.toUserEntity
import com.deh.lumen.core_data.toUserProfile
import com.deh.lumen.profile.R
import com.deh.lumen.profile.models.ProfileState
import com.deh.lumen.profile.usecase.DeleteAllDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject
import kotlin.time.Clock

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val checkInRepository: CheckInRepository,
    private val deleteAllDataUseCase: DeleteAllDataUseCase
): ViewModel() {
    private val _profileState: MutableStateFlow<ProfileState> = combine(
        userRepository.observeUser(),
        checkInRepository.observeTotalCount(),
        checkInRepository.observeAverageMoodScore(),
        checkInRepository.observeBestMonth()
    ) { user, totalCount, averageMood, bestMonth ->
        if (user == null) {
            ProfileState.Initial
        } else {
            ProfileState.Ready(
                userProfile = user
                    .toUserProfile()
                    .copy(
                        totalCheckInCount = totalCount,
                        averageMoodScore = averageMood ?: 0f,
                        bestMonthDate = bestMonth
                            ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
                    )
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ProfileState.Initial
    ) as MutableStateFlow<ProfileState>

    val profileState = _profileState.asStateFlow()

    fun onShowCheckInOptionDialog() {
        _profileState.update {
            (it as ProfileState.Ready).copy(
                showDialog = it.showDialog.not()
            )
        }
    }

    fun onNotificationItemCheckChange(checkValue: Boolean, itemTitleRes: Int) {
        if (_profileState.value is ProfileState.Ready) {
            when (itemTitleRes) {
                R.string.push_notifications_title -> {
                    _profileState.update {
                        (it as ProfileState.Ready).copy(
                            userProfile = it.userProfile.copy(
                                notificationsEnabled = checkValue
                            )
                        )
                    }
                }

                R.string.app_lock_title -> {
                    _profileState.update {
                        (it as ProfileState.Ready).copy(
                            userProfile = it.userProfile.copy(
                                appLockEnabled = checkValue
                            )
                        )
                    }
                }

                R.string.cloud_backup_title -> {
                    _profileState.update {
                        (it as ProfileState.Ready).copy(
                            userProfile = it.userProfile.copy(
                                cloudEnabled = checkValue
                            )
                        )
                    }
                }

                R.string.monitoring_title -> {
                    _profileState.update {
                        (it as ProfileState.Ready).copy(
                            userProfile = it.userProfile.copy(
                                monitoringEnabled = checkValue
                            )
                        )
                    }
                }
            }
            saveUserProfile()
        }
    }

    private fun saveUserProfile() {
        if (profileState.value is ProfileState.Ready) {
            viewModelScope.launch {
                userRepository.saveUser(
                    (profileState.value as ProfileState.Ready)
                        .userProfile
                        .toUserEntity()
                )
            }
        }
    }
}
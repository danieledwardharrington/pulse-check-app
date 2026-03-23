package com.deh.lumen.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deh.lumen.core_data.entity.toUserProfile
import com.deh.lumen.core_data.repository.CheckInRepository
import com.deh.lumen.core_data.repository.UserRepository
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.profile.R
import com.deh.lumen.profile.models.ProfileItem
import com.deh.lumen.profile.models.ProfileState
import com.deh.lumen.profile.usecase.DeleteAllDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val checkInRepository: CheckInRepository,
    private val deleteAllDataUseCase: DeleteAllDataUseCase
): ViewModel() {
    private val _profileState: MutableStateFlow<ProfileState> = combine(
        userRepository.observeUser(),
        checkInRepository.observeTotalCount(),
        checkInRepository.observeAverageMoodScore()
    ) { user, totalCount, averageMood ->
        if (user == null) {
            ProfileState.Initial
        } else {
            ProfileState.Ready(
                userProfile = user
                    .toUserProfile()
                    .copy(totalCheckInCount = totalCount, averageMoodScore = averageMood ?: 0f)
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ProfileState.Initial
    ) as MutableStateFlow<ProfileState>

    val profileState = _profileState.asStateFlow()
}
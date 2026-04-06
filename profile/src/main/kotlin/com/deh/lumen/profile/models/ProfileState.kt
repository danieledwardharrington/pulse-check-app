package com.deh.lumen.profile.models

import com.deh.lumen.core_data.models.UserProfile

sealed class ProfileState {
    object Initial: ProfileState()
    data class Ready(
        val userProfile: UserProfile,
        val showDialog: Boolean = false
    ): ProfileState()
}
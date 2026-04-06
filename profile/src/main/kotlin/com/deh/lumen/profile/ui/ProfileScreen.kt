package com.deh.lumen.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.deh.lumen.profile.models.ProfileState
import com.deh.lumen.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val profileState = viewModel.profileState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (profileState.value is ProfileState.Initial) {
            ProfileEmptyState()
        } else {
            ProfileContent(
                profileState = profileState.value as ProfileState.Ready,
                onNotificationItemSwitchChange = viewModel::onNotificationItemCheckChange
            )
        }
    }
}
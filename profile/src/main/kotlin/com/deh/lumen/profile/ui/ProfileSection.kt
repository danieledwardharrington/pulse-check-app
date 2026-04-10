package com.deh.lumen.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.profile.models.ProfileItem

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    sectionTitle: Int,
    profileItems: List<ProfileItem>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(sectionTitle).uppercase(),
            style = LumenTheme.typography.titleSmall,
            color = LumenTheme.colors.onSurfaceVariant
        )

        ProfileSettingsColumn(
            modifier = modifier,
            profileItems = profileItems
        )
    }
}
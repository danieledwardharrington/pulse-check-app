package com.deh.lumen.checkin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deh.lumen.checkin.models.MoodUI
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun MoodOrb(
    modifier: Modifier = Modifier,
    orbSize: Int,
    moodUI: MoodUI,
    onOrbClicked: (MoodUI) -> Unit
) {
    Box(
        modifier = modifier
            .size(orbSize.dp)
            .background(color = moodUI.moodColor, shape = CircleShape)
            .clickable { onOrbClicked(moodUI) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = moodUI.moodEmoji,
                style = LumenTheme.typography.labelSmall
            )

            Text(
                text = stringResource(moodUI.moodNameRes),
                style = LumenTheme.typography.labelSmall,
                color = LumenTheme.colors.onSurfaceVariant
            )
        }
    }
}
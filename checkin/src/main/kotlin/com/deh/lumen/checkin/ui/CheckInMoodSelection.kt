package com.deh.lumen.checkin.ui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deh.lumen.checkin.R
import com.deh.lumen.checkin.models.MoodUI
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun CheckInMoodSelection(
    modifier: Modifier = Modifier,
    onMoodChange: (MoodUI) -> Unit,
    moods: List<MoodUI>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.mood_selection).uppercase(),
            style = LumenTheme.typography.labelMedium,
            color = LumenTheme.colors.onSurfaceVariant
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var orbSize = 56
            val orbSizeState = animateIntAsState(orbSize)

            for (mood in moods) {
                MoodOrb(
                    orbSize = orbSizeState.value,
                    moodUI = mood,
                    onOrbClicked = {
                        onMoodChange(mood)
                        orbSize = 72
                    }
                )
            }
        }
    }
}
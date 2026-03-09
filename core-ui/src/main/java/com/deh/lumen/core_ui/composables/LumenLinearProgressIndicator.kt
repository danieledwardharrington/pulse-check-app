package com.deh.lumen.core_ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun LumenLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float
) {
    val animatedProgress = animateFloatAsState(targetValue = progress)
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp),
        progress = { animatedProgress.value },
        color = LumenTheme.colors.primary,
        trackColor = LumenTheme.colors.surface,
        strokeCap = StrokeCap.Round,
        gapSize = (-10).dp,
        drawStopIndicator = {}
    )
}
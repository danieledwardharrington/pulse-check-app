package com.deh.lumen.core_ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun LumenButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit,
    buttonTextColor: Color = LumenTheme.colors.onPrimary,
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        onClick = { onButtonClick() },
        border = BorderStroke(1.dp, LumenTheme.colors.outline),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.Transparent,
            disabledContentColor = LumenTheme.colors.onSurface.copy(alpha = 0.12f)
        ),
        enabled = isEnabled,
        contentPadding = PaddingValues(),
        shape = LumenTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LumenTheme.extendedColors.gradientCta)
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buttonText,
                style = LumenTheme.typography.titleSmall,
                color = if (isEnabled) buttonTextColor else buttonTextColor.copy(alpha = 0.38f)
            )
        }
    }
}

@Composable
fun LumenButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit,
    backgroundColor: Color,
    buttonTextColor: Color = LumenTheme.colors.onPrimary,
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        onClick = { onButtonClick() },
        border = BorderStroke(1.dp, LumenTheme.colors.outline),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = backgroundColor
        ),
        enabled = isEnabled,
        contentPadding = PaddingValues(),
        shape = LumenTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buttonText,
                style = LumenTheme.typography.titleSmall,
                color = if (isEnabled) buttonTextColor else buttonTextColor.copy(alpha = 0.38f)
            )
        }
    }
}
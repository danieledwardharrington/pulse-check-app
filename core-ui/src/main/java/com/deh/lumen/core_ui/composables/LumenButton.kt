package com.deh.lumen.core_ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
    backgroundColor: Color = LumenTheme.extendedColors.buttonCta,
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
        enabled = isEnabled
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = buttonText,
                style = LumenTheme.typography.bodyMedium,
                color = LumenTheme.colors.onPrimary
            )
        }
    }
}
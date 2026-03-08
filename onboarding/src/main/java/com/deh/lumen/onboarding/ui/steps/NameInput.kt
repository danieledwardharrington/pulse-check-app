package com.deh.lumen.onboarding.ui.steps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit
) {
    var input by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        value = input,
        onValueChange = {
            input = it
            onNameChange(input)
        },
        textStyle = LumenTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = LumenTheme.colors.onBackground,
            unfocusedTextColor = LumenTheme.colors.onBackground,
            focusedContainerColor = LumenTheme.colors.surface,
            unfocusedContainerColor = LumenTheme.colors.surface,
            focusedBorderColor = LumenTheme.colors.outlineVariant,
            unfocusedBorderColor = LumenTheme.colors.outline,
            cursorColor = LumenTheme.colors.primary
        ),
        singleLine = true,
        shape = LumenTheme.shapes.small
    )
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun NameInputPreview() {
    LumenTheme {
        NameInput(onNameChange = {})
    }
}
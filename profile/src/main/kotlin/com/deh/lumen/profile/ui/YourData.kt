package com.deh.lumen.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.composables.LumenButton
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.profile.R

@Composable
fun YourData(
    modifier: Modifier = Modifier,
    onExportEntriesClicked: () -> Unit,
    onDeleteDataClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.your_data).uppercase(),
            style = LumenTheme.typography.titleSmall,
            color = LumenTheme.colors.onSurfaceVariant
        )

        LumenButton(
            buttonText = stringResource(R.string.export_as_pdf),
            onButtonClick = onExportEntriesClicked,
            backgroundColor = LumenTheme.colors.background,
            outlineColor = LumenTheme.colors.outline,
            buttonTextColor = LumenTheme.colors.onSurfaceVariant,
        )

        LumenButton(
            buttonText = stringResource(R.string.delete_data),
            onButtonClick = onDeleteDataClicked,
            backgroundColor = LumenTheme.colors.background,
            outlineColor = LumenTheme.colors.secondary,
            buttonTextColor = LumenTheme.colors.secondary,
        )
    }
}

@Composable
@Preview
private fun PreviewYourData() {
    LumenTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LumenTheme.colors.background)
        ) {
            YourData(
                onExportEntriesClicked = {},
                onDeleteDataClicked = {}
            )
        }
    }
}
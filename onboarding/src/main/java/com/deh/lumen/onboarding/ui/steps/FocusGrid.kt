package com.deh.lumen.onboarding.ui.steps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.onboarding.models.OnboardingFocus

@Composable
fun FocusGrid(
    modifier: Modifier = Modifier,
    focusAreas: List<OnboardingFocus>,
    onFocusClicked: (OnboardingFocus) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (focus in focusAreas) {
            item {
                FocusAreaCard(
                    modifier = modifier,
                    focusArea = focus,
                    onFocusClicked = onFocusClicked
                )
            }
        }
    }
}

@Composable
private fun FocusAreaCard(
    modifier: Modifier = Modifier,
    focusArea: OnboardingFocus,
    onFocusClicked: (OnboardingFocus) -> Unit
) {
    val borderColor = if (focusArea.isSelected) LumenTheme.extendedColors.buttonCta.copy(alpha = 0.45f) else LumenTheme.colors.outline
    val backgroundColor = if (focusArea.isSelected) LumenTheme.extendedColors.buttonCta.copy(alpha = 0.07f) else LumenTheme.colors.surface
    val textColor = if (focusArea.isSelected) LumenTheme.extendedColors.buttonCta else LumenTheme.colors.onSurface

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, borderColor),
        shape = LumenTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = focusArea.focusEmoji
                )

                Text(
                    text = stringResource(focusArea.focusRes),
                    color = textColor,
                    style = LumenTheme.typography.titleSmall
                )
            }

            Checkbox(
                checked = focusArea.isSelected,
                onCheckedChange = { onFocusClicked(focusArea) },
                colors = CheckboxDefaults.colors(
                    checkedColor = LumenTheme.extendedColors.buttonCta,
                    uncheckedColor = LumenTheme.colors.outline
                )
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewFocusGrid() {
    LumenTheme {
        FocusGrid(
            focusAreas = OnboardingFocus.entries,
            onFocusClicked = {}
        )
    }
}
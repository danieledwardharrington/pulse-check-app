package com.deh.lumen.onboarding.ui.steps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.onboarding.models.OnboardingIntent

@Composable
fun IntentList(
    modifier: Modifier = Modifier,
    intents: List<OnboardingIntent>,
    onIntentClicked: (OnboardingIntent) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (intent in intents) {
            item {
                IntentRadioCard(
                    modifier = modifier,
                    intent = intent,
                    onIntentClicked = onIntentClicked
                )
            }
        }
    }
}

@Composable
private fun IntentRadioCard(
    modifier: Modifier = Modifier,
    intent: OnboardingIntent,
    onIntentClicked: (OnboardingIntent) -> Unit
) {
    val borderColor = if (intent.isSelected) LumenTheme.colors.outlineVariant else LumenTheme.colors.outline
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, borderColor),
        shape = LumenTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = LumenTheme.colors.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = intent.isSelected,
                onClick = { onIntentClicked(intent) }
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(intent.intentTitleRes),
                    color = LumenTheme.colors.onSurface,
                    style = LumenTheme.typography.titleMedium
                )

                Text(
                    text = stringResource(intent.intentDescriptionRes),
                    color = LumenTheme.colors.onSurfaceVariant,
                    style = LumenTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewIntentList() {
    LumenTheme {
        IntentList(
            intents = OnboardingIntent.entries,
            onIntentClicked = {}
        )
    }
}
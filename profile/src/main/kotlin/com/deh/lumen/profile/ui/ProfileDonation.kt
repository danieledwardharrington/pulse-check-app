package com.deh.lumen.profile.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.composables.LumenButton
import com.deh.lumen.core_ui.composables.ThreeCardRow
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.profile.R

@Composable
fun ProfileDonation(
    modifier: Modifier = Modifier,
    onAmountClicked: (Int) -> Unit,
    onDonationClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.support_lumen).uppercase(),
            style = LumenTheme.typography.titleSmall,
            color = LumenTheme.colors.onSurfaceVariant
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = LumenTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = LumenTheme.colors.surface
            ),
            border = BorderStroke(1.dp, LumenTheme.extendedColors.outlineGold)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.enjoying_lumen),
                    style = LumenTheme.typography.headlineMedium,
                    color = LumenTheme.colors.onSurface,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.buy_coffee),
                    style = LumenTheme.typography.headlineMedium,
                    color = LumenTheme.colors.tertiary,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.donation_description),
                    style = LumenTheme.typography.bodyLarge,
                    color = LumenTheme.colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ThreeCardRow(
                        horizontalPaddingValue = 32,
                        firstCardTitle = stringResource(R.string.small_donation),
                        secondCardTitle = stringResource(R.string.medium_donation),
                        thirdCardTitle = stringResource(R.string.large_donation),
                        firstCardTitleColor = LumenTheme.colors.tertiary,
                        secondCardTitleColor = LumenTheme.colors.tertiary,
                        thirdCardTitleColor = LumenTheme.colors.tertiary,
                        outlineColor = LumenTheme.extendedColors.outlineGold,
                        containerColor = LumenTheme.colors.tertiaryContainer,
                        firstCardDescription = stringResource(R.string.sip),
                        secondCardDescription = stringResource(R.string.cup),
                        thirdCardDescription = stringResource(R.string.pot)
                    )

                    LumenButton(
                        backgroundGradient = LumenTheme.extendedColors.gradientTip,
                        buttonText = stringResource(R.string.donate),
                        onButtonClick = {
                            onDonationClick()
                        }
                    )

                    Text(
                        text = stringResource(R.string.processed_via_google_play),
                        style = LumenTheme.typography.labelSmall,
                        color = LumenTheme.colors.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProfileDonation() {
    LumenTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LumenTheme.colors.background)
        ) {
            ProfileDonation(
                onDonationClick = {},
                onAmountClicked = {}
            )
        }
    }
}
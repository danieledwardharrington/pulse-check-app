package com.deh.lumen.onboarding.ui.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.onboarding.models.OnboardingPrivacy

@Composable
fun PrivacyList(
    modifier: Modifier = Modifier,
    privacyItems: List<OnboardingPrivacy>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (privacyItem in privacyItems) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = privacyItem.emoji
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(privacyItem.titleRes),
                        style = LumenTheme.typography.titleMedium,
                        color = LumenTheme.colors.onBackground
                    )

                    Text(
                        stringResource(privacyItem.descriptionRes),
                        style = LumenTheme.typography.bodyMedium,
                        color = LumenTheme.colors.onSurfaceVariant
                    )
                }
            }
        }
    }
}
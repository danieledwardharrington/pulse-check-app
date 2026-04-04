package com.deh.lumen.core_ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ThreeCardRow(
    modifier: Modifier = Modifier,
    horizontalPaddingValue: Int,
    firstCardTitle: String,
    secondCardTitle: String,
    thirdCardTitle: String,
    firstCardDescription: String,
    secondCardDescription: String,
    thirdCardDescription: String,
    containerColor: Color,
    outlineColor: Color,
    firstCardTitleColor: Color,
    secondCardTitleColor: Color,
    thirdCardTitleColor: Color
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        val cardDimen = (screenWidth - horizontalPaddingValue) / 3

        Card(
            modifier = Modifier
                .size(cardDimen.dp)
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            border = BorderStroke(1.dp, outlineColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = firstCardTitle,
                    style = LumenTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Normal),
                    color = firstCardTitleColor
                )

                Text(
                    text = firstCardDescription,
                    style = LumenTheme.typography.bodySmall,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }
        }

        Card(
            modifier = Modifier
                .size(cardDimen.dp)
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            border = BorderStroke(1.dp, outlineColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = secondCardTitle,
                    style = LumenTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Normal),
                    color = secondCardTitleColor
                )

                Text(
                    text = secondCardDescription,
                    style = LumenTheme.typography.bodySmall,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }
        }

        Card(
            modifier = Modifier
                .size(cardDimen.dp)
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            border = BorderStroke(1.dp, outlineColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = thirdCardTitle,
                    style = LumenTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Normal),
                    color = thirdCardTitleColor
                )

                Text(
                    text = thirdCardDescription,
                    style = LumenTheme.typography.bodySmall,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }
        }
    }
}
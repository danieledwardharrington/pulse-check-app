package com.deh.lumen.checkin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@Composable
fun CheckInTitle(
    modifier: Modifier = Modifier,
    greetingRes: Int,
    titleQuestionRes: Int,
    displayName: String
) {
    val nowTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(greetingRes, displayName),
            style = LumenTheme.typography.displaySmall,
            color = LumenTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(titleQuestionRes),
            style = LumenTheme.typography.displaySmall.copy(fontStyle = FontStyle.Italic),
            color = LumenTheme.colors.secondary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "${nowTime.dayOfWeek.name.lowercase().replaceFirstChar { it.titlecase() }} \u00B7 ${nowTime.month.name.lowercase().replaceFirstChar { it.titlecase() }} ${nowTime.day}",
            style = LumenTheme.typography.bodySmall,
            color = LumenTheme.colors.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
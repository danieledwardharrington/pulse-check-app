package com.deh.lumen.core_ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.deh.lumen.core_ui.theme.LumenTheme
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInTimePicker(
    modifier: Modifier = Modifier,
    checkInTime: LocalTime = LocalTime(21, 0)
) {
    val timePickerState = rememberTimePickerState(
        initialHour = checkInTime.hour,
        initialMinute = checkInTime.minute,
        is24Hour = false
    )

    TimePicker(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = timePickerState,
        colors = TimePickerDefaults.colors(
            clockDialColor = LumenTheme.colors.surfaceVariant,
            clockDialSelectedContentColor = LumenTheme.colors.onPrimary,
            clockDialUnselectedContentColor = LumenTheme.colors.onSurfaceVariant,
            selectorColor = LumenTheme.colors.primary,
            containerColor = LumenTheme.colors.surfaceVariant,
            periodSelectorBorderColor = LumenTheme.colors.outline,
            periodSelectorSelectedContainerColor = LumenTheme.colors.primaryContainer,
            periodSelectorUnselectedContainerColor = Color.Transparent,
            periodSelectorSelectedContentColor = LumenTheme.colors.onPrimaryContainer,
            periodSelectorUnselectedContentColor = LumenTheme.colors.onSurfaceVariant,
            timeSelectorSelectedContainerColor = LumenTheme.colors.primaryContainer,
            timeSelectorUnselectedContainerColor = LumenTheme.colors.surfaceVariant,
            timeSelectorSelectedContentColor = LumenTheme.colors.onPrimaryContainer,
            timeSelectorUnselectedContentColor = LumenTheme.colors.onSurface,
        )
    )
}

@Composable
@Preview
private fun PreviewCheckInTimePicker() {
    LumenTheme {
        CheckInTimePicker()
    }
}

package com.deh.lumen.core_ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.deh.lumen.core_ui.R
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun LumenChevronButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(
            modifier = modifier,
            painter = painterResource(R.drawable.ic_chevron_right),
            tint = LumenTheme.colors.onSurfaceVariant,
            contentDescription = "Chevron button"
        )
    }
}
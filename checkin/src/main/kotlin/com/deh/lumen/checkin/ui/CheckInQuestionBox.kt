package com.deh.lumen.checkin.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.theme.LumenTheme

private const val MAX_INPUT_LENGTH = 300

@Composable
fun CheckInQuestionBox(
    modifier: Modifier = Modifier,
    checkInQuestion: String,
    onResponseChanged: (String) -> Unit
) {
    val questionText by remember { mutableStateOf(checkInQuestion) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = LumenTheme.colors.surface
        ),
        border = BorderStroke(1.dp, LumenTheme.colors.outline),
        shape = LumenTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var userCheckIn by remember { mutableStateOf("") }

            AnimatedContent(questionText) {
                Text(
                    text = it,
                    color = LumenTheme.colors.onSurface,
                    style = LumenTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic)
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = userCheckIn,
                onValueChange = {
                    if (it.length <= MAX_INPUT_LENGTH) {
                        userCheckIn = it
                        onResponseChanged(userCheckIn)
                    }
                },
                textStyle = LumenTheme.typography.bodyMedium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = LumenTheme.colors.onSurface,
                    unfocusedTextColor = LumenTheme.colors.onSurface,
                    cursorColor = LumenTheme.colors.primary,
                    unfocusedBorderColor = LumenTheme.colors.outline,
                    focusedBorderColor = LumenTheme.colors.outline,
                    focusedContainerColor = Color.White.copy(alpha = 0.04f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.04f)
                ),
                shape = LumenTheme.shapes.small,
                supportingText = {
                    if (userCheckIn.length / MAX_INPUT_LENGTH.toFloat() > .7f) {
                        Text(
                            text = "${userCheckIn.length} / $MAX_INPUT_LENGTH",
                            style = LumenTheme.typography.bodySmall,
                            color = LumenTheme.colors.onSurfaceVariant
                        )
                    }
                }
            )
        }
    }
}
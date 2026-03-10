package com.deh.lumen.checkin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.checkin.R
import com.deh.lumen.core_ui.theme.LumenTheme

@Composable
fun CheckInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(LumenTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CheckInTitle(
            greetingRes = R.string.greeting_morning,
            titleQuestionRes = R.string.greeting_question_1,
            displayName = "Daniel"
        )
    }
}

@Composable
@Preview
private fun PreviewCheckInScreen() {
    LumenTheme {
        CheckInScreen()
    }
}
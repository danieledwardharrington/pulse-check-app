package com.deh.lumen.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deh.lumen.core_ui.composables.LumenButton
import com.deh.lumen.core_ui.composables.LumenLinearProgressIndicator
import com.deh.lumen.core_ui.theme.LumenTheme
import com.deh.lumen.onboarding.models.OnboardingStep
import com.deh.lumen.onboarding.R
import com.deh.lumen.onboarding.models.OnboardingFocus
import com.deh.lumen.onboarding.models.OnboardingIntent
import com.deh.lumen.onboarding.models.OnboardingPrivacy
import com.deh.lumen.onboarding.ui.steps.CheckInTimePicker
import com.deh.lumen.onboarding.ui.steps.FocusGrid
import com.deh.lumen.onboarding.ui.steps.IntentList
import com.deh.lumen.onboarding.ui.steps.NameInput
import com.deh.lumen.onboarding.ui.steps.PrivacyList

@Composable
fun OnboardingScreen(
    onboardingStep: OnboardingStep
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LumenTheme.colors.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(LumenTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LumenLinearProgressIndicator(
                progress = onboardingStep.progress
            )

            Spacer(modifier = Modifier.height(0.dp)) // Cheat 24dp spacing between progress bar and content

            val isCenterAligned = onboardingStep is OnboardingStep.Welcome || onboardingStep is OnboardingStep.Complete
            val textAlignment = if (isCenterAligned) TextAlign.Center else TextAlign.Start

            if (onboardingStep.logo != null) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(onboardingStep.logo),
                    contentDescription = stringResource(R.string.logo_content_description)
                )
            }

            if (onboardingStep.supertitleRes != null) {
                Text(
                    text = stringResource(onboardingStep.supertitleRes).uppercase(),
                    textAlign = textAlignment,
                    style = LumenTheme.typography.bodyMedium,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }

            Text(
                text = stringResource(onboardingStep.titleRes),
                textAlign = textAlignment,
                style = LumenTheme.typography.headlineLarge.copy(fontStyle = FontStyle.Italic),
                color = LumenTheme.colors.onBackground
            )

            if (onboardingStep.descriptionRes != null) {
                Text(
                    text = stringResource(onboardingStep.descriptionRes),
                    textAlign = TextAlign.Center,
                    style = LumenTheme.typography.bodyMedium,
                    color = LumenTheme.colors.onSurfaceVariant
                )
            }

            when (onboardingStep) {
                is OnboardingStep.Name -> NameInput(
                    onNameChange = { }
                )
                is OnboardingStep.Intent -> IntentList(
                    onIntentClicked = { },
                    intents = OnboardingIntent.entries
                )
                is OnboardingStep.Focus -> FocusGrid(
                    onFocusClicked = { },
                    focusAreas = OnboardingFocus.entries
                )
                is OnboardingStep.Time -> CheckInTimePicker()
                is OnboardingStep.Privacy -> PrivacyList(
                    privacyItems = OnboardingPrivacy.entries
                )
                else -> {}
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LumenButton(
                buttonText = stringResource(onboardingStep.firstButtonTextRes),
                isEnabled = true, // TODO: enable/disable based on input/choices
                onButtonClick = {
                    // TODO: Continue button click
                }
            )

            if (onboardingStep.secondButtonTextRes != null) {
                LumenButton(
                    buttonText = stringResource(onboardingStep.secondButtonTextRes),
                    backgroundColor = Color.Transparent,
                    buttonTextColor = LumenTheme.colors.onSurfaceVariant,
                    onButtonClick = {
                        // TODO: Negative button click
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenWelcome() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Welcome())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenName() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Name())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenIntent() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Intent())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenFocus() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Focus())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenCheckInTimePicker() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Time())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenPrivacy() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Privacy())
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreenComplete() {
    LumenTheme {
        OnboardingScreen(onboardingStep = OnboardingStep.Complete())
    }
}
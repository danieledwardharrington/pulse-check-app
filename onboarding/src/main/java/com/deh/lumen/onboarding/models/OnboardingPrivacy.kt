package com.deh.lumen.onboarding.models

import com.deh.lumen.onboarding.R

enum class OnboardingPrivacy(
    val emoji: String,
    val titleRes: Int,
    val descriptionRes: Int
) {
    DEVICE(
        emoji = "\uD83D\uDD12",
        titleRes = R.string.privacy_device_title,
        descriptionRes = R.string.privacy_device_description
    ),
    AI(
        emoji = "\u2728",
        titleRes = R.string.privacy_ai_title,
        descriptionRes = R.string.privacy_ai_description

    ),
    WELLBEING(
        emoji = "\uD83D\uDEE1\uFE0F",
        titleRes = R.string.privacy_wellbeing_title,
        descriptionRes = R.string.privacy_wellbeing_description
    )
}
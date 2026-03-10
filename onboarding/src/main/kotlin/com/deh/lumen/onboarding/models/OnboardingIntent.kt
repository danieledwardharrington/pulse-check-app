package com.deh.lumen.onboarding.models

import com.deh.lumen.onboarding.R

enum class OnboardingIntent(
    val intentTitleRes: Int,
    val intentDescriptionRes: Int,
    val isSelected: Boolean = false
) {
    AWARENESS(
        intentTitleRes = R.string.awareness_title,
        intentDescriptionRes = R.string.awareness_description,
    ),

    TRACK(
        intentTitleRes = R.string.track_title,
        intentDescriptionRes = R.string.track_description
    ),

    REFLECTION(
        intentTitleRes = R.string.reflection_title,
        intentDescriptionRes = R.string.reflection_description
    ),

    THERAPY(
        intentTitleRes = R.string.therapy_title,
        intentDescriptionRes = R.string.therapy_description
    )
}
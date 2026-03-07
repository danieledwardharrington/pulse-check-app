package com.deh.lumen.onboarding.models

import com.deh.lumen.onboarding.R

enum class OnboardingFocus(
    val focusRes: Int,
    val focusEmoji: String,
    val isSelected: Boolean = false
) {
    WORK(
        focusRes = R.string.focus_work,
        focusEmoji = "\uD83D\uDCBC"
    ),

    RELATIONSHIPS(
        focusRes = R.string.focus_relationships,
        focusEmoji = "\uD83E\uDEF6"
    ),

    SLEEP(
        focusRes = R.string.focus_sleep,
        focusEmoji = "\uD83D\uDE34"
    ),

    HEALTH(
        focusRes = R.string.focus_health,
        focusEmoji = "\uD83C\uDFC3"
    ),

    SOCIAL(
        focusRes = R.string.focus_social,
        focusEmoji = "\uD83D\uDC65"
    ),

    FAMILY(
        focusRes = R.string.focus_family,
        focusEmoji = "\uD83E\uDDD1\u200D\uD83E\uDDD1\u200D\uD83E\uDDD2"
    ),

    FINANCES(
        focusRes = R.string.focus_finances,
        focusEmoji = "\uD83D\uDCB0"
    ),

    GROWTH(
        focusRes = R.string.focus_growth,
        focusEmoji = "\uD83C\uDF31"
    )
}
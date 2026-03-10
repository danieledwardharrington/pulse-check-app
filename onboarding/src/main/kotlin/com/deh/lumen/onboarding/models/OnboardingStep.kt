package com.deh.lumen.onboarding.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.deh.lumen.core_ui.R

sealed class OnboardingStep(
    @DrawableRes val logo: Int?,
    @StringRes val titleRes: Int,
    @StringRes val supertitleRes: Int?,
    @StringRes val descriptionRes: Int?,
    @StringRes val firstButtonTextRes: Int,
    @StringRes val secondButtonTextRes: Int?,
    val progress: Float
) {
    data class Welcome(
        val appLogo: Int? = R.drawable.lumen_logo,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_one_title,
        val supertitle: Int? = null,
        val description: Int? = com.deh.lumen.onboarding.R.string.step_one_description,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_get_started,
        val secondButtonText: Int? = com.deh.lumen.onboarding.R.string.button_have_account
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 1 / 7f
    )

    data class Name(
        val appLogo: Int? = null,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_two_title,
        val supertitle: Int? = com.deh.lumen.onboarding.R.string.step_two_supertitle,
        val description: Int? = com.deh.lumen.onboarding.R.string.step_two_description,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_continue,
        val secondButtonText: Int? = null
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 2 / 7f
    )

    data class Intent(
        val appLogo: Int? = null,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_three_title,
        val supertitle: Int? = com.deh.lumen.onboarding.R.string.step_three_supertitle,
        val description: Int? = null,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_continue,
        val secondButtonText: Int? = null
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 3 / 7f
    )

    data class Focus(
        val appLogo: Int? = null,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_four_title,
        val supertitle: Int? = com.deh.lumen.onboarding.R.string.step_four_supertitle,
        val description: Int? = com.deh.lumen.onboarding.R.string.step_four_description,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_continue,
        val secondButtonText: Int? = null
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 4 / 7f
    )

    data class Time(
        val appLogo: Int? = null,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_five_title,
        val supertitle: Int? = com.deh.lumen.onboarding.R.string.step_five_supertitle,
        val description: Int? = com.deh.lumen.onboarding.R.string.step_five_description,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_continue,
        val secondButtonText: Int? = null
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 5 / 7f
    )

    data class Privacy(
        val appLogo: Int? = null,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_six_title,
        val supertitle: Int? = com.deh.lumen.onboarding.R.string.step_six_supertitle,
        val description: Int? = null,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_sounds_good,
        val secondButtonText: Int? = null
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 6 / 7f
    )

    data class Complete(
        val appLogo: Int? = R.drawable.lumen_logo,
        val screenTitle: Int = com.deh.lumen.onboarding.R.string.step_seven_title,
        val supertitle: Int? = null,
        val description: Int? = com.deh.lumen.onboarding.R.string.step_seven_description,
        val firstButtonText: Int = com.deh.lumen.onboarding.R.string.button_begin_check_in,
        val secondButtonText: Int? = com.deh.lumen.onboarding.R.string.button_later
    ) : OnboardingStep(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText,
        progress = 1f
    )

}
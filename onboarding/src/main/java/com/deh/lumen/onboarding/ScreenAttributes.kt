package com.deh.lumen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class ScreenAttributes(
    @DrawableRes val logo: Int?,
    @StringRes val titleRes: Int,
    @StringRes val supertitleRes: Int?,
    @StringRes val descriptionRes: Int?,
    @StringRes val firstButtonTextRes: Int,
    @StringRes val secondButtonTextRes: Int?
) {
    data class Welcome(
        val appLogo: Int? = com.deh.lumen.core_ui.R.drawable.lumen_logo,
        val screenTitle: Int = R.string.step_one_title,
        val supertitle: Int? = null,
        val description: Int? = R.string.step_one_description,
        val firstButtonText: Int = R.string.button_get_started,
        val secondButtonText: Int? = R.string.button_have_account
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Name(
        val appLogo: Int? = null,
        val screenTitle: Int = R.string.step_two_title,
        val supertitle: Int? = R.string.step_two_supertitle,
        val description: Int? = R.string.step_two_description,
        val firstButtonText: Int = R.string.button_continue,
        val secondButtonText: Int? = null
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Intent(
        val appLogo: Int? = null,
        val screenTitle: Int = R.string.step_three_title,
        val supertitle: Int? = R.string.step_three_supertitle,
        val description: Int? = null,
        val firstButtonText: Int = R.string.button_continue,
        val secondButtonText: Int? = null
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Focus(
        val appLogo: Int? = null,
        val screenTitle: Int = R.string.step_four_title,
        val supertitle: Int? = R.string.step_four_supertitle,
        val description: Int? = R.string.step_four_description,
        val firstButtonText: Int = R.string.button_continue,
        val secondButtonText: Int? = null
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Time(
        val appLogo: Int? = null,
        val screenTitle: Int = R.string.step_five_title,
        val supertitle: Int? = R.string.step_five_supertitle,
        val description: Int? = R.string.step_five_description,
        val firstButtonText: Int = R.string.button_continue,
        val secondButtonText: Int? = null
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Privacy(
        val appLogo: Int? = null,
        val screenTitle: Int = R.string.step_six_title,
        val supertitle: Int? = R.string.step_six_supertitle,
        val description: Int? = null,
        val firstButtonText: Int = R.string.button_understand,
        val secondButtonText: Int? = null
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

    data class Complete(
        val appLogo: Int? = com.deh.lumen.core_ui.R.drawable.lumen_logo,
        val screenTitle: Int = R.string.step_seven_title,
        val supertitle: Int? = null,
        val description: Int? = R.string.step_seven_description,
        val firstButtonText: Int = R.string.button_begin_check_in,
        val secondButtonText: Int? = R.string.button_later
    ) : ScreenAttributes(
        logo = appLogo,
        titleRes = screenTitle,
        supertitleRes = supertitle,
        descriptionRes = description,
        firstButtonTextRes = firstButtonText,
        secondButtonTextRes = secondButtonText
    )

}

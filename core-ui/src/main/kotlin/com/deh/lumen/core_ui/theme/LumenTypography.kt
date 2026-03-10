package com.deh.lumen.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deh.lumen.core_ui.R

val FrauncesFamily = FontFamily(
    Font(R.font.fraunces_light,          FontWeight.Light,  FontStyle.Normal),
    Font(R.font.fraunces_light_italic,   FontWeight.Light,  FontStyle.Italic),
    Font(R.font.fraunces_regular,        FontWeight.Normal, FontStyle.Normal),
    Font(R.font.fraunces_regular_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.fraunces_medium,         FontWeight.Medium, FontStyle.Normal),
)

val DmSansFamily = FontFamily(
    Font(R.font.dm_sans_light,   FontWeight.Light,  FontStyle.Normal),
    Font(R.font.dm_sans_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.dm_sans_medium,  FontWeight.Medium, FontStyle.Normal),
)

val LumenTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),

    displayMedium = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),

    displaySmall = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Italic,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FrauncesFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Italic,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    titleSmall = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    labelLarge = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    labelMedium = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    labelSmall = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),
)

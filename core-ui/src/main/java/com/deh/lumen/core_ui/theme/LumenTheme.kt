package com.deh.lumen.core_ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

// ─────────────────────────────────────────────────────────────
// Color Scheme
//
// Lumen is a dark-first app. The dark scheme is the canonical
// experience. A light scheme is defined but not exposed in the
// UI — it exists for future consideration only.
// ─────────────────────────────────────────────────────────────

private val LumenDarkColorScheme = darkColorScheme(
    // Backgrounds
    background = LumenColor.Background,
    surface = LumenColor.Surface,
    surfaceVariant = LumenColor.SurfaceVariant,

    // Primary — used by M3 components (buttons, FABs, etc.)
    primary = LumenColor.AccentViolet,
    onPrimary = LumenColor.OnPrimary,
    primaryContainer = LumenColor.AccentVioletSubtle,
    onPrimaryContainer = LumenColor.AccentViolet,

    // Secondary — rose accent
    secondary = LumenColor.AccentRose,
    onSecondary = LumenColor.OnPrimary,
    secondaryContainer = LumenColor.AccentRoseSubtle,
    onSecondaryContainer = LumenColor.AccentRose,

    // Tertiary — gold accent
    tertiary = LumenColor.AccentGold,
    onTertiary = LumenColor.OnPrimary,
    tertiaryContainer = LumenColor.AccentGoldSubtle,
    onTertiaryContainer = LumenColor.AccentGold,

    // Text
    onBackground = LumenColor.TextPrimary,
    onSurface = LumenColor.TextPrimary,
    onSurfaceVariant = LumenColor.TextMuted,

    // Error / destructive
    error = LumenColor.Destructive,
    onError = LumenColor.OnPrimary,
    errorContainer = LumenColor.DestructiveSubtle,
    onErrorContainer = LumenColor.Destructive,

    // Outline — borders and dividers
    outline = LumenColor.Border,
    outlineVariant = LumenColor.BorderFocused,
)

val LumenShapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),   // input fields, answer boxes
    small = RoundedCornerShape(14.dp),   // --radius-sm: cards, buttons, chips
    medium = RoundedCornerShape(20.dp),   // pill chips, month tabs
    large = RoundedCornerShape(24.dp),   // --radius: user card, insight hero, tip card
    extraLarge = RoundedCornerShape(44.dp),   // phone shell border radius (not used in app UI)
)

data class LumenExtendedColors(

    // Mood
    val moodGreat: Color,
    val moodGreatSubtle: Color,
    val moodGood: Color,
    val moodGoodSubtle: Color,
    val moodOkay: Color,
    val moodOkaySubtle: Color,
    val moodLow: Color,
    val moodLowSubtle: Color,
    val moodStruggling: Color,
    val moodStrugglingSubtle: Color,

    // Gradients — use with Brush.linearGradient()
    val gradientCta: Brush,
    val gradientTip: Brush,
    val gradientCardBorder: Brush,
    val gradientLogo: Brush,

    // Ambient glows — use as radial gradient fills on Box backgrounds
    val glowViolet: Color,
    val glowRose: Color,
    val glowGold: Color,
)

private val LumenDarkExtendedColors = LumenExtendedColors(
    moodGreat = LumenColor.MoodGreat,
    moodGreatSubtle = LumenColor.MoodGreatSubtle,
    moodGood = LumenColor.MoodGood,
    moodGoodSubtle = LumenColor.MoodGoodSubtle,
    moodOkay = LumenColor.MoodOkay,
    moodOkaySubtle = LumenColor.MoodOkaySubtle,
    moodLow = LumenColor.MoodLow,
    moodLowSubtle = LumenColor.MoodLowSubtle,
    moodStruggling = LumenColor.MoodStruggling,
    moodStrugglingSubtle = LumenColor.MoodStrugglingSubtle,

    gradientCta = Brush.linearGradient(
        colors = listOf(LumenColor.GradientCtaStart, LumenColor.GradientCtaEnd)
    ),
    gradientTip = Brush.linearGradient(
        colors = listOf(LumenColor.GradientTipStart, LumenColor.GradientTipEnd)
    ),
    gradientCardBorder = Brush.linearGradient(
        colors = listOf(LumenColor.GradientCardStart, LumenColor.GradientCardEnd)
    ),
    gradientLogo = Brush.linearGradient(
        colors = listOf(
            LumenColor.GradientLogoStart,
            LumenColor.GradientLogoMid,
            LumenColor.GradientLogoEnd
        )
    ),

    glowViolet = LumenColor.AccentVioletGlow,
    glowRose = LumenColor.AccentRoseGlow,
    glowGold = LumenColor.AccentGoldGlow,
)

data class LumenSpacing(
    val xs: Dp =  4.dp,
    val sm: Dp =  8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,

    // Screen horizontal padding
    val screenHorizontal: Dp = 24.dp,

    // Vertical gap between sections on a screen
    val sectionGap: Dp = 20.dp,

    // Card internal padding
    val cardPadding: Dp = 18.dp,
    val cardPaddingLarge: Dp = 20.dp,

    // Settings row internal padding
    val settingsRowPadding: Dp = 14.dp,

    // Bottom nav bar height (content area, excluding system insets)
    val navBarHeight: Dp = 56.dp,
)

val LocalLumenColors = staticCompositionLocalOf {
    LumenDarkExtendedColors
}

val LocalLumenSpacing = staticCompositionLocalOf {
    LumenSpacing()
}

@Composable
fun LumenTheme(
    // Lumen is dark-only for now
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LumenDarkColorScheme
    val extendedColors = LumenDarkExtendedColors

    CompositionLocalProvider(
        LocalLumenColors  provides extendedColors,
        LocalLumenSpacing provides LumenSpacing(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = LumenTypography,
            shapes = LumenShapes,
            content = content
        )
    }
}

object LumenTheme {

    val colors: LumenExtendedColors
        @Composable @ReadOnlyComposable
        get() = LocalLumenColors.current

    val spacing: LumenSpacing
        @Composable @ReadOnlyComposable
        get() = LocalLumenSpacing.current

    // Passthrough to MaterialTheme for convenience
    val colorScheme
        @Composable @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography
        @Composable @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes
        @Composable @ReadOnlyComposable
        get() = MaterialTheme.shapes
}

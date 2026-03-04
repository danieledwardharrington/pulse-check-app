package com.deh.lumen.core_ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Lumen color palette.
 */
object LumenColor {

    // ─── Backgrounds ────────────────────────────────────────
    /** Primary app background. */
    val Background = Color(0xFF0F0E17)

    /** First-level surface. Cards, bottom sheets, phone shell. */
    val Surface = Color(0xFF1A1927)

    /** Second-level surface. Nested cards, input fields, settings rows. */
    val SurfaceVariant = Color(0xFF242336)

    // ─── Borders & Dividers ─────────────────────────────────
    /** Subtle border used on cards, inputs, and dividers. */
    val Border = Color(0x12FFFFFF)   // rgba(255,255,255,0.07)

    /** Slightly more visible border for focused/active states. */
    val BorderFocused = Color(0x66FFFFFF)   // rgba(255,255,255,0.4)

    // ─── Text ───────────────────────────────────────────────
    /** Primary text. Warm near-white to avoid harsh contrast. */
    val TextPrimary = Color(0xFFFFFFFE)

    /** Secondary / muted text. Labels, sublabels, placeholders. */
    val TextMuted = Color(0xFFA7A5B8)

    /** Placeholder text in input fields. */
    val TextPlaceholder = Color(0x66A7A5B8)   // TextMuted at ~40% opacity

    // ─── Accent — Rose ──────────────────────────────────────
    /** Primary accent. Dusty rose. Used on CTAs, active nav, highlights. */
    val AccentRose = Color(0xFFE8B4B8)

    /** Rose at low opacity — used for glow effects and tinted backgrounds. */
    val AccentRoseSubtle = Color(0x1FE8B4B8)   // ~12% opacity
    val AccentRoseDim = Color(0x33E8B4B8)   // ~20% opacity
    val AccentRoseGlow = Color(0x26E8B4B8)   // ~15% opacity — ambient background glow

    // ─── Accent — Violet ────────────────────────────────────
    /** Secondary accent. Muted violet. Gradients, AI elements, insight cards. */
    val AccentViolet = Color(0xFF9B8EC4)

    val AccentVioletSubtle = Color(0x1F9B8EC4)   // ~12% opacity
    val AccentVioletDim = Color(0x339B8EC4)   // ~20% opacity
    val AccentVioletGlow = Color(0x1E9B8EC4)   // ~12% opacity — ambient background glow

    // ─── Accent — Gold ──────────────────────────────────────
    /** Tertiary accent. Warm gold. Streaks, stats, tip/support card. */
    val AccentGold = Color(0xFFF4D06F)

    val AccentGoldSubtle = Color(0x1FF4D06F)   // ~12% opacity
    val AccentGoldDim = Color(0x33F4D06F)   // ~20% opacity
    val AccentGoldGlow = Color(0x1AF4D06F)   // ~10% opacity

    // ─── Mood Colors ────────────────────────────────────────
    // Each mood level has a base color and a subtle tinted
    // background used for the orb, calendar dot, and pill.

    /** Mood: Great — warm gold */
    val MoodGreat = Color(0xFFF4D06F)
    val MoodGreatSubtle = Color(0x2EF4D06F)   // ~18% opacity

    /** Mood: Good — soft sage green */
    val MoodGood = Color(0xFF9BC8B4)
    val MoodGoodSubtle = Color(0x2E9BC8B4)

    /** Mood: Okay — muted gray-lavender */
    val MoodOkay = Color(0xFFA7A5B8)
    val MoodOkaySubtle = Color(0x1FA7A5B8)

    /** Mood: Low — muted violet */
    val MoodLow = Color(0xFF9B8EC4)
    val MoodLowSubtle = Color(0x2E9B8EC4)

    /** Mood: Struggling — dusty rose */
    val MoodStruggling = Color(0xFFE8B4B8)
    val MoodStrugglingSubtle = Color(0x2EE8B4B8)

    // ─── Semantic UI ────────────────────────────────────────
    /** Destructive actions — delete data button. Muted rose, not aggressive red. */
    val Destructive = Color(0xFFE8B4B8)
    val DestructiveSubtle = Color(0x26E8B4B8)

    /** On-primary text — used on gradient CTA buttons where bg is light. */
    val OnPrimary = Color(0xFF0F0E17)

    // ─── Gradients (as start/end pairs) ─────────────────────
    // Used with Brush.linearGradient() in Compose.

    /** Primary CTA button */
    val ButtonCta = Color(0xFFE8B4B8)

    /** Top border accent on cards — same gradient, lower opacity applied in composable */
    val GradientCardStart = Color(0xFF9B8EC4)
    val GradientCardEnd = Color(0xFFE8B4B8)

    /** Tip/support card gradient — gold to rose */
    val GradientTipStart = Color(0xFFF4D06F)
    val GradientTipEnd = Color(0xFFE8B4B8)

    /** Wordmark/logo gradient — violet to white to rose */
    val GradientLogoStart = Color(0xFF9B8EC4)
    val GradientLogoMid = Color(0xFFFFFFFE)
    val GradientLogoEnd = Color(0xFFE8B4B8)

    // ─── Pure ───────────────────────────────────────────────
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Transparent = Color(0x00000000)
}

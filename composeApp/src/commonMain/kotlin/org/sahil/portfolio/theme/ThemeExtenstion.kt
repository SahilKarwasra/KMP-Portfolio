package org.sahil.portfolio.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val warning: Color,
    val onWarning: Color,
    val info: Color,
    val onInfo: Color,
    val muted: Color,
    val mutedForeground: Color,
    val accent: Color,
    val accentForeground: Color,
    val popover: Color,
    val popoverForeground: Color,
    val border: Color,
    val input: Color,
    val ring: Color
)

val LightExtendedColors = ExtendedColors(
    success = SemanticColors.success,
    onSuccess = Color.White,
    warning = SemanticColors.warning,
    onWarning = Color.White,
    info = SemanticColors.info,
    onInfo = Color.White,
    muted = LightColors.muted,
    mutedForeground = LightColors.mutedForeground,
    accent = LightColors.accent,
    accentForeground = LightColors.accentForeground,
    popover = LightColors.popover,
    popoverForeground = LightColors.popoverForeground,
    border = LightColors.border,
    input = LightColors.input,
    ring = LightColors.ring
)

val DarkExtendedColors = ExtendedColors(
    success = SemanticColors.success,
    onSuccess = Color.Black,
    warning = SemanticColors.warning,
    onWarning = Color.Black,
    info = SemanticColors.info,
    onInfo = Color.White,
    muted = DarkColors.muted,
    mutedForeground = DarkColors.mutedForeground,
    accent = DarkColors.accent,
    accentForeground = DarkColors.accentForeground,
    popover = DarkColors.popover,
    popoverForeground = DarkColors.popoverForeground,
    border = DarkColors.border,
    input = DarkColors.input,
    ring = DarkColors.ring
)

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current

object AppColors {
    val current: ExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.extendedColors
}
package org.sahil.portfolio.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColorScheme = lightColorScheme(
    primary = LightColors.primary,
    onPrimary = LightColors.primaryForeground,
    primaryContainer = LightColors.secondary,
    onPrimaryContainer = LightColors.secondaryForeground,
    secondary = LightColors.accent,
    onSecondary = LightColors.accentForeground,
    secondaryContainer = LightColors.muted,
    onSecondaryContainer = LightColors.mutedForeground,
    tertiary = LightColors.accent,
    onTertiary = LightColors.accentForeground,
    error = LightColors.destructive,
    onError = LightColors.destructiveForeground,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = LightColors.background,
    onBackground = LightColors.foreground,
    surface = LightColors.card,
    onSurface = LightColors.cardForeground,
    surfaceVariant = SurfaceColors.surfaceVariantLight,
    onSurfaceVariant = LightColors.mutedForeground,
    outline = LightColors.border,
    outlineVariant = LightColors.input,
    scrim = Color(0xFF000000),
    inverseSurface = DarkColors.background,
    inverseOnSurface = DarkColors.foreground,
    inversePrimary = DarkColors.primary,
    surfaceTint = LightColors.primary
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkColors.primary,
    onPrimary = DarkColors.primaryForeground,
    primaryContainer = DarkColors.secondary,
    onPrimaryContainer = DarkColors.secondaryForeground,
    secondary = DarkColors.accent,
    onSecondary = DarkColors.accentForeground,
    secondaryContainer = DarkColors.muted,
    onSecondaryContainer = DarkColors.mutedForeground,
    tertiary = DarkColors.accent,
    onTertiary = DarkColors.accentForeground,
    error = DarkColors.destructive,
    onError = DarkColors.destructiveForeground,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = DarkColors.background,
    onBackground = DarkColors.foreground,
    surface = DarkColors.card,
    onSurface = DarkColors.cardForeground,
    surfaceVariant = SurfaceColors.surfaceVariantDark,
    onSurfaceVariant = DarkColors.mutedForeground,
    outline = DarkColors.border,
    outlineVariant = DarkColors.input,
    scrim = Color(0xFF000000),
    inverseSurface = LightColors.background,
    inverseOnSurface = LightColors.foreground,
    inversePrimary = LightColors.primary,
    surfaceTint = DarkColors.primary
)

// Typography
val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}

@Composable
fun AppThemePreviewLight(
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = false, content = content)
}

@Composable
fun AppThemePreviewDark(
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme = true, content = content)
}
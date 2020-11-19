package com.codingwithmitch.openchat.framework.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightThemeColors = lightColors(
        primary = Blue600,
        primaryVariant = Blue400,
        onPrimary = Black5,
        secondary = Orange700,
        secondaryVariant = Orange400,
        onSecondary = Color.Black,
        error = RedErrorDark,
        onError = RedErrorLight,
        background = Blue600,
        onBackground = Color.White,
        surface = Color.White,
        onSurface = Black5,

)

private val DarkThemeColors = darkColors(
        primary = Blue700,
        primaryVariant = Color.White,
        onPrimary = Color.White,
        secondary = Orange300,
        onSecondary = Orange700,
        error = RedErrorLight,
        background = Color.Black,
        onBackground = Color.White,
        surface = Black3,
        onSurface = Color.White,
)

@Composable
fun AppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
) {
    MaterialTheme(
            colors = if (darkTheme) DarkThemeColors else LightThemeColors,
            typography = RobotoTypography,
            shapes = AppShapes,
            content = content
    )
}

































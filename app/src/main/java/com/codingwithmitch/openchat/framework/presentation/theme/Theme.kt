package com.codingwithmitch.openchat.framework.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightThemeColors = lightColors(
        primary = Blue600,
        primaryVariant = Blue400,
        onPrimary = Color.White,
        secondary = Orange700,
        secondaryVariant = Orange400,
        onSecondary = Color.Black,
        error = RedErrorDark,
        onError = RedErrorLight,
        background = Blue600,
        onBackground = Color.White,
        surface = Color.White,
        onSurface = GreyDark6,

)

private val DarkThemeColors = darkColors(
        primary = Blue300,
        primaryVariant = Blue700,
        onPrimary = Color.Black,
        secondary = Orange300,
        onSecondary = Orange700,
        error = RedErrorLight,
        background = Color.Black,
        onBackground = Color.Gray,
)

@Composable
fun AppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
) {
    MaterialTheme(
            colors = if (darkTheme) DarkThemeColors else LightThemeColors,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
    )
}

































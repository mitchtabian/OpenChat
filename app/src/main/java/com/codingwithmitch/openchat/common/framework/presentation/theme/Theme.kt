package com.codingwithmitch.openchat.common.framework.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codingwithmitch.openchat.common.framework.presentation.components.CircularIndeterminateProgressBar
import com.codingwithmitch.openchat.splash.framework.presentation.SplashScreen


private val LightThemeColors = lightColors(
        primary = Blue600,
        primaryVariant = Blue400,
        onPrimary = Black5,
        secondary = Orange800,
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
        onSecondary = Orange800,
        error = RedErrorLight,
        background = Color.Black,
        onBackground = Color.White,
        surface = Black3,
        onSurface = Color.White,
)

@Composable
fun AppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        progressBarIsDisplayed: Boolean = false,
        content: @Composable () -> Unit
) {
    MaterialTheme(
            colors = if (darkTheme) DarkThemeColors else LightThemeColors,
            typography = RobotoTypography,
            shapes = AppShapes
    ){
            Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                    content()
                    CircularIndeterminateProgressBar(isDisplayed = progressBarIsDisplayed, 0.2f)
            }
    }
}

































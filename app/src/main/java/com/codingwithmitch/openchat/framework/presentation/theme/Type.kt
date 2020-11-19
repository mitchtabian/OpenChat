package com.codingwithmitch.openchat.framework.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.codingwithmitch.openchat.R

private val Ubuntu = fontFamily(
        font(R.font.ubuntu_regular),
        font(R.font.ubuntu_medium, FontWeight.W500),
        font(R.font.ubuntu_bold, FontWeight.W600)
)


val AppTypography = Typography(
        h1 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 30.sp,
        ),
        h2 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
        ),
        h3 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
        ),
        h4 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
        ),
        h5 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
        ),
        h6 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
        ),
        subtitle1 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
        ),
        subtitle2 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
        ),
        body1 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        body2 = TextStyle(
                fontFamily = Ubuntu,
                fontSize = 14.sp
        ),
        button = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = Color.White
        ),
        caption = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        ),
        overline = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp
        )
)
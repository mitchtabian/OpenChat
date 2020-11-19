package com.codingwithmitch.openchat.framework.presentation.theme

import androidx.compose.material.Typography
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
                color = GreyDark6,
        ),
        h2 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                color = GreyDark6,
        ),
        h3 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = GreyDark6,
        ),
        h4 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                color = GreyDark6,
        ),
        h5 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = GreyDark6,
        ),
        h6 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = GreyDark6,
        ),
        subtitle1 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = GreyDark6,
        ),
        subtitle2 = TextStyle(
                fontFamily = Ubuntu,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = GreyDark6,
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
                fontSize = 14.sp
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
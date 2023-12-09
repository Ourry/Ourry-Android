package com.worldonetop.ourry.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.worldonetop.ourry.R

val DefaultFont = FontFamily(
    Font(R.font.saira_thin, FontWeight.W100),
    Font(R.font.saira_extra_light, FontWeight.W200),
    Font(R.font.saira_light, FontWeight.W300),
    Font(R.font.saira_regular, FontWeight.W400),
    Font(R.font.saira_medium, FontWeight.W500),
    Font(R.font.saira_semi_bold, FontWeight.W600),
    Font(R.font.saira_bold, FontWeight.W700),
    Font(R.font.saira_extra_bold, FontWeight.W800),
    Font(R.font.saira_black, FontWeight.W900),
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
package com.worldonetop.ourry.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val DefaultTextStyle = TextStyle(
    fontFamily = DefaultFont,
    fontSize = 16.sp,
    color = Color.Black,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
)


val TitleTextStyle = DefaultTextStyle.copy(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = (36*1.1).sp
)

val BodyTextStyle = DefaultTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
)

val PlaceholderTextStyle = BodyTextStyle.copy(
    color = Gray30
)

val ButtonTextStyle = BodyTextStyle.copy(
    color = BackgroundPrimaryColor,
    fontWeight = FontWeight.Bold,
)

val ErrorTextStyle = DefaultTextStyle.copy(
    color = ErrorColor,
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
)

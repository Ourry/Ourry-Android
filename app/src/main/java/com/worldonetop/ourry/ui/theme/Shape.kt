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
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    lineHeight = (16*1.1).sp
)


val TitleTextStyle = DefaultTextStyle.copy(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = (36*1.1).sp
)

val LargeTextStyle = DefaultTextStyle.copy(
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = (18*1.1).sp
)

val BodyTextStyle = DefaultTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
)

val SmallTextStyle = DefaultTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = (14*1.1).sp
)

val TinyTextStyle = DefaultTextStyle.copy(
    fontWeight = FontWeight.Light,
    fontSize = 11.sp,
    lineHeight = (11*1.1).sp
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
    lineHeight = (12*1.1).sp
)

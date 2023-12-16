package com.worldonetop.ourry.ui.component.appbar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.worldonetop.ourry.ui.theme.BodyTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OnlyTextAppBar(
    title: String = "회원가입"
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                style = BodyTextStyle,
                fontSize = 20.sp,
                text = title
            )
        }
    )
}
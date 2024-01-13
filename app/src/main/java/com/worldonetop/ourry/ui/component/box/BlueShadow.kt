package com.worldonetop.ourry.ui.component.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.ui.theme.BackgroundPrimaryColor
import com.worldonetop.ourry.ui.theme.Primary20
import com.worldonetop.ourry.ui.theme.Primary80
import com.worldonetop.ourry.ui.theme.default_horizontal_padding

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun BlueShadow(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(size = 8.dp),
                ambientColor = Primary80,
                spotColor = Color.Blue,
            )
            .background(BackgroundPrimaryColor),
        content = content
    )
}
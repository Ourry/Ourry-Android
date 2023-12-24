package com.worldonetop.ourry.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.ui.theme.BackgroundPrimaryColor
import com.worldonetop.ourry.ui.theme.ButtonTextStyle
import com.worldonetop.ourry.ui.theme.Gray10
import com.worldonetop.ourry.ui.theme.Gray30
import com.worldonetop.ourry.ui.theme.Gray80
import com.worldonetop.ourry.ui.theme.Primary60
import com.worldonetop.ourry.util.extension.conditional



@Preview
@Composable
private fun BorderButtonPreview(){
    Column {
        BorderButton(text = "asdf",onClick = { },enabled = true,showBorder = true)
        Spacer(modifier = Modifier.height(10.dp))
        BorderButton(text = "asdf",onClick = { },enabled = true,showBorder = false)
        Spacer(modifier = Modifier.height(10.dp))
        BorderButton(text = "asdf",onClick = { },enabled = false,showBorder = true)
        Spacer(modifier = Modifier.height(10.dp))
        BorderButton(text = "asdf",onClick = { },enabled = false,showBorder = false)
    }
}
@Composable
fun BorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: ()->Unit,
    enabled: Boolean,
    showBorder:Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .conditional(enabled){
                clickable { onClick() }
            }
            .clip(RoundedCornerShape(size = 8.dp))
            .background(BackgroundPrimaryColor)
            .conditional(showBorder) {
                border(
                    2.dp,
                    if(enabled) Primary60 else Gray30,
                    RoundedCornerShape(size = 8.dp)
                )
            }
            .padding(8.dp)
    ){
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = ButtonTextStyle,
            color = if(!enabled)
                Gray30
            else if(showBorder)
                Primary60
            else
                Gray80
        )
    }
}
package com.worldonetop.ourry.ui.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.ui.theme.BackgroundPrimaryColor
import com.worldonetop.ourry.ui.theme.BodyTextStyle


/** content padding 설정 가능한 TextField **/
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = BodyTextStyle,
    singleLine: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(5.dp),
    shape: Shape = RoundedCornerShape(size = 8.dp),
    fillColor: Color = BackgroundPrimaryColor,
    borderColor: Color = Color.Transparent,
    placeholder: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        singleLine = singleLine,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .clip(shape)
                    .background(fillColor)
                    .border(
                        1.dp,
                        borderColor,
                        shape = shape,
                    )
                    .padding(contentPadding),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leading?.invoke()
                    Box(modifier = Modifier.weight(1f)) {
                        placeholder?.let {
                            if (value.isEmpty())
                                it()
                        }
                        innerTextField()
                    }
                    trailing?.invoke()
                }
            }
        }
    )
}
package com.worldonetop.ourry.ui.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.input.InputField
import com.worldonetop.ourry.ui.theme.BodyTextStyle
import com.worldonetop.ourry.ui.theme.ErrorTextStyle
import com.worldonetop.ourry.ui.theme.PlaceholderTextStyle

@Preview
@Composable
private fun SignupInfoPreview() {
    SignupInfoView(state = SignupUiState(), onName = {}, onPhoneNumber = {})
}

@Composable
fun SignupInfoView(
    modifier:Modifier = Modifier,
    state: SignupUiState,
    onName: (String) -> Unit,
    onPhoneNumber: (String) -> Unit,
) {

    Column(
        modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.nickname),
            style = BodyTextStyle
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            value = state.name,
            onValueChange = onName,
            contentPadding = PaddingValues(10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.nickname_placeholder),
                    style = PlaceholderTextStyle,
                )
            },
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = if(state.showNameError)
                stringResource(id = R.string.nickname_error)
            else
                "",
            style = ErrorTextStyle
        )

        Text(
            text = stringResource(id = R.string.phone_number),
            style = BodyTextStyle
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputField(
            value = state.phoneNumber,
            onValueChange = onPhoneNumber,
            contentPadding = PaddingValues(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.phone_number_placeholder),
                    style = PlaceholderTextStyle,
                )
            },
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = if(state.showPhoneNumberError)
                stringResource(id = R.string.phone_number_error)
            else
                "",
            style = ErrorTextStyle
        )
    }
}
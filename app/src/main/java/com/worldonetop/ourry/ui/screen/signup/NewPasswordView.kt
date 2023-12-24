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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.input.InputField
import com.worldonetop.ourry.ui.theme.BodyTextStyle
import com.worldonetop.ourry.ui.theme.ErrorTextStyle

@Preview
@Composable
private fun NewPasswordPreview() {
    NewPasswordView(state = SignupUiState(), onPw = {}, onPwCheck = {})
}

@Composable
fun NewPasswordView(
    modifier:Modifier = Modifier,
    state: SignupUiState,
    onPw: (String) -> Unit,
    onPwCheck: (String) -> Unit,
) {
    Column(
        modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.new_pw),
            style = BodyTextStyle
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        InputField(
            value = state.pw,
            onValueChange = onPw,
            contentPadding = PaddingValues(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation('*'),
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = if(state.showPwError)
                stringResource(id = R.string.pw_error)
            else
                "",
            style = ErrorTextStyle
        )

        Text(
            text = stringResource(id = R.string.new_pw_check),
            style = BodyTextStyle
        )

        Spacer(modifier = Modifier.height(12.dp))

        InputField(
            value = state.pwCheck,
            onValueChange = onPwCheck,
            contentPadding = PaddingValues(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation('*'),
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = if(state.showPwCheckError)
                stringResource(id = R.string.pw_check_error)
            else
                "",
            style = ErrorTextStyle
        )
    }
}
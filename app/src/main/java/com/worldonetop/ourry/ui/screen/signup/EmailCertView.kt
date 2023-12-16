package com.worldonetop.ourry.ui.screen.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.input.InputField
import com.worldonetop.ourry.ui.theme.ButtonTextStyle
import com.worldonetop.ourry.ui.theme.ErrorTextStyle
import com.worldonetop.ourry.ui.theme.Gray30
import com.worldonetop.ourry.ui.theme.PlaceholderTextStyle
import com.worldonetop.ourry.ui.theme.Primary40
import com.worldonetop.ourry.ui.theme.TitleTextStyle
import com.worldonetop.ourry.util.extension.conditional


@Preview
@Composable
private fun EmailCertPreview() {
    EmailCertView(
        state = SignupUiState(),
        onEmail = {},
        onCert = {},
        emailOnClick = {},
        certOnClick = {}
    )
}

@Composable
fun EmailCertView(
    modifier: Modifier = Modifier,
    state: SignupUiState,
    onEmail: (String) -> Unit,
    onCert: (String) -> Unit,
    emailOnClick: () -> Unit,
    certOnClick: () -> Unit,
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.email_cert_title),
            style = TitleTextStyle
        )

        Spacer(modifier = Modifier.height(16.dp))

        EmailCertSection(
            keyboardType = KeyboardType.Email,
            value = state.email,
            onValue = onEmail,
            placeholder = stringResource(id = R.string.email_cert_placeholder),
            errorString = stringResource(id = R.string.email_error),
            buttonEnabled = state.emailSendEnabled,
            textfieldEnabled = state.emailInputEnabled,
            showError = state.showEmailError,
            onClick = emailOnClick,
            timeLeft = state.emailRefreshTimeLeft,
            buttonText = stringResource(id = R.string.email_cert_send)
        )

        if (state.showEmailCert) {
            Spacer(modifier = Modifier.height(24.dp))

            EmailCertSection(
                keyboardType = KeyboardType.Number,
                value = state.emailCert,
                onValue = onCert,
                placeholder = stringResource(id = R.string.email_cert_check_placeholder),
                errorString = stringResource(id = R.string.email_cert_check_error),
                buttonEnabled = state.emailCert.isNotEmpty(),
                textfieldEnabled = true,
                showError = state.showEmailCertError,
                onClick = certOnClick,
                buttonText = stringResource(id = R.string.email_cert_check)
            )
        }
    }
}

@Composable
private fun EmailCertSection(
    keyboardType: KeyboardType,
    value: String,
    onValue: (String) -> Unit,
    placeholder: String,
    errorString: String,
    buttonEnabled: Boolean,
    textfieldEnabled: Boolean,
    showError: Boolean,
    onClick: () -> Unit,
    timeLeft: Int = 0,
    buttonText:String,
) {

    InputField(
        value = value,
        onValueChange = onValue,
        enabled = textfieldEnabled,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = {
            Text(
                text = placeholder,
                style = PlaceholderTextStyle,
            )
        },
        trailing = {
            Text(
                text = if (timeLeft <= 0)
                    ""
                else
                    "%02d:%02d".format(timeLeft / 60, timeLeft % 60),
                style = ErrorTextStyle,
            )
        }
    )

    Spacer(modifier = Modifier.height(12.dp))

    EmailCertButton(buttonEnabled, onClick, buttonText)

    Text(
        modifier = Modifier.padding(top = 6.dp),
        text = if (showError) errorString else "",
        style = ErrorTextStyle
    )
}


@Composable
private fun EmailCertButton(
    enabled: Boolean,
    onClick: () -> Unit,
    text: String
) {

    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(size = 8.dp),
                color = if (enabled) Primary40 else Gray30
            )
            .fillMaxWidth()
            .conditional(enabled) {
                clickable { onClick() }
            }
            .padding(vertical = 12.dp),
    ) {
        Text(
            text = text,
            style = ButtonTextStyle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
package com.worldonetop.ourry.ui.screen.login

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.InputField
import com.worldonetop.ourry.ui.theme.BackgroundPrimary
import com.worldonetop.ourry.ui.theme.BackgroundSecondary
import com.worldonetop.ourry.ui.theme.BodyTextStyle
import com.worldonetop.ourry.ui.theme.Error
import com.worldonetop.ourry.ui.theme.Gray50
import com.worldonetop.ourry.ui.theme.Primary40
import com.worldonetop.ourry.ui.theme.Primary50


@Preview(showSystemUi = true)
@Composable
private fun LoginPreview(){
    LoginUI(
        rememberNavController(),
    )
}
@Composable
fun LoginScreen(
    navController: NavController
) {
    LoginUI(navController)
}

@Composable
private fun LoginUI(
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    var idError by remember { mutableStateOf(false) }

    var pw by remember { mutableStateOf("") }
    var pwError by remember { mutableStateOf(false) }

    var autoLogin by remember { mutableStateOf(true) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSecondary)
            .padding(horizontal = 38.dp, vertical = 35.dp),
    ) {
        Spacer(modifier = Modifier.weight(3f))

        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier.size(189.dp, 41.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        EmailInput(id, { id = it }, idError, { idError = it })

        PwInput(pw, { pw = it }, pwError, { pwError = it })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 38.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.set_auto_login),
                style = BodyTextStyle,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Checkbox(
                modifier = Modifier.size(20.dp),
                colors = CheckboxDefaults.colors(checkedColor = Primary50),
                checked = autoLogin,
                onCheckedChange = { autoLogin = it }
            )
        }

        Row {
            DefaultButton(stringResource(id = R.string.signup)){

            }
            Spacer(modifier = Modifier.width(12.dp))
            DefaultButton(stringResource(id = R.string.login)){
                if(id.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(id).matches())
                    idError = true
                if(pw.isEmpty())
                    pwError = true
                if(!idError && !pwError){

                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.reset_pw),
            style = BodyTextStyle,
            modifier = Modifier.clickable {

            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.anonymous_login),
            style = BodyTextStyle,
            modifier = Modifier.clickable {

            }
        )
    }
}

@Composable
private fun EmailInput(
    value: String,
    onValue: (String) -> Unit,
    error: Boolean,
    onError: (Boolean) -> Unit,
){
    InputField(
        value = value,
        onValueChange = {
            onValue(it)
            if(error && it.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(it).matches())
                onError(false)
        },
        contentPadding = PaddingValues(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        placeholder = {
            Text(
                text = stringResource(id = R.string.email_placeholder),
                style = BodyTextStyle,
                color = Gray50,
            )
        }
    )
    Text(
        text = if(error) stringResource(id = R.string.email_error_text) else "",
        style = BodyTextStyle,
        color = Error,
        fontSize = 12.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp, bottom = 18.dp)
    )
}


@Composable
private fun PwInput(
    value: String,
    onValue: (String) -> Unit,
    error: Boolean,
    onError: (Boolean) -> Unit,
){
    InputField(
        value = value,
        onValueChange = {
            onValue(it)
            if(error && it.isNotEmpty())
                onError(false)
        },
        contentPadding = PaddingValues(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation('*'),
        placeholder = {
            Text(
                text = stringResource(id = R.string.pw_placeholder),
                style = BodyTextStyle,
                color = Gray50,
            )
        }
    )
    Text(
        text = if(error) stringResource(id = R.string.pw_error_text) else "",
        style = BodyTextStyle,
        color = Error,
        fontSize = 12.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp, bottom = 8.dp)
    )
}

@Composable
private fun DefaultButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(100.dp, 55.dp)
            .background(
                color = Primary40,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BodyTextStyle,
            fontWeight = FontWeight.SemiBold,
            color = BackgroundPrimary,
        )
    }
}
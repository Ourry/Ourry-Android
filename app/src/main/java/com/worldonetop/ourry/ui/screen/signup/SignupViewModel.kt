package com.worldonetop.ourry.ui.screen.signup

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldonetop.ourry.util.extension.updateFlowFromCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject
import kotlin.random.Random


data class SignupUiState(
    // email 인증
    val email: String = "",
    val emailInputEnabled: Boolean = true,
    val emailRefreshTimeLeft: Int = 0,
    val showEmailError: Boolean = false,
    val emailSendEnabled: Boolean = false,

    val showEmailCert: Boolean = false,
    val emailCert: String = "",
    val showEmailCertError: Boolean = false,

    val isEmailScreenComplete: Boolean = false,

    // 비밀번호 설정
    val pw: String = "",
    val showPwError: Boolean = false,

    val pwCheck: String = "",
    val showPwCheckError: Boolean = false,

    val isPwScreenComplete: Boolean = false,

    // 추가정보
    val name: String = "",
    val showNameError: Boolean = false,

    val phoneNumber: String = "",
    val showPhoneNumberError: Boolean = false,

    val isLastComplete: Boolean = false,

    )

@HiltViewModel
class SignupViewModel @Inject constructor(
) : ViewModel() {
    private val viewModelState = MutableStateFlow(SignupUiState())

    val uiState = viewModelState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        viewModelState.value
    )

    private val emailPattern: Pattern = Patterns.EMAIL_ADDRESS
    private val pwRegex = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#\$%])[a-zA-Z\\d!@#\$%]{8,16}\$")
    private val nameRegex = Regex("^[a-zA-Z0-9가-힣]*\$")
    private val phoneNumberRegex = Regex("^010\\d{7,8}\$")

    fun setEmail(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            val emailValid = value.isNotEmpty() && emailPattern.matcher(value).matches()
            it.copy(
                email = value,
                emailSendEnabled = emailValid,
                showEmailError = value.isNotEmpty() && !emailValid
            )
        }
    }

    fun sendEmail() {
        updateFlowFromCoroutine(viewModelState) {
            it.copy(
                showEmailCert = true,
                emailInputEnabled = false,
                emailSendEnabled = false,
                showEmailError = false,
            )
        }
        viewModelScope.launch {
            var leftTime = 5 * 60
            while (leftTime > 0) {
                updateFlowFromCoroutine(viewModelState) {
                    it.copy(emailRefreshTimeLeft = leftTime--)
                }
                delay(1000L)
            }
            updateFlowFromCoroutine(viewModelState) {
                it.copy(emailInputEnabled = true)
            }
        }
    }

    fun setEmailCert(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            it.copy(
                emailCert = value,
            )
        }
    }

    fun sendEmailCert() {
        updateFlowFromCoroutine(viewModelState) {
            if (Random.nextBoolean()) {
                it.copy(
                    showEmailCertError = false,
                    isEmailScreenComplete = true,
                )
            } else {
                it.copy(
                    showEmailCertError = true,
                    isEmailScreenComplete = false,
                )
            }
        }
    }

    fun setPw(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            val pwValid = value.isNotEmpty() && pwRegex.matches(value)
            it.copy(
                pw = value,
                showPwError = value.isNotEmpty() && !pwValid,
                showPwCheckError = it.pwCheck.isNotEmpty() && value != it.pwCheck,
                isPwScreenComplete = pwValid && value == it.pwCheck,
            )
        }
    }

    fun setPwCheck(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            val pwValid = it.pw.isNotEmpty() && pwRegex.matches(it.pw)
            it.copy(
                pwCheck = value,
                showPwCheckError = value.isNotEmpty() && it.pw != value,
                isPwScreenComplete = pwValid && value == it.pw,
            )
        }
    }

    fun setName(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            val nameValid = value.isNotEmpty() && nameRegex.matches(value)
            val phoneValid =
                it.phoneNumber.isNotEmpty() && phoneNumberRegex.matches(it.phoneNumber)
            it.copy(
                name = value,
                showNameError = value.isNotEmpty() && !nameValid,
                isLastComplete = nameValid && phoneValid,
            )
        }
    }

    fun setPhonNumber(value: String) {
        updateFlowFromCoroutine(viewModelState) {
            val nameValid = it.name.isNotEmpty() && nameRegex.matches(it.name)
            val phoneValid = value.isNotEmpty() && phoneNumberRegex.matches(value)
            it.copy(
                phoneNumber = value,
                showPhoneNumberError = value.isNotEmpty() && !phoneValid,
                isLastComplete = nameValid && phoneValid,
            )
        }
    }

    fun sendComplete() {

    }
}
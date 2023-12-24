package com.worldonetop.ourry.ui.screen.signup

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.worldonetop.ourry.R
import com.worldonetop.ourry.ui.component.appbar.OnlyTextAppBar
import com.worldonetop.ourry.ui.component.button.BorderButton
import com.worldonetop.ourry.ui.theme.BackgroundSecondaryColor
import kotlinx.coroutines.launch


@Preview(showSystemUi = true)
@Composable
private fun SignupPreview(){
    SignupUI(
        rememberNavController(),
        SignupViewModel(),
        SignupUiState()
    )
}
@Composable
fun SignupScreen(
    navController: NavController,
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by signupViewModel.uiState.collectAsStateWithLifecycle()

    SignupUI(navController, signupViewModel, uiState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SignupUI(
    navController: NavController,
    viewModel: SignupViewModel,
    uiState: SignupUiState,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundSecondaryColor)
    ) {
        OnlyTextAppBar(stringResource(id = R.string.signup))

        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            Box(Modifier.padding(horizontal = 24.dp)) {
                when(page){
                    0 ->{
                        EmailCertView(
                            state = uiState,
                            onEmail = { viewModel.setEmail(it) },
                            onCert = { viewModel.setEmailCert(it) },
                            emailOnClick = { viewModel.sendEmail() },
                            certOnClick = { viewModel.sendEmailCert() }
                        )
                    }
                    1 ->{
                        NewPasswordView (
                            state = uiState,
                            onPw = { viewModel.setPw(it) },
                            onPwCheck = { viewModel.setPwCheck(it) }
                        )
                    }
                    2 ->{
                        SignupInfoView(
                            state = uiState,
                            onName = { viewModel.setName(it) },
                            onPhoneNumber = { viewModel.setPhonNumber(it) }
                        )
                    }
                }
            }
        }

        Column(Modifier.padding(horizontal = 24.dp)) {
            BorderButton(
                text = if(pagerState.currentPage < 2) stringResource(id = R.string.next_page)
                else stringResource(id = R.string.signup_complete),
                onClick = {
                    if(pagerState.currentPage < 2)
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage+1)
                        }
                    else{

                    }
                },
                enabled = when(pagerState.currentPage){
                    0 -> uiState.isEmailScreenComplete
                    1 -> uiState.isPwScreenComplete
                    else -> uiState.isLastComplete
                },
                showBorder = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            BorderButton(
                text = stringResource(id = R.string.back),
                onClick = {
                    if(pagerState.currentPage == 0){

                    }
                    else
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage-1)
                        }
                },
                enabled = true,
                showBorder = false
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }

}
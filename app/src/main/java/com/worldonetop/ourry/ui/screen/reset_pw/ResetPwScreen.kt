package com.worldonetop.ourry.ui.screen.reset_pw

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
import com.worldonetop.ourry.ui.screen.signup.EmailCertView
import com.worldonetop.ourry.ui.screen.signup.NewPasswordView
import com.worldonetop.ourry.ui.screen.signup.SignupInfoView
import com.worldonetop.ourry.ui.screen.signup.SignupUiState
import com.worldonetop.ourry.ui.screen.signup.SignupViewModel
import com.worldonetop.ourry.ui.theme.BackgroundSecondaryColor
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
private fun ResetPwPreview(){
    ResetPwUI(
        rememberNavController(),
        SignupViewModel(),
        SignupUiState(),
        { }
    )
}
@Composable
fun ResetPwScreen(
    navController: NavController,
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by signupViewModel.uiState.collectAsStateWithLifecycle()

    ResetPwUI(navController, signupViewModel, uiState) {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ResetPwUI(
    navController: NavController,
    viewModel: SignupViewModel,
    uiState: SignupUiState,
    onBack: ()->Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundSecondaryColor)
    ) {
        OnlyTextAppBar(stringResource(id = R.string.reset_pw))

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
                }
            }
        }

        Column(Modifier.padding(horizontal = 24.dp)) {
            BorderButton(
                text = if(pagerState.currentPage < 1) stringResource(id = R.string.next_page)
                else stringResource(id = R.string.reset_pw_complete),
                onClick = {
                    if(pagerState.currentPage < 1)
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
                        onBack()
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
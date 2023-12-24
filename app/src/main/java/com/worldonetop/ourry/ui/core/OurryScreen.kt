package com.worldonetop.ourry.ui.core

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.worldonetop.ourry.ui.screen.login.LoginScreen
import com.worldonetop.ourry.ui.screen.reset_pw.ResetPwScreen
import com.worldonetop.ourry.ui.screen.signup.SignupScreen


@Composable
fun OurryScreen(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface {
        NavHost(
            navController = navController,
            startDestination = Screens.Login.route,
        ) {
            composable(Screens.Login.route){
                LoginScreen(navController)
            }
            composable(Screens.Signup.route){
                SignupScreen(navController)
            }
            composable(Screens.ResetPassword.route){
                ResetPwScreen(navController)
            }
        }
    }
}
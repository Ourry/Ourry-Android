package com.worldonetop.ourry.ui.main


sealed class Screens(val route: String) {
    data object Login : Screens("Login")
    data object Signup : Screens("Signup")
    data object ResetPassword : Screens("ResetPassword")

    data object Home : Screens("Home")
    data object Setting : Screens("Setting")
    data object Notification : Screens("Notification")

    data object DetailQuestion : Screens("Notification")
    data object AddQuestion : Screens("Notification")
}
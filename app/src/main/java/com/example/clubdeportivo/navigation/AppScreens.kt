package com.example.clubdeportivo.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object MenuUserScreen: AppScreens("menu_user_screen")
    object MenuAdminScreen: AppScreens("menu_admin_screen")
    object RegisterAdminScreen: AppScreens("register_admin_screen")
    object PayFeeAdminScreen: AppScreens("pay_fee_admin_screen")
}
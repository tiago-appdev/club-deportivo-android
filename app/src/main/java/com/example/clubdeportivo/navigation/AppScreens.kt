package com.example.clubdeportivo.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object ForgotPasswordScreen: AppScreens("forgot_password_screen")
    object RegisterUserScreen: AppScreens("register_user_screen")
    object MenuUserScreen : AppScreens("menu_user_screen/{uid}") {
        fun createRoute(uid: String) = "menu_user_screen/$uid"
    }
    object MenuAdminScreen: AppScreens("menu_admin_screen")
    object RegisterAdminScreen: AppScreens("register_admin_screen")
    object PayFeeAdminScreen: AppScreens("pay_fee_admin_screen")
    object PayFeeUserScreen: AppScreens("pay_fee_user_screen")
    object ClientsDebtScreen: AppScreens("clients_debt_screen")
    object PrintCredentialsAdminScreen: AppScreens("print_credentials_admin_screen")
}
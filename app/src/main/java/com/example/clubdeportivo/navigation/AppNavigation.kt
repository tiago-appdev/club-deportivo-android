package com.example.clubdeportivo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clubdeportivo.screens.ClientsDebt
import com.example.clubdeportivo.screens.ForgotPasswordScreen
import com.example.clubdeportivo.screens.LoginScreen
import com.example.clubdeportivo.screens.MenuAdminScreen
import com.example.clubdeportivo.screens.MenuUserScreen
import com.example.clubdeportivo.screens.PayFeeAdmin
import com.example.clubdeportivo.screens.PrintCredentialsAdminScreen
import com.example.clubdeportivo.screens.RegisterAdmin
import com.example.clubdeportivo.screens.RegisterUser
import com.example.clubdeportivo.screens.SplashScreen

@Composable()
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController)
        }
        composable(route = AppScreens.RegisterUserScreen.route) {
            RegisterUser(navController)
        }
        composable(route = AppScreens.MenuUserScreen.route) {
            MenuUserScreen(navController)
        }
        composable(route = AppScreens.MenuAdminScreen.route) {
            MenuAdminScreen(navController)
        }
        composable(route = AppScreens.RegisterAdminScreen.route) {
            RegisterAdmin(navController)
        }
        composable(route = AppScreens.PayFeeAdminScreen.route) {
            PayFeeAdmin(navController)
        }
        composable(route = AppScreens.ClientsDebtScreen.route) {
            ClientsDebt(navController)
        }
        composable(route = AppScreens.PrintCredentialsAdminScreen.route) {
            PrintCredentialsAdminScreen(navController)
        }
    }
}


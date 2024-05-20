package com.example.clubdeportivo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clubdeportivo.screens.LoginScreen
import com.example.clubdeportivo.screens.MenuAdminScreen
import com.example.clubdeportivo.screens.MenuUserScreen
import com.example.clubdeportivo.screens.PayFeeAdmin
import com.example.clubdeportivo.screens.RegisterAdmin
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
    }
}


package com.example.clubdeportivo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.clubdeportivo.navigation.AppScreens


@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla Login")
        Button(onClick = {
            navController.navigate(AppScreens.MenuUserScreen.route)
        }) {
            Text(text = "User")
        }

        Button(onClick = {
            navController.navigate(AppScreens.MenuAdminScreen.route)
        }) {
            Text(text = "Admin")
        }

        Row {
            Button(onClick = {
                navController.navigate(AppScreens.ForgotPasswordScreen.route)
            }) {
                Text(text = "Olvidaste tu contraseña?")
            }

            Button(onClick = {
                navController.navigate(AppScreens.RegisterUserScreen.route)
            }) {
                Text(text = "Registrarse")
            }
        }
    }
    // navController.popBackStack()
}

//@Composable
//fun Login(navController: NavController) {
//
//}

package com.example.clubdeportivo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.clubdeportivo.navigation.AppScreens
import kotlinx.coroutines.delay


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
    }
    // navController.popBackStack()
}

//@Composable
//fun Login(navController: NavController) {
//
//}

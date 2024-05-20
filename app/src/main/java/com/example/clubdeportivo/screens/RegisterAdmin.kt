package com.example.clubdeportivo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.clubdeportivo.navigation.AppScreens

@Composable
fun RegisterAdmin(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla Register Admin")
        Button(onClick = {
            navController.navigate(AppScreens.MenuAdminScreen.route)
        }) {
            Text(text = "Atras!")
        }
    }
}

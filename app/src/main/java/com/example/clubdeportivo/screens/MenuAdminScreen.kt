package com.example.clubdeportivo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.clubdeportivo.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuAdminScreen(navController: NavController){
    Scaffold {
        AdminScreen(navController)
    }
}

@Composable
fun AdminScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla Admin")

        Button(onClick = {
            navController.navigate(AppScreens.RegisterAdminScreen.route)
        }) {
            Text(text = "Registrar Cliente")
        }
        Button(onClick = {
            navController.navigate(AppScreens.PayFeeAdminScreen.route)
        }) {
            Text(text = "Pagar Cuota")
        }
        Button(onClick = {
            navController.navigate(AppScreens.ClientsDebtScreen.route)
        }) {
            Text(text = "Clientes Deudores")
        }
        Button(onClick = {
            navController.navigate(AppScreens.PrintCredentialsAdminScreen.route)
        }) {
            Text(text = "Imprimir Credenciales")
        }
        Button(onClick = {
            navController.navigate(AppScreens.LoginScreen.route)
        }) {
            Text(text = "Atras!")
        }
    }
}

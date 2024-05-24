package com.example.clubdeportivo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuAdminScreen(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Menu",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                    )
            },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.fitness),
                            modifier = Modifier.width(36.dp),
                            contentDescription = "Fitness Logo",
                            tint = Color.Red
                        )
                    }
                })
        } // TopBar
    ) {innerPadding ->
        AdminScreen(navController, innerPadding)
    } // Scalffold
} // MenuAdminScreen

@Composable
fun AdminScreen(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       MenuItems(
           icon = painterResource(id = R.drawable.person),
           text = "Registrar Cliente",
           onClick = {
               navController.navigate(AppScreens.RegisterAdminScreen.route)
           }
           )
        Spacer(modifier = Modifier.height(32.dp))

        MenuItems(
            icon = painterResource(id = R.drawable.attach_money),
            text = "Pagar Cuota",
            onClick = {
                navController.navigate(AppScreens.PayFeeAdminScreen.route)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        MenuItems(
            icon = painterResource(id = R.drawable.list),
            text = "Clientes Deudores",
            onClick = {
                navController.navigate(AppScreens.ClientsDebtScreen.route)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        MenuItems(
            icon = painterResource(id = R.drawable.print),
            text = "Imprimir Credenciales",
            onClick = {
                navController.navigate(AppScreens.PrintCredentialsAdminScreen.route)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                            navController.navigate(AppScreens.LoginScreen.route)
                          },
                modifier = Modifier
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Salir",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    } // Column

} // AdminScreen

@Composable
fun MenuItems(icon: Painter, text: String, onClick: () -> Unit){
    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier
                .size(50.dp),
            tint = Color(0xFFDD4B39)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
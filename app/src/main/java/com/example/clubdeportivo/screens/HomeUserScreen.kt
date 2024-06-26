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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuUserScreen(navController: NavController){
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
                            tint = Color(0xFFF14D56)
                        )
                    }
                })
        } // TopBar
    ) {innerPadding ->
        HomeBodyContent(navController, innerPadding)
    }
}

@Composable
fun HomeBodyContent(navController: NavController, innerPadding: PaddingValues){
    val auth = FirebaseAuth.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItemsUser(
            icon = painterResource(id = R.drawable.person),
            title = "Número de Cliente",
            text = "ABC123",
        ){}
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.attach_money),
            title = "Vencimiento de cuota",
            text = "15 JUN, 2024",
        ){}
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.list),
            title = "Actividades",
            text = "Yoga",
        ){}
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.print),
            title = "Ver / Imprimir credencial",
            text = "Ver credencial",
        ){}
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    auth.signOut()
                    navController.popBackStack( AppScreens.LoginScreen.route, inclusive = false, saveState = false)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.onSurface)
            ) {
                Text(
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    text = "Salir"
                )
            }
        }

    }
}


@Composable
fun MenuItemsUser(icon: Painter,title: String, text: String, onClick: () -> Unit){

    Row(
        modifier = Modifier
            .width(450.dp)
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width( 250.dp)
                .height(80.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(top = 5.dp),
                text = title,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                color = MaterialTheme.colorScheme.outline,
                text = text,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier
                .size(70.dp),
            tint = Color(0xFFF14D56)
        )
    }


}
package com.example.clubdeportivo.screens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.clubdeportivo.utils.formatDateToLocale
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuUserScreen(navController: NavController, uid: String) {
    var clientDni by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf(Date()) }

    LaunchedEffect(uid) {
        fetchUserData(uid) { dni, date ->
            Log.d("MenuUserScreen", "Fetched DNI: $dni, Fetched Date: $date")
            clientDni = dni
            dueDate = date
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Menu",
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
    ) { innerPadding ->
        HomeBodyContent(navController, innerPadding, clientDni, dueDate)
    }
}

@Composable
fun HomeBodyContent(navController: NavController, innerPadding: PaddingValues, clientDni: String, dueDate: Date) {
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
            text = clientDni,
        ) {}
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.attach_money),
            title = "Vencimiento de cuota",
            text = formatDateToLocale(dueDate, Locale("es", "ES")),
        ) {
            navController.navigate(AppScreens.PayFeeUserScreen.route)
        }
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.list),
            title = "Actividades",
            text = "Yoga",
        ) {}
        Spacer(modifier = Modifier.height(32.dp))

        MenuItemsUser(
            icon = painterResource(id = R.drawable.print),
            title = "Ver / Imprimir credencial",
            text = "Ver credencial",
        ) {}
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
                    navController.popBackStack(AppScreens.LoginScreen.route, inclusive = false, saveState = false)
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
fun MenuItemsUser(icon: Painter, title: String, text: String, onClick: () -> Unit) {
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
                .width(250.dp)
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

fun fetchUserData(uid: String, onComplete: (String, Date) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("users").document(uid)

    userRef.get().addOnSuccessListener { userDoc ->
        val clientDni = userDoc.getString("dni") ?: ""
        Log.d("MenuUserScreen", "Client DNI: $clientDni")  // Debug log for clientDni

        val feesRef = db.collection("fees").whereEqualTo("clientdni", clientDni)
            .orderBy("duedate", Query.Direction.DESCENDING)
            .limit(1)

        feesRef.get().addOnSuccessListener { feesDocs ->
            val dueDate = feesDocs.documents[0].getDate("duedate") ?: Date()
            Log.d("MenuUserScreen", "Due Date: $dueDate")
            onComplete(clientDni, dueDate)
        }.addOnFailureListener { e ->
            Log.e("MenuUserScreen", "Error fetching fees: ", e)
        }
    }.addOnFailureListener { e ->
        Log.e("MenuUserScreen", "Error fetching user data: ", e)
    }
}

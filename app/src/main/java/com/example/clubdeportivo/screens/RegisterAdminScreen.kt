package com.example.clubdeportivo.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.clubdeportivo.data.AppDB
import com.example.clubdeportivo.data.Contract
import com.example.clubdeportivo.data.UsersEntitie

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterAdmin(navController: NavController) {
    
    val nameData = remember{ mutableStateOf("") }
    val passwordData = remember{ mutableStateOf("") }
    val context = navController.context
    val db = Room.databaseBuilder(
        context,
        AppDB::class.java,
        Contract.DB.DB_NAME)
        .allowMainThreadQueries()
        .build()
    val users = db.userDao()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Register Admin") },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            )
        } //Top AppBar
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.width(300.dp),
                label = { Text(text = "Name")},
                value = nameData.value, 
                onValueChange = {
                    if (it.length <= 20) {
                        nameData.value = it
                    }
                } 
            )
            OutlinedTextField(
                modifier = Modifier.width(300.dp),
                label = { Text(text = "Password")},
                value = passwordData.value,
                onValueChange = {
                    if(it.length <= 8) {
                        passwordData.value = it
                    }
                }
            )

            OutlinedButton(
                onClick = {
                    val newUser = UsersEntitie(0,nameData.value, passwordData.value)
                    users.newUser(newUser)
                    Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier.width(300.dp),
                ) {
                Text(text = "Registrar")

            }

        } //Column

    } //Scaffold
}

package com.example.clubdeportivo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.clubdeportivo.data.AppDB
import com.example.clubdeportivo.data.Contract
import com.example.clubdeportivo.data.UsersEntitie
import com.example.clubdeportivo.navigation.AppScreens

@Composable
fun PrintCredentialsAdminScreen(navController: NavController) {
    var listUsers = mutableListOf<UsersEntitie>()
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDB::class.java,
        Contract.DB.DB_NAME
        )
        .allowMainThreadQueries()
        .build()
    listUsers = db.userDao().getAllUsers().toMutableList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(listUsers){ pos, user ->
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(start = 10.dp),
                    text = "Id: ${user.id}")
                Text(
                    modifier = Modifier.weight(0.3f),
                    text = "Name: ${user.name}")
                Text(
                    modifier = Modifier.weight(0.3f),
                    text = "Password: ${user.password}")
            }
        } //itemsIndexed

    } //LazyColumn

}
package com.example.clubdeportivo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.clubdeportivo.R
import com.example.clubdeportivo.data.AppDB
import com.example.clubdeportivo.data.Contract
import com.example.clubdeportivo.data.UsersEntitie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrintCredentialsAdminScreen(navController: NavController) {
    var listUsers by remember { mutableStateOf(mutableListOf<UsersEntitie>()) }
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDB::class.java,
        Contract.DB.DB_NAME
    )
        .allowMainThreadQueries()
        .build()
    listUsers = db.userDao().getAllUsers().toMutableList()

    var showImage by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Credencial",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(36.dp),
                            painter = painterResource(id = R.drawable.arrow_circle),
                            contentDescription = "Localized description",
                            tint = Color(0xFFF14D56)
                        )
                    }
                },

                scrollBehavior = scrollBehavior,
            )
        },

        ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_init),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .padding(16.dp)
            )

            // Numero de cliente input
            OutlinedTextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(text = "Numero de cliente") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Client Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Ver credencial button
            Button(
                onClick = {
                    showImage = !showImage
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(text = "Ver credencial")
            }

            if (showImage) {
                Image(
                    painter = painterResource(id = R.drawable.credential_placeholder_image),
                    contentDescription = "Image",
                    modifier = Modifier
                        .padding(16.dp)
                        .aspectRatio(2f)
                )
            }
        }
    }
}


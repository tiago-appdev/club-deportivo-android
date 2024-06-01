package com.example.clubdeportivo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "Print Credentials") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_circle),
                        contentDescription = "Back",
                        tint = Color(0xFFF14D56)
                    )
                }
            }
        )

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

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(listUsers) { pos, user ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = "Id: ${user.id}"
                    )
                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = "Name: ${user.name}"
                    )
                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = "Password: ${user.password}"
                    )
                }
            }
        }
    }
}



//@Composable
//fun ClientNumberField(
//    title: String,
//    value: String,
//    onChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    placeholder: String = "Ingresa el numero de cliente"
//){
//    val focusManager = LocalFocusManager.current
//    val leadingIcon = @Composable {
//        Icon(
//            Icons.Default.AccountBox,
//            contentDescription = "",
//            tint = Color(0xFFF14D56)
//        )
//    }
//    Text(
//        color = MaterialTheme.colorScheme.onBackground,
//        text = title,
//        modifier = Modifier.padding(start = 10.dp),
//        fontWeight = FontWeight.Bold,
//        fontSize = MaterialTheme.typography.titleMedium.fontSize)
//
//    TextField(
//        value = value,
//        onValueChange = onChange,
//        modifier = modifier,
//        leadingIcon = leadingIcon,
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//        keyboardActions = KeyboardActions(
//            onNext = {focusManager.moveFocus(FocusDirection.Down)}
//        ),
//        placeholder = {
//            Text(
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
//                text = placeholder) },
//        singleLine = true,
//        visualTransformation = VisualTransformation.None,
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
//            unfocusedContainerColor = MaterialTheme.colorScheme.surface
//        )
//    )
//}

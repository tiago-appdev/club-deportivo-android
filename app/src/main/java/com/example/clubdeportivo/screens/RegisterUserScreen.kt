package com.example.clubdeportivo.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.data.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUser(navController: NavController) {
    var user by remember { mutableStateOf(UserData()) }
    val context = navController.context
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.background)
            .padding(30.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Registrate",
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
                actions = {
                    IconButton(onClick = {
                        user = UserData()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(36.dp),
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "Reset Form",
                            tint = Color(0xFFF14D56)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }

        ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ){

            Spacer(modifier = Modifier.size(40.dp))

            NameUserField(
                title = "Nombre",
                modifier = Modifier.width(420.dp),
                value = user.name,
                onChange = {
                    user = user.copy(
                        name = it
                    )
                }
            )

            Spacer(modifier = Modifier.size(30.dp))

            SurNameUserField(
                title = "Apellido",
                modifier = Modifier.width(420.dp),
                value = user.surname,
                onChange = {
                    user = user.copy(
                        surname = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            PasswordUserField(
                title = "Contraseña",
                modifier = Modifier.width(420.dp),
                value = user.password,
                onChange = {
                    user = user.copy(
                        password = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            DNIUserField(
                title = "DNI",
                modifier = Modifier.width(420.dp),
                value = user.dni,
                onChange = {
                    user = user.copy(
                        dni = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            PhoneUserField(
                title = "Teléfono",
                modifier = Modifier.width(420.dp),
                value = user.phone,
                onChange = {
                    user = user.copy(
                        phone = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            EmailUserField(
                title = "Email",
                modifier = Modifier.width(420.dp),
                value = user.email,
                onChange = {
                    user = user.copy(
                        email = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(onClick = {
                    registerUser(navController, user)
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    user = UserData()
                    navController.popBackStack()
                },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        style = MaterialTheme.typography.titleMedium,
                        text = "Registrarse")
                }
            }

        } //Column

    } //Scaffold
}

@Composable
fun NameUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu nombre"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun SurNameUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu apellido"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun PasswordUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa una contraseña para tu cuenta"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Password,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun DNIUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu DNI"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.AccountBox,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun PhoneUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu teléfono"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.PhoneAndroid,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun EmailUserField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu email"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Email,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = title,
        modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {focusManager.moveFocus(FocusDirection.Down)}
        ),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}
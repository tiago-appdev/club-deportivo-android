package com.example.clubdeportivo.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterAdmin(navController: NavController) {

    val context = navController.context
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(MaterialTheme.colorScheme.background)
            .padding(30.dp),

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Registrar Cliente",
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
        },
    ) {innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.size(40.dp))

            NameField(
                title = "Nombre",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {}
            )

            Spacer(modifier = Modifier.size(30.dp))

            SurNameField(
                title = "Apellido",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {})

            Spacer(modifier = Modifier.size(30.dp))

            DirectionField(
                title = "Dirección",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {})

            Spacer(modifier = Modifier.size(30.dp))

            DNIField(
                title = "DNI",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {})

            Spacer(modifier = Modifier.size(30.dp))

            PhoneField(
                title = "Teléfono",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {})

            Spacer(modifier = Modifier.size(30.dp))

            EmailField(
                title = "Email",
                modifier = Modifier.width(420.dp),
                value = "",
                onChange = {})

            Spacer(modifier = Modifier.size(30.dp))

            TypeUser()

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(onClick = {
                    Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show()
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
                        text = "Registrar")
                }
            }

        } //Column

    } //Scaffold
}

@Composable
fun NameField(
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
fun SurNameField(
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
fun DirectionField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu dirección"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Directions,
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
fun DNIField(
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
fun PhoneField(
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
fun EmailField(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeUser(){
    var isExpanded by remember { mutableStateOf(false) }
    var typeUser by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = !isExpanded} )
    {

        TextField(
            label = {
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Tipo",
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize)
            },
            readOnly = true,
            value = typeUser,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Socio") },
                onClick = {
                    typeUser = "Socio"
                    isExpanded = false })

            DropdownMenuItem(
                text = { Text("No Socio") },
                onClick = {
                    typeUser = "No Socio"
                    isExpanded = false})

            DropdownMenuItem(
                text = { Text("Administrador") },
                onClick = {
                    typeUser = "Administrador"
                    isExpanded = false})
        }
    }
}
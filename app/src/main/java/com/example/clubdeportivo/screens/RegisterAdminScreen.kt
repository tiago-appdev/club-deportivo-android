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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterAdmin(navController: NavController) {
    var user by remember { mutableStateOf(UserData()) }
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
        },
    ) {innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.size(40.dp))

            NameField(
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

            SurNameField(
                title = "Apellido",
                modifier = Modifier.width(420.dp),
                value = user.surname,
                onChange = {
                    user = user.copy(
                        surname = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            PasswordField(
                title = "Contraseña",
                modifier = Modifier.width(420.dp),
                value = user.password,
                onChange = {
                    user = user.copy(
                        password = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            DNIField(
                title = "DNI",
                modifier = Modifier.width(420.dp),
                value = user.dni,
                onChange = {
                    user = user.copy(
                        dni = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            PhoneField(
                title = "Teléfono",
                modifier = Modifier.width(420.dp),
                value = user.phone,
                onChange = {
                    user = user.copy(
                        phone = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            EmailField(
                title = "Email",
                modifier = Modifier.width(420.dp),
                value = user.email,
                onChange = {
                    user = user.copy(
                        email = it)
                })

            Spacer(modifier = Modifier.size(30.dp))

            Text(
                color = MaterialTheme.colorScheme.onBackground,
                text = "Tipo",
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize)
            TypeUser( value = user.type, onChange = {
                user = user.copy(
                    type = it)
            })

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(onClick = {
                    registerUser(navController, user)
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    user = UserData()
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

fun registerUser( navController: NavController, user: UserData) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    auth.createUserWithEmailAndPassword(user.email, user.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User registered successfully
                val firebaseUser = auth.currentUser
                val userId = firebaseUser?.uid

                if (userId != null) {
                    db.collection("users").document(userId).set(user)
                        .addOnSuccessListener {
                            Toast.makeText(navController.context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(navController.context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                // Registration failed
                Toast.makeText(navController.context, "Error al registrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
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
fun PasswordField(
    title: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresá una contraseña para tu cuenta"
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
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
fun TypeUser(value: String, onChange: (String) -> Unit){
    var isExpanded by remember { mutableStateOf(false) }
//    var typeUser by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = !isExpanded} )
    {

        TextField(
            readOnly = true,
            value = value,
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
                    onChange("Socio")
                    isExpanded = false })

            DropdownMenuItem(
                text = { Text("No Socio") },
                onClick = {
                    onChange("NoSocio")
                    isExpanded = false})

            DropdownMenuItem(
                text = { Text("Administrador") },
                onClick = {
                    onChange("Admin")
                    isExpanded = false})
        }
    }
}
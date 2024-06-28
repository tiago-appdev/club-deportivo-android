package com.example.clubdeportivo.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun LoginScreen(navController: NavController) {
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current
    val auth = remember { FirebaseAuth.getInstance() }

    fun checkUserType(uid: String, navController: NavController) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(uid)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {
                    val userType = document.getString("type")
                    if (userType == "Admin") {
                        navController.navigate(AppScreens.MenuAdminScreen.route)
                    } else {
                        navController.navigate(AppScreens.MenuUserScreen.createRoute(uid))
                    }
                } else {
                    Toast.makeText(navController.context, "El usuario no existe en la base de datos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(navController.context, "Error al verificar el usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            val uid = user.uid
                            checkUserType(uid, navController)
                        } else {
                            Toast.makeText(context, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(painter = painterResource(id = R.drawable.logo_init),
                contentDescription = stringResource(R.string.splash_image),
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {

                LoginField(
                    value = credentials.email,
                    onChange = { data -> credentials = credentials.copy(email = data) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                PasswordField(
                    value = credentials.pwd,
                    onChange = { data -> credentials = credentials.copy(pwd = data) },
                    modifier = Modifier.fillMaxWidth()
                )
            }



            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        signIn(credentials.email, credentials.pwd)
                    },
                    enabled = true,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        style = MaterialTheme.typography.titleSmall,
                        text ="Login"
                    )
                }

                TextButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(150.dp),
                    onClick = {
                        navController.navigate(AppScreens.ForgotPasswordScreen.route)
                    }) {
                    Text(
                        text = "Olvidaste tu \n" + "contraseña?",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red)

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {navController.navigate(AppScreens.RegisterUserScreen.route)},
                enabled = true,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseOnSurface),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
                ) {
                Text(
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    style = MaterialTheme.typography.titleMedium,
                    text = "Registrarse")
            }

        } // Column
    } // Surface
}
@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu email"
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
        text = "Email",
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
    value: String,
    onChange: (String) -> Unit,
//    submit: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Ingresa tu contraseña"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = Color(0xFFF14D56)
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = Color(0xFFF14D56)
            )
        }
    }
    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = "Contraseña",
        modifier = Modifier.padding( start = 10.dp ),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleMedium.fontSize)
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = null),
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = placeholder) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceDim,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}
data class Credentials(
    var email: String = "",
    var pwd: String = ""
)
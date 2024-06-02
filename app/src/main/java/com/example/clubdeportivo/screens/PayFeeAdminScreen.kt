package com.example.clubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayFeeAdmin(navController: NavController) {
    var clientNumber by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Efectivo") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "Pagar Cuota") },
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


        // Numero de cliente input with verificar button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = clientNumber,
                onValueChange = { clientNumber = it },
                label = { Text(text = "Numero de cliente") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.7f).padding(8.dp)
            )

            Button(
                onClick = {
                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(text = "Verificar", maxLines = 1) // Ensure text stays on one line
            }
        }

        // Monto field
        OutlinedTextField(
            value = "$ Monto a pagar",
            onValueChange = { /* Nothing */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)),
            enabled = false,
            readOnly = true,
            singleLine = true
        )

        // Metodo de Pago section title
        Text(
            text = "Metodo de Pago",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, top = 8.dp, bottom = 4.dp) // Adjust padding
        )

        // Radio buttons for Metodo de Pago
        Column(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = paymentMethod == "Efectivo",
                    onClick = { paymentMethod = "Efectivo" },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Efectivo",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = paymentMethod == "Tarjeta",
                    onClick = { paymentMethod = "Tarjeta" },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Tarjeta",
                    textAlign = TextAlign.Center
                )
            }
        }

        // Conditionally show card inputs if "Tarjeta" is selected
        Column(modifier = Modifier.weight(1f)) {
            if (paymentMethod == "Tarjeta") {
                // Numero de Tarjeta input
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text(text = "Numero de Tarjeta") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Fecha de Vencimiento input
                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text(text = "Fecha de Vencimiento") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // CVC input
                OutlinedTextField(
                    value = cvc,
                    onValueChange = { cvc = it },
                    label = { Text(text = "CVC") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }


        // Pagar Cuota button
        Button(
            onClick = {
                // Handle payment
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
            Text(text = "Pagar Cuota")
        }
    }
}


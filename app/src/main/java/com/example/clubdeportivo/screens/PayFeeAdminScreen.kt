package com.example.clubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayFeeAdmin(navController: NavController) {
    var clientNumber by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Efectivo") }
    var totalAmount by remember { mutableDoubleStateOf(0.0) }
    var successMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
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
                        "Pagar Cuota",
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

        ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            // Numero de cliente input with verificar button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = clientNumber,
                    onValueChange = { clientNumber = it },
                    label = { Text(text = "Numero de cliente") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(bottom = 8.dp)
                )
                //Función para buscar saldos impagos con el dni ingresado
                fun fetchUserFees(clientDni: String, onComplete: (Double) -> Unit) {
                    val db = FirebaseFirestore.getInstance()
                    val feesRef = db.collection("fees")
                        .whereEqualTo("clientdni", clientDni)
                        .whereEqualTo("paymentstatus", "pending")

                    feesRef.get()
                        .addOnSuccessListener { feesDocs ->
                            val calculatedAmount =
                                feesDocs.documents.sumOf { it.getDouble("amount") ?: 0.0 }
                            onComplete(calculatedAmount)
                        }
                        .addOnFailureListener {
                            errorMessage="Ocurrió un error al procesar la solicitud"
                        }
                }


                Button(
                    //Llamamos a la función al dar click en "Verificar"
                    onClick = {
                        fetchUserFees(clientNumber) { amount ->
                            totalAmount = amount
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.surface,
                        containerColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(text = "Verificar", maxLines = 1) // Ensure text stays on one line
                }

            }// Row

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(IntrinsicSize.Min)
            ) {
                // Monto field
                OutlinedTextField(
                    value = "$ $totalAmount",
                    onValueChange = { /* Nothing */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
//                    enabled = false,
//                    readOnly = true,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.inverseSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        unfocusedBorderColor = Color.White,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Metodo de Pago section title
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Metodo de Pago",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 8.dp, bottom = 4.dp) // Adjust padding
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Radio buttons for Metodo de Pago
            Column(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
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
                } //
                Spacer(modifier = Modifier.height(20.dp))
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
            //Función para actualizar las cuotas a pagadas al clickear botón "Pagar cuota"
            fun updatePaymentStatus(clientDni: String) {
                val db = FirebaseFirestore.getInstance()

                val feesRef = db.collection("fees").whereEqualTo("clientdni", clientDni)
                    .whereEqualTo("paymentstatus", "pending")
                feesRef.get().addOnSuccessListener { feesDocs ->
                    feesDocs.documents.forEach { feeDoc ->
                        val feeId = feeDoc.id
                        val feeRef = db.collection("fees").document(feeId)

                        // Cambiar el estado a "pagado"
                        feeRef.update("paymentstatus", "paid").addOnSuccessListener {
                            successMessage =
                                "Cuota pagada exitosamente" //
                            totalAmount = 0.0 // Se debería reiniciar el totalAmount a 0 después de prcesar la solicitud
                        }.addOnFailureListener {
                            errorMessage="Ocurrió un error al procesar la solicitud"
                        }
                    }
                }.addOnFailureListener {
                    errorMessage="Ocurrió un error al procesar la solicitud"
                }
            }

            // Pagar Cuota button
            Button(
                onClick = {
                    updatePaymentStatus(clientNumber)
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
            // Mensaje que confirma pago de cuota
            if (successMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = successMessage,
                    color = Color.Green,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

        }

    }
}


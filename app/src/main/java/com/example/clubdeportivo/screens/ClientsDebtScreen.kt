package com.example.clubdeportivo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientsDebt(navController: NavController) {
    // Dummy data for demonstration
    val debtors = listOf(
        Debtor("Juan Perez", "123456", "2024-05-01", 500),
        Debtor("Javier Lopez", "789012", "2024-05-10", 750),
        Debtor("Alicia Rodriguez", "345678", "2024-04-20", 300)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "Clientes con deudas") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_circle),
                        contentDescription = "Back",
                        tint = Color(0xFFF14D56)
                    )
                }
            }
        )

        // List of debtors
        Column(modifier = Modifier.padding(16.dp)) {
            debtors.forEach { debtor ->
                DebtorItem(debtor = debtor)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun DebtorItem(debtor: Debtor) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().shadow(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Text(text = debtor.name)
            Text(text = "Número de cliente: ${debtor.clientNumber}", textAlign = TextAlign.End)
            Text(text = "Fecha de inicio de la deuda: ${debtor.startDate}", textAlign = TextAlign.End)
            Text(text = "Monto adeudado: $${debtor.amount}", textAlign = TextAlign.End)
        }
    }
}

data class Debtor(
    val name: String,
    val clientNumber: String,
    val startDate: String,
    val amount: Int
)

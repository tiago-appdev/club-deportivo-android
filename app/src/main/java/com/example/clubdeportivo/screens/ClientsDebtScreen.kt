package com.example.clubdeportivo.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clubdeportivo.R
import com.example.clubdeportivo.utils.formatDateToLocale
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientsDebt(navController: NavController) {
    // Estado para almacenar los deudores
    var debtors by remember { mutableStateOf<List<Debtor>>(emptyList()) }

    // Consultar la base de datos
    LaunchedEffect(Unit) {
        fetchDebtors { result ->
            debtors = result
        }
    }

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
                        "Clientes con deudas",
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
                            modifier = Modifier.size(36.dp),
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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Spacer(modifier = Modifier.height(30.dp))
            DebtorList(debtors)
        }
    }
}

fun fetchDebtors(onComplete: (List<Debtor>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val currentDate = Date()

    db.collection("fees")
        .whereEqualTo("paymentstatus", "pending")
        .get()
        .addOnSuccessListener { result ->
            val debtorsList = mutableListOf<Debtor>()

            for (document in result) {
                val clientDni = document.getString("clientdni") ?: ""
                val dueDate = document.getDate("duedate")

                if (dueDate != null && dueDate.before(currentDate)) {
                    val formattedDate = formatDateToLocale(dueDate, Locale("es", "ES"))

                    db.collection("users")
                        .whereEqualTo("dni", clientDni)
                        .get()
                        .addOnSuccessListener { userResult ->
                            if (!userResult.isEmpty) {
                                val userDocument = userResult.documents[0]
                                val userName = userDocument.getString("name") ?: "Name not found"

                                val debtor = Debtor(
                                    name = userName,
                                    clientNumber = clientDni,
                                    startDate = formattedDate,
                                    amount = document.getDouble("amount")?.toInt() ?: 0
                                )
                                debtorsList.add(debtor)
                            }
                            if (debtorsList.size == result.size()) {
                                onComplete(debtorsList)
                            }
                        }
                        .addOnFailureListener { exception ->
                        }
                }
            }
            if (debtorsList.size == result.size()) {
                onComplete(debtorsList)
            }
        }
        .addOnFailureListener { exception ->
        }
}


@Composable
fun DebtorList(debtors: List<Debtor>) {
    LazyColumn {
        items(debtors) { debtor ->
            DebtorItem(debtor = debtor)
        }
    }
}

@Composable
fun DebtorItem(debtor: Debtor) {
    Surface(
        color = Color.LightGray, // Set the background color to gray
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = debtor.name, fontWeight = FontWeight.Bold)
                Text(
                    text = "$${debtor.amount}",
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Número de cliente: ${debtor.clientNumber}",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "Desde el ${debtor.startDate}",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Light
            )
        }
    }
}


data class Debtor(
    val name: String,
    val clientNumber: String,
    val startDate: String,
    val amount: Int
)

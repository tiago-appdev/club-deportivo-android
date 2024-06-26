package com.example.clubdeportivo.data

data class UserData(
    val name: String = "",
    val surname: String = "",
    val password: String = "",
    val dni: String = "",
    val phone: String = "",
    val email: String = "",
    val type: String = "NoSocio"
)
// Utils.kt
package com.example.clubdeportivo.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateToLocale(date: Date?, locale: Locale): String {
    return if (date != null) {
        val formatter = SimpleDateFormat("dd/MM/yy", locale)
        formatter.format(date)
    } else {
        ""
    }
}
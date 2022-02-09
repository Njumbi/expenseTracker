package com.example.expensetracker

import java.text.NumberFormat
import java.util.*

fun getFormattedNumber(number: String): String? {
    return if (!number.isEmpty()) {
        val `val` = number.toDouble()
        NumberFormat.getNumberInstance(Locale.US).format(`val`)
    } else {
        "0"
    }
}


package com.androidessence.utility

import java.text.NumberFormat

/**
 * Extension methods for Doubles.
 */
object DoubleUtils {
    val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()
}

/**
 * Formats a double as a currency string.
 */
fun Double.asCurrency(): String {
    return DoubleUtils.currencyFormat.format(this)
}
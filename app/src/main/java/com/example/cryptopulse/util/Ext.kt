package com.example.cryptopulse.util

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import java.util.Locale

// Use a singleton for formatters to avoid creating new instances on each recomposition
private object Formatters {
    private val percentageFormatter = DecimalFormat("0.0")

    private val priceSymbols = DecimalFormatSymbols(Locale.UK).apply {
        groupingSeparator = ','
        decimalSeparator = '.'
    }

    private val priceFormatter = DecimalFormat("#,###0.00", priceSymbols)

    fun formatPercentage(number: Double): String = "${percentageFormatter.format(number)}%"

    fun formatPrice(number: Double): String = "$${priceFormatter.format(number)}"
}

fun percentageFormatter(number: Double): String = Formatters.formatPercentage(number)

fun priceFormatter(number: Double): String = Formatters.formatPrice(number)
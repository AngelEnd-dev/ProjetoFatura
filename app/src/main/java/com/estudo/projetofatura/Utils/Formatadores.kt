package com.estudo.projetofatura.Utils

import java.text.NumberFormat
import java.util.Locale

fun Double.comoMoeda(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

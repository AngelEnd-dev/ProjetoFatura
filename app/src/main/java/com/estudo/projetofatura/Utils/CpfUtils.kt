package com.estudo.projetofatura.Utils

fun formatarCpf(cpf: String): String {

    val numeros = cpf.replace(Regex("[^0-9]"), "")
    if (numeros.length != 11) return cpf

    return "${numeros.substring(0, 3)}.${numeros.substring(3, 6)}.${numeros.substring(6, 9)}-${numeros.substring(9, 11)}"
}


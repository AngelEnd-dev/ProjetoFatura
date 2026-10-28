package com.estudo.projetofatura.Utils

import com.estudo.projetofatura.Data.remote.Invoice
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


//fornece as funções de conversão
private val gson = Gson()
//ta usando uma lista de faturas
private val tipoListaFaturas = object : TypeToken<List<Invoice>>(){
}.type

fun faturasParaJson(faturas: List<Invoice>): String = gson.toJson(faturas)
//devolve lista vazia se o texto for nulo, vazio ou inválido

fun jsonParaFaturas(json: String?): List<Invoice>{
    if(json.isNullOrBlank()) return emptyList()
    return try {
        gson.fromJson<List<Invoice>>(json, tipoListaFaturas) ?: emptyList()
    } catch (e: Exception){
        emptyList()
    }
}


//Converter uma lista de faturas (Invoice)
// em texto (JSON) e vice-versa
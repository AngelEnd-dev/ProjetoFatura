package com.estudo.projetofatura.Data.DataStore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKey {

    val CUSTOMER_CODE = stringPreferencesKey("customerCode")
    val CPF_KEY = stringPreferencesKey("cpf")
    val INVOICE_API = stringPreferencesKey("invoice")
    val SALVAR_FATURAS = stringPreferencesKey("faturas")
    val OBTER_FATURAS = stringPreferencesKey("obter")
}
//guarda as chaves da preferência
//API, code, cpf


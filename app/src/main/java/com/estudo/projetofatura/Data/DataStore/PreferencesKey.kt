package com.estudo.projetofatura.Data.DataStore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKey {

    val CUSTOMER_CODE = stringPreferencesKey("customerode")
    val CPF_KEY = stringPreferencesKey("cpf")
    val INVOICE_API = stringPreferencesKey("invoice")
}
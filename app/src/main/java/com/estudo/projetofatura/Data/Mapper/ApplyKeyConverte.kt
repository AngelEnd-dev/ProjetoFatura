package com.estudo.projetofatura.Data.Mapper

import androidx.datastore.preferences.core.Preferences
import com.estudo.projetofatura.Data.DataStore.PreferencesKey
import com.estudo.projetofatura.domain.models.ApplyKey

internal fun ApplyKey.toPreferencesKey(): Preferences.Key<String>{
    return when(this){
        ApplyKey.CUSTOMER_CODE -> PreferencesKey.CUSTOMER_CODE
        ApplyKey.CPF_KEY -> PreferencesKey.CPF_KEY
        ApplyKey.INVOICE_API -> PreferencesKey.INVOICE_API
        ApplyKey.SALVAR_FATURAS -> PreferencesKey.SALVAR_FATURAS
        ApplyKey.OBTER_FATURAS -> PreferencesKey.OBTER_FATURAS
    }
}
//Pega da ApplyKey e transforma em PreferenceKey
//Ou seja, assim o dataStore lê mais fácil
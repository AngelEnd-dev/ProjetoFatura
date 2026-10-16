package com.estudo.projetofatura.domain.models.UserCase

import androidx.datastore.preferences.core.Preferences
import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.PreferencesRepository

//CASO DE USO (2.Edit (put)) DO DATA STORE
class EditUserCase constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke (key: ApplyKey, value: String) {
        repository.put(key, value)
    }
}
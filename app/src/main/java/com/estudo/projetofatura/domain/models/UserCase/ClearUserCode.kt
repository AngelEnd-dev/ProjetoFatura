package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.domain.models.Repository.PreferencesRepository

//CASO DE USO (3.CLEAR) DO DATA STORE

class ClearUserCode constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(){
        repository.clear()
    }
}
package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.domain.models.ApplyKey
import com.estudo.projetofatura.domain.models.Repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

//CASO DE USO (1.Read (get)) DO DATA STORE
class ReadUserCase constructor(
    private val repository: PreferencesRepository
){
      suspend operator fun invoke(key: ApplyKey): Flow<String?> {
          return repository.get(key)
    }
}
package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.Data.Usuario
import com.estudo.projetofatura.domain.models.ApiRepository

// CASO DE USO DA API (CHAMA CUSTOMER CODE E O CPF)
class ApiUserCase constructor(
    private val repository: ApiRepository
) {
    suspend fun invoke (customerCode: String, cpf: String): List<Usuario> {
        return repository.buscarFatura(customerCode, cpf)
    }
}
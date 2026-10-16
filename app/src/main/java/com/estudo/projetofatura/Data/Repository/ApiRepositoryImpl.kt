package com.estudo.projetofatura.Data.Repository

import com.estudo.projetofatura.Data.ApiService
import com.estudo.projetofatura.Data.Usuario

class ApiRepositoryImpl (
    private val apiService: ApiService
): ApiService {
    override suspend fun buscarFatura(
        customerCode: String,
        cpf: String
    ): List<Usuario> {
        return apiService.buscarFatura(customerCode, cpf)
    }

}
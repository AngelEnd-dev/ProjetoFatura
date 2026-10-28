package com.estudo.projetofatura.Data.Repository

import com.estudo.projetofatura.Data.remote.ApiService
import com.estudo.projetofatura.Data.remote.Invoice
import com.estudo.projetofatura.domain.models.Repository.ApiRepository

class ApiRepositoryImpl (
    private val apiService: ApiService
): ApiRepository {
    override suspend fun buscarFatura(
        customerCode: String?,
        cpf: String?
    ): List<Invoice> {
        return apiService.buscarFatura(customerCode, cpf)
    }

}
//Implementação da Interface do Domain (ApiRepository)

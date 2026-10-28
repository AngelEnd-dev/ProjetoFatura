package com.estudo.projetofatura.domain.models.UserCase

import com.estudo.projetofatura.Data.remote.Invoice
import com.estudo.projetofatura.domain.models.Repository.ApiRepository

// CASO DE USO DA API (CHAMA CUSTOMER CODE E O CPF)
class ApiUserCase constructor(
    private val repository: ApiRepository
) {
    suspend operator fun invoke (customerCode: String?, cpf: String?): List<Invoice> {
        return repository.buscarFatura(customerCode, cpf)
    }
}
//invoke (objeto como função)
//operator(função normal mas pode usar simbolos)
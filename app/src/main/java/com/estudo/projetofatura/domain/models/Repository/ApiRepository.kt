package com.estudo.projetofatura.domain.models.Repository

import com.estudo.projetofatura.Data.remote.Invoice

interface ApiRepository {

    suspend fun buscarFatura(
        customerCode: String?,
        cpf: String?
    ): List<Invoice>
}
package com.estudo.projetofatura.domain.models

import com.estudo.projetofatura.Data.Usuario

interface ApiRepository {

    suspend fun buscarFatura(
        customerCode: String,
        cpf: String
    ): List<Usuario>
}
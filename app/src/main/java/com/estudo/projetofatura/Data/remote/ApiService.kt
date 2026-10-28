package com.estudo.projetofatura.Data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun buscarFatura(
        @Query("customerCode") customerCode: String? = null,
        @Query("cpf") cpf: String? = null,
    ): List<Invoice>

}
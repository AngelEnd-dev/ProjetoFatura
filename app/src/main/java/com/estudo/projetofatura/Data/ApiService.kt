package com.estudo.projetofatura.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun buscarFatura(
        @Query("customerCode") customerCode: String,
        @Query("cpf") cpf: String,
    ): List<Usuario>

}
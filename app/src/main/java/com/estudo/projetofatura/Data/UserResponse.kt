package com.estudo.projetofatura.Data

import com.google.gson.annotations.SerializedName


//Modelo de dados - que vai ser recebido da API

data class Usuario (
    @SerializedName ("customerName")
    val name : String,

    @SerializedName("customerCode")
    val code: String,

    @SerializedName("cpf")
    val cpf: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("value")
    val valor: Long,

    @SerializedName("documentDescription")
    val description: String,

    @SerializedName("expiration")
    val expiration: String,

    @SerializedName("formPayment")
    val payment: String,

    @SerializedName("parcel")
    val parcel: Int

)
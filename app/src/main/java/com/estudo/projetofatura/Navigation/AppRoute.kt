package com.estudo.projetofatura.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object TelaConsulta: AppRoute

    @Serializable
    data object TelaFaturas: AppRoute

    @Serializable
    data class TelaDetalhes(val ids: String): AppRoute

}

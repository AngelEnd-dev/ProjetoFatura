package com.estudo.projetofatura.Navigation.ScreenFaturas

import com.estudo.projetofatura.ui.Components.Filtro

sealed interface FaturaEvents {
    data class AlterarFiltro(val filtro: Filtro) : FaturaEvents
    data class AlterarSelecao(val id: Int) : FaturaEvents
    data object SelecionarTodas : FaturaEvents
    data object LimparSelecap : FaturaEvents

}


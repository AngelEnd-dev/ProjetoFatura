package com.estudo.projetofatura.Navigation.ScreenFaturas

import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.ui.Components.Filtro

data class FaturasUIState(
    val carregando: Boolean = true, //dados
    val filtro: Filtro = Filtro.TODAS,
    val faturas: List<InvoiceUI> = emptyList(), //todas
    val visiveis: List<InvoiceUI> = emptyList(),
    val selecionadasIds: Set<Int> = emptySet(), //oq selecionou
    val selecionadas: List<InvoiceUI> = emptyList(),
    val totalSelecionado: Double = 0.0
)

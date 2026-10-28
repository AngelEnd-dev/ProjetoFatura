package com.estudo.projetofatura.Navigation.ScreenFaturas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estudo.projetofatura.domain.models.UserCase.ObterUserCase
import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.domain.models.StatusFatura
import com.estudo.projetofatura.domain.models.toUI
import com.estudo.projetofatura.ui.Components.Filtro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FaturaViewModel (
    private val obterUserCase: ObterUserCase
): ViewModel(){
    private val _faturas = MutableStateFlow<List<InvoiceUI>>(emptyList())
    private val _filtro = MutableStateFlow(Filtro.TODAS)
    private val _selecionadasIds = MutableStateFlow(setOf<Int>())
    private val _carregando = MutableStateFlow(true)

    //combine junta esses fluxos
    val uiState: StateFlow<FaturasUIState> = combine(
        _faturas,
        _filtro,
        _selecionadasIds,
        _carregando
    ){ faturas, filtro, ids, carregando ->
        val ordenadas = faturas.sortedByDescending { it.vencimento }
        val visiveis = when (filtro){
            Filtro.TODAS -> ordenadas
            Filtro.EM_ABERTO -> ordenadas.filter { it.status == StatusFatura.EM_ABERTO }
            Filtro.ATRASADAS -> ordenadas.filter { it.status == StatusFatura.ATRASADA }
        }
        val selecionadas = ordenadas.filter { it.id in ids }
        FaturasUIState(
            carregando = carregando,
            filtro = filtro,
            faturas = ordenadas,
            visiveis = visiveis,
            selecionadasIds = ids,
            selecionadas = selecionadas,
            totalSelecionado = selecionadas.sumOf { it.valor }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FaturasUIState()
    )

    init {
        carregarFaturasSalvas()
    }

    private fun carregarFaturasSalvas(){
        viewModelScope.launch {
            obterUserCase.executar().collect { lista ->
                _faturas.value = lista.map { it.toUI() }
                _carregando.value = false
            }
        }
    }

    fun onEvent(event: FaturaEvents){
        when(event){
            is FaturaEvents.AlterarFiltro -> _filtro.value = event.filtro
            is FaturaEvents.AlterarSelecao -> _selecionadasIds.update { ids ->
                if(event.id in ids) ids - event.id else ids + event.id
            }

            FaturaEvents.SelecionarTodas ->
                _selecionadasIds.value = uiState.value.visiveis.map { it.id }.toSet()

            FaturaEvents.LimparSelecap -> _selecionadasIds.value = emptySet()
        }
    }
}



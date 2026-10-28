package com.estudo.projetofatura.Navigation.ScreenConsulta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estudo.projetofatura.ViewModel.InvoiceUIState
import com.estudo.projetofatura.domain.models.UserCase.ApiUserCase
import com.estudo.projetofatura.domain.models.UserCase.SalveUserCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

//basicamente, esse arquivo é o "cerebro" da tela de consulta, gerencia os estados
class InvoiceViewModel(
    private val apiUserCase: ApiUserCase,
    private val salveUserCase: SalveUserCase
) : ViewModel() {

    //controla oq acontece visualmente
    private val _uiState = MutableStateFlow<InvoiceUIState>(InvoiceUIState.Empty)
    val uiState: StateFlow<InvoiceUIState> = _uiState.asStateFlow()

    //algo aconteceu e vai mudar a tela
    private val _navigationEvent = Channel<NavigationEffect>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    sealed interface NavigationEffect { //efeitos
        data object NavegarParaFaturas : NavigationEffect
        data class ShowSnackBar(val mensagem: String): NavigationEffect
    }

    fun onEvent(events: InvoiceEvents){
        when(events){
            is InvoiceEvents.BuscarFaturas -> buscarFaturas(
                customerCode = events.customerCode,
                cpf = events.cpf
            )
        }
    }
    private fun buscarFaturas(customerCode: String? = null, cpf: String? = null){
        viewModelScope.launch {
            _uiState.value = InvoiceUIState.Loading
            try {
                val result = apiUserCase(customerCode, cpf)
                if (result.isEmpty()) {
                    _uiState.value = InvoiceUIState.Empty
                    _navigationEvent.send(
                        NavigationEffect.ShowSnackBar("Nenhuma fatura encontrada para os dados informados.")
                    )
                } else {
                    salveUserCase.executar(result)
                    _uiState.value = InvoiceUIState.Success(result)
                    _navigationEvent.send(NavigationEffect.NavegarParaFaturas)
                }
            } catch (e: Exception){
                _uiState.value = InvoiceUIState.Error(e.message ?: "Erro desconhecido ao buscar faturas")
            }
        }
    }
}
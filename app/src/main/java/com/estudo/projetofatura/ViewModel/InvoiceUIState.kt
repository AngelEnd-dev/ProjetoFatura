package com.estudo.projetofatura.ViewModel

import com.estudo.projetofatura.Data.remote.Invoice

//Definindo os estados de tela(UI state)
sealed class InvoiceUIState {
    object Loading : InvoiceUIState()

    object Empty : InvoiceUIState() //vazia

    data class Success(
        val faturas: List<Invoice>,
        val dadosSalvos: Boolean = false
    ): InvoiceUIState()

    data class Error(val mensagem: String): InvoiceUIState()
}
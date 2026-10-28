package com.estudo.projetofatura.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceViewModel
import com.estudo.projetofatura.domain.models.UserCase.ApiUserCase
import com.estudo.projetofatura.domain.models.UserCase.ObterUserCase
import com.estudo.projetofatura.domain.models.UserCase.SalveUserCase

//Criação da ViewModel
class InvoiceViewModelFactory(
    private val apiUserCase: ApiUserCase,
    private val salveUserCase: SalveUserCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InvoiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InvoiceViewModel(apiUserCase,salveUserCase) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
    }
}
//se precisar dos outros casos de uso, passar
//Cria a ViewModel e injeção de dependencia manual
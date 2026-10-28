package com.estudo.projetofatura.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estudo.projetofatura.Navigation.ScreenFaturas.FaturaViewModel
import com.estudo.projetofatura.domain.models.UserCase.ObterUserCase

class FaturaViewModelFactory(
    private val obterUserCase: ObterUserCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FaturaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FaturaViewModel(obterUserCase) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
    }
}
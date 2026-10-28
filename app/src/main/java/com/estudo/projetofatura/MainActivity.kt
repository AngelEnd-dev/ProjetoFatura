package com.estudo.projetofatura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.estudo.projetofatura.Data.DataStore.DataStoreManager
import com.estudo.projetofatura.Data.Repository.ApiRepositoryImpl
import com.estudo.projetofatura.Data.Repository.PreferencesRepositoryImpl
import com.estudo.projetofatura.Data.remote.RetrofitClient
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceViewModel
import com.estudo.projetofatura.ViewModel.InvoiceViewModelFactory
import com.estudo.projetofatura.domain.models.UserCase.ApiUserCase
import com.estudo.projetofatura.Navigation.AppNavigation
import com.estudo.projetofatura.Navigation.ScreenFaturas.FaturaViewModel
import com.estudo.projetofatura.ViewModel.FaturaViewModelFactory
import com.estudo.projetofatura.domain.models.UserCase.ObterUserCase
import com.estudo.projetofatura.domain.models.UserCase.SalveUserCase
import com.estudo.projetofatura.ui.theme.ProjetoFaturaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataStore e Preferences
        val dataStoreManager = DataStoreManager(applicationContext)
        val prefereRepository = PreferencesRepositoryImpl(dataStoreManager)

        // Inicializa o repositório da API remota
        val apiRepository = ApiRepositoryImpl(RetrofitClient.apiService)

        // Casos de uso
        val getObterUserCase = ObterUserCase(prefereRepository)
        val getSalveUserCase = SalveUserCase(prefereRepository)

        //  Caso de uso ( API)
        val getApiInvoiceUseCase = ApiUserCase(apiRepository)

        // Instancia as Factories
        val invoiceViewModelFactory = InvoiceViewModelFactory(getApiInvoiceUseCase, getSalveUserCase)
        val faturaViewModelFactory = FaturaViewModelFactory(getObterUserCase)

        val faturasViewModel = ViewModelProvider(this, faturaViewModelFactory)[FaturaViewModel::class.java]
        val invoiceViewModel = ViewModelProvider(this, invoiceViewModelFactory)[InvoiceViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            val navControler = rememberNavController()

            ProjetoFaturaTheme {
                AppNavigation(
                    navController = navControler,
                    invoceFactory = invoiceViewModelFactory,
                    faturaFactory = faturaViewModelFactory
                )
            }
        }
    }
}

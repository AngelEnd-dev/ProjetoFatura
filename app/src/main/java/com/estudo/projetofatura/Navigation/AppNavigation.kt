package com.estudo.projetofatura.Navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.estudo.projetofatura.ViewModel.InvoiceViewModelFactory
import com.estudo.projetofatura.ViewModel.FaturaViewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceViewModel
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceViewModel.NavigationEffect
import com.estudo.projetofatura.Navigation.ScreenFaturas.FaturaViewModel
import com.estudo.projetofatura.ui.Screens.ConsultaScreen
import com.estudo.projetofatura.ui.Screens.TelaListagem
import com.estudo.projetofatura.ViewModel.InvoiceUIState
import com.estudo.projetofatura.ui.Screens.DetalhesScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    invoceFactory: InvoiceViewModelFactory,
    faturaFactory: FaturaViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.TelaConsulta
    ) {
        composable<AppRoute.TelaConsulta> {
            val viewModel: InvoiceViewModel = viewModel(factory = invoceFactory)
            val snackbarHostState = remember { SnackbarHostState() }
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(key1 = viewModel.navigationEvent) {
                viewModel.navigationEvent.collect { effect ->
                    when (effect) {
                        is NavigationEffect.NavegarParaFaturas -> {
                            navController.navigate(AppRoute.TelaFaturas)
                        }

                        is NavigationEffect.ShowSnackBar -> {
                            snackbarHostState.showSnackbar(
                                message = effect.mensagem,
                                withDismissAction = true
                            )
                        }
                    }
                }
            }

            val isLoading = uiState is InvoiceUIState.Loading
            val errorMessage = (uiState as? InvoiceUIState.Error)?.mensagem


            ConsultaScreen(
                viewModel = viewModel,
                isLoading = isLoading,
                errorMessage = errorMessage,
                snackBarHostState = snackbarHostState,
                onEvents = { events -> viewModel.onEvent(events) }
            )
        }

        composable<AppRoute.TelaFaturas> {
            val faturasViewModel: FaturaViewModel = viewModel(factory = faturaFactory)
            val state by faturasViewModel.uiState.collectAsState()

            TelaListagem(
                state = state,
                onEvent = { event -> faturasViewModel.onEvent(event) },
                onVoltar = { navController.popBackStack() },
                onVerDetalhes = { listaSelecionadas ->
                    val idsString = listaSelecionadas.map { it.id }.joinToString(",")
                    navController.navigate(AppRoute.TelaDetalhes(ids = idsString))
                }
            )
        }

        composable<AppRoute.TelaDetalhes> { backStackEntry ->
            val rotaDetalhes = backStackEntry.toRoute<AppRoute.TelaDetalhes>()
            val idsRecebidos = rotaDetalhes.ids

            val listaDeIds = idsRecebidos.split(",")
                .mapNotNull { idTexto ->
                    idTexto.trim().toIntOrNull()
                }.toSet()

            val faturasViewModel: FaturaViewModel = viewModel(factory = faturaFactory)
            val state by faturasViewModel.uiState.collectAsState()

            val faturasFiltradasParaExibir = state.faturas.filter { fatura ->
                fatura.id in listaDeIds
            }

            DetalhesScreen(
                faturas = faturasFiltradasParaExibir,
                onVoltar = { navController.popBackStack() }
            )
        }
    }
}
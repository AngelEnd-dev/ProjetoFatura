package com.estudo.projetofatura.ui.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estudo.projetofatura.Navigation.ScreenFaturas.FaturaEvents
import com.estudo.projetofatura.Navigation.ScreenFaturas.FaturasUIState
import com.estudo.projetofatura.ui.Components.AppTopBar
import com.estudo.projetofatura.ui.Components.BarraSelecao
import com.estudo.projetofatura.ui.Components.ClienteCard
import com.estudo.projetofatura.ui.Components.FaturaItem
import com.estudo.projetofatura.ui.Components.FiltroTabs
import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.ui.Components.Filtro
import com.estudo.projetofatura.ui.Components.faturasDeExemplo
import com.estudo.projetofatura.ui.theme.AzulAcizTextoSecunda
import com.estudo.projetofatura.ui.theme.Branco

@Composable
fun TelaListagem(
    state: FaturasUIState,
    onEvent: (FaturaEvents) -> Unit,
    onVoltar: () -> Unit,
    onVerDetalhes: (List<InvoiceUI>) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Branco,
        topBar = {
            AppTopBar(titulo = "Faturas", onVoltar = onVoltar)
        },
        bottomBar = {
            AnimatedVisibility(
                visible = state.selecionadas.isNotEmpty(),
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                BarraSelecao(
                    quantidade = state.selecionadas.size,
                    total = state.totalSelecionado,
                    onVerDetalhes = { onVerDetalhes(state.selecionadas) }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            state.faturas.firstOrNull()?.let {
                ClienteCard(fatura = it, modifier = Modifier.padding(horizontal = 16.dp))
            }

            Spacer(Modifier.height(16.dp))

            FiltroTabs(
                atual = state.filtro,
                onSelecionar = { onEvent(FaturaEvents.AlterarFiltro(it)) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (state.visiveis.isEmpty() && !state.carregando) {
                    item {
                        Text(
                            text = "Nenhuma fatura nesta categoria.",
                            color = AzulAcizTextoSecunda,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                    }
                }

                items(state.visiveis, key = { it.id }) { fatura ->
                    FaturaItem(
                        fatura = fatura,
                        selecionada = fatura.id in state.selecionadasIds,
                        onToggle = { onEvent(FaturaEvents.AlterarSelecao(fatura.id)) },
                        onClick = { onVerDetalhes(listOf(fatura)) }
                    )
                }
            }
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun ListaPreview() {
//    val estadoSimulado = FaturasUIState(
//        faturas = faturasDeExemplo,
//        visiveis = faturasDeExemplo,
//        filtro = Filtro.EM_ABERTO,
//        carregando = false,
//        selecionadasIds = emptySet(),
//        selecionadas = emptyList(),
//        totalSelecionado = 0.0
//    )
//
//    TelaListagem(
//        state = estadoSimulado,
//        onEvent = {},
//        onVoltar = {},
//        onVerDetalhes = {},
//        modifier = Modifier
//    )
//}

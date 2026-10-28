package com.estudo.projetofatura.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.R
import com.estudo.projetofatura.Utils.comoMoeda
import com.estudo.projetofatura.ui.Components.AppTopBar
import com.estudo.projetofatura.ui.Components.faturasDeExemplo
import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.domain.models.StatusFatura
import com.estudo.projetofatura.ui.theme.AzulAcizTextoSecunda
import com.estudo.projetofatura.ui.theme.Branco
import com.estudo.projetofatura.ui.theme.Preto
import com.estudo.projetofatura.ui.theme.RoxoPrincipal
import com.estudo.projetofatura.ui.theme.Vermelho


@Composable
fun DetalhesScreen(
    faturas: List<InvoiceUI>,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val varias = faturas.size > 1

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Branco)
    ) {
        AppTopBar(
            titulo = if (varias) "Faturas selecionadas" else "Detalhes da fatura",
            onVoltar = onVoltar
        )

        // sem travar
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (varias) {
                item {
                    TotalCard(
                        quantidade = faturas.size,
                        total = faturas.sumOf { it.valor }
                    )
                }
            }

            items(faturas, key = { it.id }) { fatura ->
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (fatura.status == StatusFatura.ATRASADA) {
                        BannerAtraso(fatura.vencimentoTexto)
                    }
                    FaturaCard(fatura)
                }
            }

            item {
                Spacer(Modifier.height(8.dp))
            }
        }

        //
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun BannerAtraso(vencimentoTexto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Vermelho.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.alerta),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = "Fatura atrasada",
                color = Vermelho,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "O vencimento foi em $vencimentoTexto.",
                color = Vermelho,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun TotalCard(quantidade: Int, total: Double) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = RoxoPrincipal
    ) {
        Column(Modifier.padding(20.dp)) {
            Text(
                text = if (quantidade == 1) "1 fatura" else "$quantidade faturas",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp
            )
            Text(
                text = "Total: ${total.comoMoeda()}",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun FaturaCard(fatura: InvoiceUI) {
    val atrasada = fatura.status == StatusFatura.ATRASADA

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.doc),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        text = fatura.titulo,
                        color = Preto,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Parcela ${fatura.parcela}",
                        color = AzulAcizTextoSecunda,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = fatura.valor.comoMoeda(),
                color = RoxoPrincipal,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            DetalheLinha("Nome do cliente", fatura.clienteNome)
            Divisor()
            DetalheLinha("Código do cliente", fatura.clienteCodigo)
            Divisor()
            DetalheLinha("CPF", fatura.cpf)
            Divisor()
            DetalheLinha("E-mail", fatura.email)
            Divisor()

            Spacer(Modifier.height(12.dp))

            DetalheLinha(
                label = "Data de vencimento",
                valor = fatura.vencimentoTexto,
                corValor = if (atrasada) Vermelho else RoxoPrincipal
            )
            Divisor()
            DetalheLinha("Descrição do documento", fatura.titulo)
            Divisor()
            DetalheLinha("Forma de pagamento", formaPagamentoTexto(fatura.formaPagamento))
            Divisor()
            DetalheLinha("Parcela", fatura.parcela.toString())
        }
    }
}

@Composable
private fun DetalheLinha(
    label: String,
    valor: String,
    corValor: Color = Preto
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = AzulAcizTextoSecunda,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valor,
            color = corValor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun Divisor() {
    HorizontalDivider(color = Color(0xFFE5E7EB), thickness = 1.dp)
}

private fun formaPagamentoTexto(codigo: String): String {
    return when (codigo.trim().uppercase()) {
        "D", "DINHEIRO" -> "Dinheiro (D)"
        "C", "CARTAO", "CRÉDITO" -> "Cartão de Crédito (C)"
        else -> codigo
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetalhesScreenSingularPreview() {
    DetalhesScreen(
        faturas = listOf(faturasDeExemplo.first()),
        onVoltar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetalhesScreenPluralPreview() {
    DetalhesScreen(
        faturas = faturasDeExemplo,
        onVoltar = {}
    )
}
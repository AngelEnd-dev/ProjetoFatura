package com.estudo.projetofatura.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.Utils.comoMoeda
import com.estudo.projetofatura.ui.theme.Branco
import com.estudo.projetofatura.ui.theme.RoxoPrincipal


@Composable
fun BarraSelecao(
    quantidade: Int,
    total: Double,
    onVerDetalhes: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(RoxoPrincipal, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = if (quantidade == 1) "1 fatura selecionada" else "$quantidade faturas selecionadas",
                color = Branco.copy(alpha = 0.85f),
                fontSize = 14.sp
            )

            Text(
                text = total.comoMoeda(),
                color = Branco,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = onVerDetalhes,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Branco,
                contentColor = RoxoPrincipal
            )
        ) {
            Text("Ver detalhes", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarraSelecaoPreview() {
    BarraSelecao(
        quantidade = 2,
        total = 1640.90,
        onVerDetalhes = {}
    )
}

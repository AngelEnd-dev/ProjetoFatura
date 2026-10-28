package com.estudo.projetofatura.ui.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.Utils.comoMoeda
import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.ui.theme.AzulAcizTextoSecunda
import com.estudo.projetofatura.ui.theme.Preto
import com.estudo.projetofatura.ui.theme.RoxoPrincipal


@Composable
fun FaturaItem(
    fatura: InvoiceUI,
    selecionada: Boolean,
    onToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 4.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selecionada,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = RoxoPrincipal,
                    uncheckedColor = AzulAcizTextoSecunda
                )
            )

            Column(Modifier.weight(1f)) {
                Text(
                    text = fatura.titulo,
                    color = Preto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Vencimento: ${fatura.vencimentoTexto}",
                    color = AzulAcizTextoSecunda,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = fatura.valor.comoMoeda(),
                        color = Preto,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    StatusChip(fatura.status)
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = AzulAcizTextoSecunda
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FaturaItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FaturaItem(
            fatura = faturasDeExemplo[0],
            selecionada = false,
            onToggle = {},
            onClick = {}
        )
        FaturaItem(
            fatura = faturasDeExemplo[1],
            selecionada = true,
            onToggle = {},
            onClick = {}
        )
    }
}

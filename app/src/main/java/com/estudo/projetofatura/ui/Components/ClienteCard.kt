package com.estudo.projetofatura.ui.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.R
import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.ui.theme.AzulAcizTextoSecunda

@Composable
fun ClienteCard(
    fatura: InvoiceUI,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = fatura.clienteNome,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Código: ${fatura.clienteCodigo}",
                    color = AzulAcizTextoSecunda,
                    fontSize = 14.sp
                )

                Text(
                    text = "CPF: ${fatura.cpf}",
                    color = AzulAcizTextoSecunda,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClienteCardPreview() {
    ClienteCard(
        fatura = faturasDeExemplo.first(),
        modifier = Modifier.padding(16.dp)
    )
}

package com.estudo.projetofatura.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon


import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.domain.models.StatusFatura
import com.estudo.projetofatura.ui.theme.Branco
import com.estudo.projetofatura.ui.theme.Verde
import com.estudo.projetofatura.ui.theme.Vermelho

//Componente de <- Faturas, <- Detalhes
@Composable
fun AppTopBar(titulo: String, onVoltar: () -> Unit, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = onVoltar) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription =
            "Voltar", tint = Color.Black)
        }
        Text(
            text = titulo,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
@Composable
fun StatusChip(status: StatusFatura, modifier: Modifier = Modifier) {
    val (texto, corTexto, corFundo) = when (status) {
        StatusFatura.EM_ABERTO -> Triple("Em aberto", Branco, Verde)
        StatusFatura.ATRASADA -> Triple("Atrasada", Branco, Vermelho)
    }
    Text(
        text = texto,
        color = corTexto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .background(corFundo, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)

    )
}
@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    AppTopBar(
        titulo = "Minha Fatura",
        onVoltar = {  }
    )
}


package com.estudo.projetofatura.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceEvents
import com.estudo.projetofatura.R
import com.estudo.projetofatura.Navigation.ScreenConsulta.InvoiceViewModel
import com.estudo.projetofatura.ui.theme.AzulAcizTextoSecunda
import com.estudo.projetofatura.ui.theme.RoxoPrincipal
import com.estudo.projetofatura.ui.theme.Vermelho

@Composable
fun ConsultaScreen(
    viewModel: InvoiceViewModel,
    onEvents: (InvoiceEvents) -> Unit,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var texto by rememberSaveable { mutableStateOf("") }
    val podeConsultar = texto.isNotEmpty() && !isLoading

    val consultar = {
        if (podeConsultar) {
            if (texto.length == 11) {
                onEvents(InvoiceEvents.BuscarFaturas(null, texto))
            } else {
                onEvents(InvoiceEvents.BuscarFaturas(texto, null))
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = RoxoPrincipal
        ) { paddingValues ->

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(RoxoPrincipal)
                    .statusBarsPadding()
            ) {
                // Cabeçalho roxo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 40.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.papelmoeda),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .align(Alignment.TopEnd)
                    )
                    Column(modifier = Modifier.padding(top = 48.dp)) {
                        Text(
                            text = "Olá, \nvamos cuidar \ndas suas faturas.",
                            color = Color.White,
                            fontSize = 37.sp,
                            lineHeight = 38.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Consulte, selecione e acompanhe suas faturas em um só lugar.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 15.sp
                        )
                    }
                }

                // Cartão/Card branco
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .imePadding()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Acesse suas faturas",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Digite o código do cliente ou o CPF para consultar suas faturas.",
                            color = AzulAcizTextoSecunda,
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.height(24.dp))
                        Text(
                            text = "Código do cliente ou CPF",
                            color = RoxoPrincipal,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(8.dp))

                        OutlinedTextField(
                            value = texto,
                            onValueChange = { novo ->
                                texto = novo.filter { it.isDigit() }.take(11)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Digite seu código ou CPF") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            singleLine = true,
                            isError = errorMessage != null,
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(onSearch = { consultar() }),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RoxoPrincipal,
                                focusedLeadingIconColor = RoxoPrincipal
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = AzulAcizTextoSecunda,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Você pode informar o código do cliente ou o CPF (apenas números).",
                                color = AzulAcizTextoSecunda,
                                fontSize = 13.sp
                            )
                        }

                        if (errorMessage != null) {
                            Spacer(Modifier.height(12.dp))
                            Text(text = errorMessage, color = Vermelho, fontSize = 14.sp)
                        }

                        Spacer(Modifier.height(24.dp))

                        Button(
                            onClick = { consultar() },
                            enabled = podeConsultar,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = RoxoPrincipal)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Consultar faturas",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Spacer(Modifier.width(8.dp))

                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
            SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
            )
        }
    }
}
//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//private fun ConsultaScreenPreview() {
//    ConsultaScreeen(
//        onConsultar = { customerCode, cpf ->
//            // Bloco vazio apenas para o Preview funcionar
//        }
//    )
//}




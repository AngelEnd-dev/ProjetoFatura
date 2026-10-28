package com.estudo.projetofatura.ui.Components

import com.estudo.projetofatura.domain.models.InvoiceUI
import com.estudo.projetofatura.domain.models.StatusFatura
import java.time.LocalDate

//só para os preview(tipo os mock)
val faturasDeExemplo = listOf(
    InvoiceUI(
        id = 1,
        clienteNome = "Alexandre Soares",
        clienteCodigo = "9482",
        cpf = "123.456.789-00",
        titulo = "Mensalidade - Outubro/2026",
        vencimento = LocalDate.of(2026, 10, 10),
        vencimentoTexto = "10/10/2026",
        valor = 350.00,
        status = StatusFatura.ATRASADA,
        email = "livia@gmail.com",
        formaPagamento = "Dinheiro",
        parcela = 2
    ),
    InvoiceUI(
        id = 2,
        clienteNome = "Alexandre Soares",
        clienteCodigo = "9482",
        cpf = "123.456.789-00",
        titulo = "Licença de Software Premium",
        vencimento = LocalDate.of(2026, 11, 5),
        vencimentoTexto = "05/11/2026",
        valor = 1290.90,
        status = StatusFatura.EM_ABERTO,
        email = "livia@gmail.com",
        formaPagamento = "Dinheiro",
        parcela = 4
    ),
    InvoiceUI(
        id = 3,
        clienteNome = "Alexandre Soares",
        clienteCodigo = "9482",
        cpf = "123.456.789-00",
        titulo = "Taxa de Manutenção Externa",
        vencimento = LocalDate.of(2026, 11, 20),
        vencimentoTexto = "20/11/2026",
        valor = 75.00,
        status = StatusFatura.EM_ABERTO,
        email = "livia@gmail.com",
        formaPagamento = "Dinheiro",
        parcela = 2
    ),
    InvoiceUI(
        id = 4,
        clienteNome = "Alexandre Soares",
        clienteCodigo = "9482",
        cpf = "123.456.789-00",
        titulo = "Serviço de Nuvem e Backup Mensal",
        vencimento = LocalDate.of(2026, 9, 30),
        vencimentoTexto = "30/09/2026",
        valor = 420.50,
        status = StatusFatura.ATRASADA,
        email = "livia@gmail.com",
        formaPagamento = "Cartao",
        parcela = 4
    )
)

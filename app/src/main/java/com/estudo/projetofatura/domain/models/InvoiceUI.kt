package com.estudo.projetofatura.domain.models

import com.estudo.projetofatura.Data.remote.Invoice
import com.estudo.projetofatura.Utils.formatarCpf
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class StatusFatura {
    EM_ABERTO,
    ATRASADA
}

data class InvoiceUI(
    val id: Int,
    val titulo: String,
    val clienteNome: String,
    val clienteCodigo: String,
    val cpf: String,
    val email: String,
    val valor: Double,
    val vencimento: LocalDate?,
    val vencimentoTexto: String,
    val formaPagamento: String,
    val parcela: Int,
    val status: StatusFatura
)

private val formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy")

private fun parseData(texto: String): LocalDate? {
    val limpo = texto.trim().take(10)
    return runCatching { LocalDate.parse(limpo) }.getOrNull()
        ?: runCatching { LocalDate.parse(limpo, formatoData) }.getOrNull()
}

fun Invoice.toUI(hoje: LocalDate = LocalDate.now()): InvoiceUI {
    val dataVencimento = parseData(expiration)


    val textoFormatado = dataVencimento?.format(formatoData) ?: expiration

    return InvoiceUI(
        id = id,
        titulo = description,
        clienteNome = name,
        clienteCodigo = code,
        cpf = formatarCpf(cpf),
        email = email,
        valor = valor,
        vencimento = dataVencimento,
        vencimentoTexto = textoFormatado,
        formaPagamento = payment,
        parcela = parcel,
        status = if (dataVencimento != null && dataVencimento.isBefore(hoje)) {
            StatusFatura.ATRASADA
        } else {
            StatusFatura.EM_ABERTO
        }
    )
}

//API -> UI
//Formatação de CPF, datas e status da fatura

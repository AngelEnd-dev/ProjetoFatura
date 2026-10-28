package com.estudo.projetofatura.Navigation.ScreenConsulta


sealed interface InvoiceEvents {
    data class BuscarFaturas (val customerCode: String?, val cpf: String?): InvoiceEvents
}

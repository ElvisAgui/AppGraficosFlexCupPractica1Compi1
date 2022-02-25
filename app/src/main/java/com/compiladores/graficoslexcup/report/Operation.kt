package com.compiladores.graficoslexcup.report

import java.io.Serializable

open class Operation: Serializable {
    var nomOperation: String? = null
    var line: Int?= null
    var column: Int? = null
    var ocurrencia: String?= null

    constructor(nomOperation: String?, line: Int?, column: Int?, ocurrencia: String?) {
        this.nomOperation = nomOperation
        this.line = line
        this.column = column
        this.ocurrencia = ocurrencia
    }
}
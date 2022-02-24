package com.compiladores.graficoslexcup.report

class Error {
    var lexeme: String?= null
    var linea: Int?= null
    var columna: Int?= null
    var descripcion: String?= null
    var esSintactico = false

    constructor()
    constructor(lexeme: String?, linea: Int?, columna: Int?, descripcion: String?) {
        this.lexeme = lexeme
        this.linea = linea
        this.columna = columna
        this.descripcion = descripcion
    }
    constructor(lexeme: String?, linea: Int?, columna: Int?, descripcion: String?, esSintactico: Boolean) {
        this.lexeme = lexeme
        this.linea = linea
        this.columna = columna
        this.descripcion = descripcion
        this.esSintactico = esSintactico
    }


}
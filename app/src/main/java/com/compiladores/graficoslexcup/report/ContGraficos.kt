package com.compiladores.graficoslexcup.report

import java.io.Serializable

class ContGraficos: Serializable {
    var contGraficosBarra: Int = 0
    var contGraficosPie: Int = 0

    constructor()
    constructor(contGraficosBarra: Int, contGraficosPie: Int) {
        this.contGraficosBarra = contGraficosBarra
        this.contGraficosPie = contGraficosPie
    }



}
package com.compiladores.graficoslexcup.objetos

class ManejadorGraficos {

    constructor()


    fun agregarGraficoArray(graficoPila: Grafica, graficas: MutableList<Grafica>, esBarra: Boolean){
        if (esBarra && graficoPila.completoBarra()){
            graficas.add(graficoPila.convertGrafica(true))
        }
        if (!esBarra && graficoPila.veriricaionElementosPie()){
            graficas.add(graficoPila.convertGrafica(false))
        }
    }
}
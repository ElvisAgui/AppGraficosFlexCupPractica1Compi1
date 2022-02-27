package com.compiladores.graficoslexcup.objetos

import java.io.Serializable

class Tupla(val items: Int, val valoresOY: Int): Serializable {
    override fun toString(): String {
        return "Tupla(items=$items, valoresOY=$valoresOY)"
    }
}
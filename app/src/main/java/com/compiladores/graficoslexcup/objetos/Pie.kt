package com.compiladores.graficoslexcup.objetos

class Pie( titulo: String?,
           esCantidad: Boolean,
           total: Double?,
           extra: String?,
           tuplas: MutableList<Tupla>,
           items: MutableList<String>,
           valores: MutableList<Double>):Grafica( titulo, esCantidad , total, extra, tuplas, items, valores) {
}
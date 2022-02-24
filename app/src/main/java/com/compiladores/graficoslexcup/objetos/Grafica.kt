package com.compiladores.graficoslexcup.objetos

open class Grafica {
    var titulo: String? = null
    var ejecutar = false
    var esCantidad = false
    var total: Double?= null
    var extra: String?= null
    var tuplas : MutableList<Tupla> = mutableListOf()
    var items : MutableList<String> = mutableListOf()
    var valores : MutableList<Double> = mutableListOf()
    var contadorDeProduciones: Int=0
    var yaTipo = false
    /*constructores*/
    constructor(
        titulo: String?,
        esCantidad: Boolean,
        total: Double?,
        extra: String?,
        tuplas: MutableList<Tupla>,
        items: MutableList<String>,
        valores: MutableList<Double>
    ) {
        this.titulo = titulo
        this.ejecutar = ejecutar
        this.esCantidad = esCantidad
        this.total = total
        this.extra = extra
        this.tuplas = tuplas
        this.items = items
        this.valores = valores
    }
    /*constructores*/
    constructor()
    constructor(
        titulo: String?,
        tuplas: MutableList<Tupla>,
        items: MutableList<String>,
        valores: MutableList<Double>
    ) {
        this.titulo = titulo
        this.tuplas = tuplas
        this.items = items
        this.valores = valores
    }
    /*funciones de ayuda, verificando elementos faltantes*/
    fun controTitle(): Boolean{
        contadorDeProduciones++
        return this.titulo == null
    }
    fun controValores(): Boolean{
        contadorDeProduciones++
        return this.titulo == null
    }
    fun controItems(): Boolean{
        contadorDeProduciones++
        return this.titulo == null
    }
    fun controTuplas(): Boolean{
        contadorDeProduciones++
        return this.titulo == null
    }
    fun controExtra(): Boolean{
        contadorDeProduciones++
        return this.titulo == null
    }
    fun controlTotal(): Boolean{
        contadorDeProduciones++
        return esCantidad && total == null
    }
    fun controlTipo(): Boolean{
        contadorDeProduciones++
        return yaTipo
    }
    fun veriricaionElementosBarra(): Boolean{
        return contadorDeProduciones == 4;
    }
    fun veriricaionElementosPie(): Boolean{
        if(contadorDeProduciones == 7 && esCantidad)
            return true
        else if (contadorDeProduciones == 6 && !esCantidad)
            return true;
        else
            return false
    }
    /**
     * convirte esta misma grafica a barra o pie
     */
    fun convertGrafica(esBarra:Boolean): Grafica{
        if (esBarra)
            return Barra(this.titulo,this.tuplas,this.items,this.valores)
        else
            return Pie(this.titulo,this.esCantidad,this.total,this.extra,this.tuplas,this.items,this.valores)
    }
    /**
     * reinicia los valores de la grafica para capturar otras especificaciones
     */
    fun limpiarGrafica(){
        this.titulo= null
        this.ejecutar = false
        this.esCantidad = false
        this.total= null
        this.extra= null
        this.tuplas  = mutableListOf()
        this.items  = mutableListOf()
        this.valores  = mutableListOf()
        this.contadorDeProduciones=0
        this.yaTipo = false
    }

}
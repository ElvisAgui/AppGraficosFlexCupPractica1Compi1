package com.compiladores.graficoslexcup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.compiladores.graficoslexcup.objetos.Grafica
import com.compiladores.graficoslexcup.report.ContGraficos
import com.compiladores.graficoslexcup.report.ErrorSinLex
import com.compiladores.graficoslexcup.report.Operation

class PantallaMenu : AppCompatActivity() {

    var errores : ArrayList<ErrorSinLex> = arrayListOf()
    var graficas : MutableList<Grafica> = mutableListOf()
    var operations: MutableList<Operation> = mutableListOf()
    var contGraficos: ContGraficos?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu)
        val bun = intent.extras
        /**
         * errores = bun?.get("errores") as MutableList<ErrorSinLex>
        operations = bun?.get("operations") as MutableList<Operation>
        graficas = bun?.get("graficas") as MutableList<Grafica>
         */
        contGraficos = bun?.get("contGraficos") as ContGraficos
        errores = bun?.get("errores")  as ArrayList<ErrorSinLex>
    }

    fun onClickReturn(view: View){
        val lanzar = Intent(this, MainActivity::class.java)
        println("contador Barra ${contGraficos?.contGraficosBarra}, contador Pie ${contGraficos?.contGraficosPie}")
        errores.forEach(){
            println("errores "+it.lexeme+ " "+ it.linea+" "+ it.descripcion)
        }
        startActivity(lanzar)
    }
}
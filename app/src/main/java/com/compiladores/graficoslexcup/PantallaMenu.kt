package com.compiladores.graficoslexcup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.compiladores.graficoslexcup.objetos.Grafica
import com.compiladores.graficoslexcup.report.ContGraficos
import com.compiladores.graficoslexcup.report.ErrorSinLex
import com.compiladores.graficoslexcup.report.Operation


class PantallaMenu : AppCompatActivity() {

    var errores : ArrayList<ErrorSinLex> = arrayListOf()
    var graficas : ArrayList<Grafica> = arrayListOf()
    var graficasSeleccionadas : ArrayList<Grafica> = arrayListOf()
    var operations: ArrayList<Operation> = arrayListOf()
    var contGraficos: ContGraficos?=null
    var listGraficasEjecutar: ArrayList<String> = arrayListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu)
        val bun = intent.extras
        errores = bun?.get("errores") as ArrayList<ErrorSinLex>
        operations = bun?.get("operations") as ArrayList<Operation>
        graficas = bun?.get("graficas") as ArrayList<Grafica>
        contGraficos = bun?.get("contGraficos") as ContGraficos
        errores = bun?.get("errores")  as ArrayList<ErrorSinLex>
        listGraficasEjecutar = bun?.get("listGraficasEjecutar") as ArrayList<String>
    }

    /**
     * retorna a la pantalla principal
     */
    fun onClickReturn(view: View){
        val lanzar = Intent(this, MainActivity::class.java)
        println("contador Barra ${contGraficos?.contGraficosBarra}, contador Pie ${contGraficos?.contGraficosPie}")
        errores.forEach(){
            println("errores "+it.lexeme+ " "+ it.linea+" "+ it.descripcion)
        }
        startActivity(lanzar)
    }

    /**
     * funcion que muestra el cativity con los graficos
     */
    fun onClickMostGraficos(view: View){
        if(errores.isEmpty()){
            var listTitulosReit: ArrayList<String> = arrayListOf()
            graficas.forEach(){
                if (listGraficasEjecutar.contains(it.titulo) && listTitulosReit.isEmpty()){
                    graficasSeleccionadas.add(it)
                    listTitulosReit.add(it.titulo.toString())
                }else if (listGraficasEjecutar.contains(it.titulo) && !listTitulosReit.contains(it.titulo)){
                    graficasSeleccionadas.add(it)
                    listTitulosReit.add(it.titulo.toString())
                }
            }
            val lanzar = Intent(this, PantallasDeGraficos::class.java)
            lanzar.putExtra("graficas", graficasSeleccionadas as java.util.ArrayList<Grafica>?)
            startActivity(lanzar)
        }else{
            Toast.makeText(this, "No Puedes acceder a los Graficos ya que exiten Errores en el texto de entrada", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Revisar Reporte de ERRORES", Toast.LENGTH_LONG).show()

        }
    }
    /**
     * Reporte de Errores
     */
    fun onClickReportE(view: View){
        if (!errores.isEmpty()){

        }else{
            Toast.makeText(this, "No tiene Errores", Toast.LENGTH_LONG).show()

        }
    }

    /**
     * funcion para mostrar reporte general
     */
    fun onClickMostrarReporte(view: View){
        if (errores.isEmpty()){

        }else{
            Toast.makeText(this, "No Puedes acceder a los Graficos ya que exiten Errores en el texto de entrada", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Revisar Reporte de ERRORES", Toast.LENGTH_LONG).show()
        }
    }
}
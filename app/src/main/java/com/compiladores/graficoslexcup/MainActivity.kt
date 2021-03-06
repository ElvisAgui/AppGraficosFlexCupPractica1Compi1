 package com.compiladores.graficoslexcup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.compiladores.graficoslexcup.analizadores.*
import com.compiladores.graficoslexcup.objetos.*;
import com.compiladores.graficoslexcup.report.ContGraficos
import com.compiladores.graficoslexcup.report.ErrorSinLex
import com.compiladores.graficoslexcup.report.Operation
import java.io.Reader
import java.io.StringReader
import java.util.ArrayList

 class MainActivity : AppCompatActivity() {
    lateinit var texArea: EditText
     var errores : ArrayList<ErrorSinLex> = arrayListOf()
     var graficas : ArrayList<Grafica> = arrayListOf()
     var operations: ArrayList<Operation> = arrayListOf()
     var listGraficasEjecutar: ArrayList<String> = arrayListOf()
     var contGraficos:ContGraficos = ContGraficos(0,5)
     var texto: String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClick(view: View){
        texArea = findViewById(R.id.areaText)
        texto = texArea.text.toString()
        val reader: Reader = StringReader(texto)
        val lexicam: LexerAnalysis = LexerAnalysis(reader)
        val parserVar: parser = parser(lexicam)
        if(texto  == ""){
            Toast.makeText(this,"Area de Texto Vacio",Toast.LENGTH_LONG).show()
        }else {
            try {
                parserVar.parse()
                graficas = parserVar.graficas
                operations = parserVar.operations
                contGraficos = parserVar.contGraficos
                errores = parserVar.errorsSinLexs
                Grafica.unionErroresLexiconConSintacticos(LexerAnalysis.errorsSinLexs, errores)
                listGraficasEjecutar = parserVar.listGraficasEjecucion
                Toast.makeText(this, "BIEN TEXTO ANALIZADO CON EXITO", Toast.LENGTH_LONG).show()
                val lanzar = Intent(this, PantallaMenu::class.java)
                lanzar.putExtra("graficas", graficas as ArrayList<Grafica>?)
                lanzar.putExtra("operations", operations as ArrayList<Operation>?)
                lanzar.putExtra("errores", errores as ArrayList<ErrorSinLex>?)
                lanzar.putExtra("contGraficos", contGraficos)
                lanzar.putExtra("listGraficasEjecutar", listGraficasEjecutar as ArrayList<String>?)
                errores.forEach(){
                    println("errores "+it.lexeme+ " "+ it.linea+" "+ it.descripcion)
                }
                graficas.forEach(){
                    println("grafica ${it.titulo} ${it.contadorDeProduciones} ${it.toString()} ")
                }
                graficas.get(0).tuplas.forEach(){
                    println(it.toString())
                }
                println("llegue hasta el final")
                startActivity(lanzar)
                } catch (exe:Exception){
                    println("error desde exception catch")
                    errores = parserVar.errorsSinLexs
                    Grafica.unionErroresLexiconConSintacticos(LexerAnalysis.errorsSinLexs, errores)
                    errores.forEach(){
                        println("errores "+it.lexeme+ " "+ it.linea+" "+ it.descripcion)
                    }
                    exe.printStackTrace()
                    val lanzar = Intent(this, PantallaMenu::class.java)
                    lanzar.putExtra("graficas", graficas as ArrayList<Grafica>?)
                    lanzar.putExtra("operations", operations as ArrayList<Operation>?)
                    lanzar.putExtra("errores", errores as ArrayList<ErrorSinLex>?)
                    lanzar.putExtra("contGraficos", contGraficos)
                    lanzar.putExtra("listGraficasEjecutar", listGraficasEjecutar as ArrayList<String>?)
                    startActivity(lanzar)
                    Toast.makeText(this, "ERROR FATAL ALGO NO HAZ ECHO BIEN", Toast.LENGTH_LONG).show()
                }

            }

        }

    }


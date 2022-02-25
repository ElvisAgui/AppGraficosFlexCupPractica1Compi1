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
import com.compiladores.graficoslexcup.report.ErrorSinLex
import java.io.Reader
import java.io.StringReader

 class MainActivity : AppCompatActivity() {
    lateinit var archivoButton : Button
    lateinit var texArea: EditText
    lateinit var lexicam: LexerAnalysis;
   // lateinit var Parser: parser;
    var errores : MutableList<ErrorSinLex> =mutableListOf()
    var gra: Grafica = Grafica()
    var texto: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClick(view: View){
        var cadena:String?=null
        texArea = findViewById(R.id.areaText)
        texto = texArea.text.toString()
        val reader: Reader = StringReader(texto)
        lexicam = LexerAnalysis(reader)
      //  Parser = parser(lexicam)
        if(texto  == ""){
            Toast.makeText(this,"Area de Texto Vacio",Toast.LENGTH_LONG).show()
        }else{
            try {
               // Parser.parse()
                // errores = Parser.errorsSinLexs

               // Parser.operations.forEach(){
                  //  println(it.nomOperation+it.ocurrencia)
                    //cadena+=it.ocurrencia

                Toast.makeText(this,cadena+"hola",Toast.LENGTH_LONG).show()
                val lanzar = Intent(this, PantallaMenu::class.java)
                lanzar.putExtra("pruesf",gra)
                startActivity(lanzar)
            }catch (exe:Exception){
                println("error  ${exe.localizedMessage} hola ocmo estamoa s")
                Toast.makeText(this,"ERRORRROROROR",Toast.LENGTH_LONG).show()
            }

        }



    }






}
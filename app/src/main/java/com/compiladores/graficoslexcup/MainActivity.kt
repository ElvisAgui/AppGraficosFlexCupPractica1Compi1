 package com.compiladores.graficoslexcup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.TextView
import com.compiladores.graficoslexcup.objetos.*;

class MainActivity : AppCompatActivity() {
    lateinit var archivoButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val texView = findViewById<TextView>(R.id.textPrincipal)
        //texView.text = "Bienvenido!!"
        var palabre: String = texView.toString()
        archivoButton = findViewById(R.id.archivoButton) as Button

        print(palabre)
        print("hola mundo")

    }

    fun click(){
        archivoButton.setOnClickListener {
            val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

        }
    }

    // Request code for selecting a PDF document.





}
package com.compiladores.graficoslexcup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.compiladores.graficoslexcup.objetos.Grafica

class PantallaMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu)
        val bun = intent.extras
    }

    fun onClickReturn(view: View){
        val lanzar = Intent(this, MainActivity::class.java)
        startActivity(lanzar)
    }
}
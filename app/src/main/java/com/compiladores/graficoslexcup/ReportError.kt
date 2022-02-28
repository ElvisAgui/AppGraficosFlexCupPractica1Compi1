package com.compiladores.graficoslexcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.compiladores.graficoslexcup.report.ErrorSinLex

class ReportError : AppCompatActivity() {
    var errores : ArrayList<ErrorSinLex> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_error)
        val bun = intent.extras
        errores = bun?.get("errores") as ArrayList<ErrorSinLex>
    }
}
package com.compiladores.graficoslexcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.compiladores.graficoslexcup.report.ErrorSinLex

class ReportError : AppCompatActivity() {
    var errores : ArrayList<ErrorSinLex> = arrayListOf()
    var textView: TextView?=null
    var tabla: TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_error)
        val bun = intent.extras
        errores = bun?.get("errores") as ArrayList<ErrorSinLex>
        textView =findViewById(R.id.textRow) as TextView
        tabla=findViewById(R.id.errrorTeble) as TableLayout;
        textView!!.setText("");

        errores.forEach(){
            var lexema = TextView(this);
            var linea = TextView(this);
            var columna = TextView(this);
            var tipo = TextView(this);
            var descripcion = TextView(this);
            var row= TableRow(this);
            lexema.setText(it.lexeme+" ");
            linea.setText(" ${it.linea} ")
            columna.setText(" ${it.columna} ");
            tipo.setText(tipo(it.esSintactico));
            descripcion.setText(it.descripcion)
            row.addView(lexema);
            row.addView(linea);
            row.addView(columna);
            row.addView(tipo);
            row.addView(descripcion);
            tabla!!.addView(row);
        }
    }

    fun tipo(esSintactico:Boolean):String{
        if (esSintactico) {
            return "SINTACTICO"
        }else{
            return "LEXICO"
        }

    }
}
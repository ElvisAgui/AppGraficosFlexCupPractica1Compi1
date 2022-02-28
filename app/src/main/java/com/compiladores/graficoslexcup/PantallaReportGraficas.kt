package com.compiladores.graficoslexcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.compiladores.graficoslexcup.report.ContGraficos
import com.compiladores.graficoslexcup.report.Operation

class PantallaReportGraficas : AppCompatActivity() {
    var contG: ContGraficos?=null
    var operations: ArrayList<Operation> = arrayListOf()
    var textPie: TextView?=null;
    var textBarras: TextView?=null;
    var textView:TextView?=null;
    var tabla: TableLayout?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_report_graficas)
        val bun = intent.extras
        contG = bun?.get("contGraficos") as ContGraficos
        operations = bun?.get("operations") as ArrayList<Operation>
        textBarras=findViewById(R.id.contBarras) as TextView ;
        textPie=findViewById(R.id.contPie) as TextView;


        /*asignando valores a contador de barras y de pie*/
        textBarras!!.setText(contG?.contGraficosBarra.toString());
        textPie!!.setText(contG?.contGraficosPie.toString());


        /*agregando rows a reporte de operadores*/
        textView=findViewById(R.id.contenido) as TextView;
        tabla=findViewById(R.id.tablaOperadores) as TableLayout;
        textView!!.setText("");
        iterarOperadores()

    }

    fun iterarOperadores(){
        operations.forEach(){
            var operador = TextView(this);
            var fila=TextView(this);
            var columna=TextView(this);
            var ocurrencia=TextView(this);
            var row= TableRow(this)
            operador.setText(it.nomOperation);
            fila.setText(it.line.toString());
            columna.setText(it.column.toString());
            ocurrencia.setText(it.ocurrencia);
            row.addView(operador);
            row.addView(fila);
            row.addView(columna);
            row.addView(ocurrencia);
            tabla!!.addView(row);
        }
    }
}
package com.compiladores.graficoslexcup

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.compiladores.graficoslexcup.objetos.Barra
import com.compiladores.graficoslexcup.objetos.Grafica
import com.compiladores.graficoslexcup.objetos.Pie
import com.compiladores.graficoslexcup.report.ContGraficos
import com.compiladores.graficoslexcup.report.ErrorSinLex
import com.compiladores.graficoslexcup.report.Operation
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import java.lang.Exception

class PantallasDeGraficos : AppCompatActivity() {

    var graficas : MutableList<Grafica> = arrayListOf()
    var barras: ArrayList<Barra> = arrayListOf()
    var pies: ArrayList<Pie> = arrayListOf()
    private var layout: LinearLayout? = null
    var items:ArrayList<String> = arrayListOf()
    var valores:ArrayList<Double> = arrayListOf()
    var tituloTem:String = ""
    private val colors = intArrayOf(Color.CYAN, Color.MAGENTA, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.DKGRAY)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantallas_de_graficos)
        layout = findViewById<View>(R.id.layout) as LinearLayout
        val bun = intent.extras
        graficas = bun?.get("graficas") as ArrayList<Grafica>
        separarGraficas()
        /*se itera n veces la grafie Barras*/
        var index=0
        barras.forEach(){
            ordenandoEjeX(index)
            ordenandoEjeY(index)
            unir(index, true)
            tituloTem = it.titulo.toString()
            val barchart = BarChart(this)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
            barchart.layoutParams = lp
            createCharts(barchart)
            layout?.addView(barchart)
            index++
        }

        /*se itera n veces la grafie Pie*/
        index = 0
        pies.forEach(){
            ordenandoValores(index)
            ordenandoEtiquetas(index)
            unir(index, false)
            calculoExtra(index,it.esCantidad)
            tituloTem = it.titulo.toString()
            val pieChart = PieChart(this)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
            pieChart.layoutParams = lp
            createPieCharts(pieChart)
            layout?.addView(pieChart)
            index++
        }
    }

    /**
     * Configuracion de diseno de la grafica, titulo, color titulo, background, por defecto
     */
    private fun getSameChart(
        chart: Chart<*>,
        description: String,
        textColor: Int,
        background: Int
    ): Chart<*>? {
        chart.description.text = description
        chart.description.textColor = textColor
        chart.description.textSize = 15f
        chart.setBackgroundColor(background)
        legend(chart)
        return chart
    }

    /**
     * Desactivar leyenda char.legen.isEnables = true
     * Fondo de plano para las graficas set backgrount
     */
    fun createCharts(barChart: BarChart) {
        var barChart = barChart
        barChart = getSameChart(barChart, tituloTem, Color.BLUE, Color.LTGRAY) as BarChart
        barChart.setDrawGridBackground(true)
        barChart.data = getBarData()
        barChart.invalidate()
        barChart.legend.isEnabled = true
        //Configuracion de numeros eje x, eje y
        axisX(barChart.xAxis) //
        axisLeft(barChart.axisLeft)
        axisRight(barChart.axisRight)
    }

    /**
     * configuraciones del pie
     * Radio del circulo del centro
     * Color del circulo del centro
     * Radio del circulo semi-transparente del centro
     * Color del circulo semi-transparente del centro
     * Activar/desactivar circulo del centro
     */
    fun createPieCharts(pieChart: PieChart?) {
        var pieChart = pieChart
        pieChart = getSameChart(pieChart!!, tituloTem, Color.BLACK, Color.GRAY) as PieChart?
        pieChart!!.holeRadius = 12f
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 15f
        pieChart.setTransparentCircleColor(Color.TRANSPARENT)
        pieChart.invalidate()
        pieChart.data = getPieData()
        pieChart.isDrawHoleEnabled = true
    }

    /**
     * configuraciones de data de Pie
     * Espacio entre las partes del Pie
     */
    private fun getPieData(): PieData {
        val pieDataSet = getDataSame(PieDataSet(getPieEntries(), "")) as PieDataSet
        pieDataSet.sliceSpace = 3f
        pieDataSet.valueFormatter = PercentFormatter()
        return PieData(pieDataSet)
    }

    /**
     * Se agregan los valores a los pedazos del pie
     */
    private fun getPieEntries(): java.util.ArrayList<PieEntry>? {
        val entries = java.util.ArrayList<PieEntry>()
        for (i in valores.indices) entries.add(PieEntry(valores.get(i).toFloat()))
        return entries
    }

    private fun getBarData(): BarData? {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(), "")) as BarDataSet
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f // grosor de la barra
        return barData
    }

    /**
     * Se agregan los valores a las barras
     */
    private fun getBarEntries(): java.util.ArrayList<BarEntry>? {
        val entries = java.util.ArrayList<BarEntry>()
        for (i in valores.indices) entries.add(BarEntry(i.toFloat(), valores[i].toFloat()))
        return entries
    }

    /**
     * Personalizar el diseno de los datos en la grafica
     * Colores de las barras/partes de la grafica
     * Color de los valores
     * Tamanio de los valores
     */
    private fun getDataSame(dataSet: DataSet<*>): DataSet<*>? {
        dataSet.setColors(*colors)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 15f
        return dataSet
    }

    /**
     * asignacion etiquetas de la barra eje x
     */
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(items)
    }

    /**
     * Eje Vertical o eje Y lado izquierdo
     */
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 100f
        axis.axisMinimum = 0f
        axis.granularity = 50f


    }

    /**
     * Eje Vertical o eje Y lado Derecho desctivando
     */
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    //Para modificar el diseno de la legenda y su texto acompaniante
    private fun legend(chart: Chart<*>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.LINE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        val entries = java.util.ArrayList<LegendEntry>()
        for (i in items.indices) {
            val entry = LegendEntry()
            entry.formColor = colors.get(i)
            entry.label = items.get(i)
            entries.add(entry)
        }
        legend.setCustom(entries)
    }

    /**
     * dividira el array de graficas con uno de su respectivo grupos(pie,barra)
     */
    fun separarGraficas(){
        graficas.forEach(){
            if (it is Barra){
               barras.add(it)
            }else{
                if (it is Pie){
                    pies.add(it)
                }
            }
        }
    }

    /**
     * asignando valor del array[]
     */
    fun ordenandoEjeX(index: Int){
        var itemsTemp:ArrayList<String> = arrayListOf()
        var cont:Int=barras.get(index).items.size-1
        barras.get(index).items.forEach(){
            itemsTemp.add(barras.get(index).items.get(cont))
            cont--
        }
        items = itemsTemp
    }

    /**
     * asignando valor del array[]
     */
    fun ordenandoEjeY(index: Int){
        var valoresTem:ArrayList<Double> = arrayListOf()
        var cont:Int=barras.get(index).items.size-1
        barras.get(index).valores.forEach(){
            valoresTem.add(barras.get(index).valores.get(cont))
            cont--
        }
        valores = valoresTem

    }

    /**
     * funcion unir ordena ejex/etiquets respectivament con ejey/valores
     */
    fun unir(index: Int, esBarra: Boolean){
        try {
            if(esBarra){
                barras.get(index).tuplas.forEach(){
                    println("---${it.items} ${it.valoresOY}")
                    barras.get(index).valores.set(it.items,valores.get(it.valoresOY))
                }
                valores =  barras.get(index).valores
            }else{
                pies.get(index).tuplas.forEach(){
                    println("---${it.items} ${it.valoresOY}")
                    pies.get(index).valores.set(it.items,valores.get(it.valoresOY))
                }
                valores =  pies.get(index).valores
            }
        }catch (ex: Exception){
            Toast.makeText(this,"Haz cometido un error Semantico", Toast.LENGTH_LONG).show()
        }

    }

    /**
     * oredena lor valores de las graficas pie
     */
    fun ordenandoValores(index: Int){
        var valoresTem:ArrayList<Double> = arrayListOf()
        var cont:Int=pies.get(index).items.size-1
        pies.get(index).valores.forEach(){
            valoresTem.add(pies.get(index).valores.get(cont))
            cont--
        }
        valores = valoresTem

    }

    /**
     * oredena todos los valors de etiquetas de graficas de pie
     */
    fun ordenandoEtiquetas(index: Int){
        var itemsTemp:ArrayList<String> = arrayListOf()
        var cont:Int=pies.get(index).items.size-1
        pies.get(index).items.forEach(){
            itemsTemp.add(pies.get(index).items.get(cont))
            cont--
        }
        items = itemsTemp
    }

    /**
     * funcion calcula el valor de extra en la grafica de pie
     */
    fun calculoExtra(index: Int, esCantidad: Boolean){
        var suma:Double=0.0;
        valores.forEach(){
            suma += it
        }
        if (esCantidad){
            var cont=0
           valores.forEach(){
               pies.get(index).valores.set(cont,it*100.0 / pies.get(index).total!!)
               cont++
           }
            pies.get(index).valores.add(pies.get(index).total!!-suma)
            valores = pies.get(index).valores
        }else{
            valores.add(360.0-suma)
        }
        items.add(pies.get(index).extra.toString())

    }

}
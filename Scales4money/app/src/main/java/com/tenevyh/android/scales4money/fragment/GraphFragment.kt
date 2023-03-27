package com.tenevyh.android.scales4money.fragment


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import kotlinx.android.synthetic.main.graph_fragment.*
import java.text.SimpleDateFormat
import java.util.*


class GraphFragment: Fragment(R.layout.graph_fragment) {
    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Подписываемся на изменения данных в ViewModel
        chart.apply {
            setTouchEnabled(true)
            setPinchZoom(true)
            description.isEnabled = false
            setNoDataText("No data")
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelCount = 5
            xAxis.textColor = Color.WHITE
            axisLeft.textColor = Color.WHITE
            axisRight.isEnabled = false
            setDrawGridBackground(false)
        }

        // Подписываемся на изменения данных в ViewModel
        balanceViewModel.balanceSheetsLD.observe(viewLifecycleOwner) { balanceList ->
            // Создание списка значений для XAxis
            val dates = balanceList.map { balance -> balance.date.time.toFloat() }

            // Создание списка значений для YAxis
            val balances = balanceList.map { balance -> Entry(balance.date.time.toFloat(), balance.number.toFloat()) }

            // Создание объекта LineDataSet с вашими данными
            val dataSet = LineDataSet(balances, "Balance").apply {
                color = Color.BLUE
                setCircleColor(Color.BLUE)
                lineWidth = 2f
                circleRadius = 4f
                setDrawCircleHole(false)
                valueTextColor = Color.BLUE
                valueTextSize = 12f
            }

            // Создание объекта LineData, добавление в него LineDataSet
            val lineData = LineData(dataSet)

            // Настройка XAxis
            val xAxis = chart.xAxis
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    // Конвертация миллисекунд в дату в формате "dd/MM/yyyy"
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    return dateFormat.format(Date(value.toLong()))
                }
            }

            // Настройка YAxis
            val yAxis = chart.axisLeft
            yAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }

            // Добавление данных в график и обновление отображения
            chart.data = lineData
            chart.invalidate()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GraphFragment()
    }
}
package com.amst.amst_eva2_g5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;

public class Bateria extends AppCompatActivity {
    private BarChart batteryChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryChart = findViewById(R.id.batteryChart);

        // Configura el gráfico
        setupChart();

        // Actualiza el nivel de batería
        updateBatteryLevel(100); // Cambia este valor al nivel de batería real
    }

    private void setupChart() {
        batteryChart.getDescription().setEnabled(false);
        batteryChart.setDrawValueAboveBar(false);

        // Configuración de los ejes X e Y
        XAxis xAxis = batteryChart.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftYAxis = batteryChart.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(100f);

        YAxis rightYAxis = batteryChart.getAxisRight();
        rightYAxis.setEnabled(false);

        // Configuración de la leyenda
        Legend legend = batteryChart.getLegend();
        legend.setEnabled(false);
    }

    private void updateBatteryLevel(int batteryLevel) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, batteryLevel));

        BarDataSet dataSet = new BarDataSet(entries, "Battery Level");
        dataSet.setColor(getResources().getColor(R.color.colorPrimary));

        BarData data = new BarData(dataSet);
        data.setDrawValues(false);

        batteryChart.setData(data);
        batteryChart.invalidate();
    }
}
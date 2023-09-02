package com.amst.amst_eva2_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_chart, fab_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_chart = findViewById(R.id.fab_chart); // Referencia al botón flotante componente chart
        fab_battery = findViewById(R.id.fab_battery); // Referencia al botón flotante componente battery

        fab_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatosSensados.class);
                startActivity(intent);
            }
        });

        fab_battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bateria.class);
                startActivity(intent);
            }
        });
    }
}
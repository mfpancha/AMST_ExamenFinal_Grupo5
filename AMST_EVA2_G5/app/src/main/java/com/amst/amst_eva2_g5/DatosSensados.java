package com.amst.amst_eva2_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosSensados extends AppCompatActivity {

    private Button aceptarButton;
    DatabaseReference db_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensados);
        aceptarButton = findViewById(R.id.boton_aceptar);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupos").child("Grupo5");
        leerRegistros();

        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosSensados.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                }
                Log.d("Firebase", "onDataChange: Datos recuperados exitosamente.");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Firebase", "onCancelled: Error al recuperar datos de Firebase: " + error.toString());
            }
        });
    }

    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
        LinearLayout contFecha = (LinearLayout) findViewById(R.id.ContenedorFecha);
        LinearLayout contFlame = (LinearLayout) findViewById(R.id.ContenedorFlame);
        LinearLayout contState = (LinearLayout) findViewById(R.id.ContenedorState);

        String fechaVal = String.valueOf(snapshot.child("uplink_message").child("received_at").getValue());
        String flameVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("flame").getValue());
        String stateVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("state").getValue());

        TextView fecha = new TextView(getApplicationContext());
        fecha.setText(fechaVal);
        contFecha.addView(fecha);

        TextView flame = new TextView(getApplicationContext());
        flame.setText(flameVal);
        contFlame.addView(flame);

        TextView state = new TextView(getApplicationContext());
        if ("1".equals(stateVal)) {
            state.setText("Bocina on");
        } else {
            state.setText("Bocina off");
        }
        contState.addView(state);
    }
}
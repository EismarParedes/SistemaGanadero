package com.example.myapplication.ui.praderas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Potrero;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroPotreros extends AppCompatActivity {

    private TextInputEditText areaPotreros;
    private TextInputEditText capacidadPotreros;
    private Spinner statePotreros;
    private TextInputEditText observacionPotreros;
    private Button potreroCancel;
    private Button potreroSuccess;
    private DatabaseReference databasePotreros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_potreros);

        databasePotreros = FirebaseDatabase.getInstance().getReference("potreros");

        areaPotreros = findViewById(R.id.txtAreaPotrero);
        capacidadPotreros = findViewById(R.id.txtCapacidadPotrero);
        statePotreros = findViewById(R.id.Spinner_statePotrero);
        observacionPotreros = findViewById(R.id.txtObservacionPotrero);

        potreroSuccess = findViewById(R.id.registroPotreroSucces);
        potreroCancel = findViewById(R.id.registroPotreroCancel);
        initSpinner();

        potreroSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoPotrero();
            }
        });

        potreroCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroPotreros.super.onBackPressed();
            }
        });
    }

    private void agregarNuevoPotrero() {
        String area = areaPotreros.getText().toString().trim();
        String capacidad = capacidadPotreros.getText().toString().trim();
        String estado = statePotreros.getSelectedItem().toString();
        String observacion = observacionPotreros.getText().toString().trim();

        if(!TextUtils.isEmpty(area) && !TextUtils.isEmpty(capacidad) && !TextUtils.isEmpty(observacion)){
            String id = databasePotreros.push().getKey();
            Potrero potrero = new Potrero(area, capacidad, estado, observacion);

            databasePotreros.child(id).setValue(potrero);
            limpiar();
            Toast.makeText(this, "Potrero agregado con exito", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void initSpinner() {
        String[] estado = {"Cercado", "Cercado Electrico", "Cercado Puas", "No Cercado"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        statePotreros.setAdapter(adapter);
        statePotreros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void limpiar() {
        this.areaPotreros.setText("");
        this.capacidadPotreros.setText("");
        this.observacionPotreros.setText("");
    }
}

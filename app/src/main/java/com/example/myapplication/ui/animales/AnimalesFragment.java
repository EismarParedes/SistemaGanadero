package com.example.myapplication.ui.animales;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AnimalesFragment extends AppCompatActivity {

    private Spinner sexAnimales;
    private Spinner stateAnimales;
    private Spinner razaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animales);
        sexAnimales = findViewById(R.id.Spinner_sexAnim);
        stateAnimales = findViewById(R.id.Spinner_stateAnim);
        razaAnimales = findViewById(R.id.Spinner_razaAnim);
        initspinnerfooter();
    }

    private void initspinnerfooter() {
        String[] sexoAnimales = new String[]{"Seleccionar sexo", "Masculino", "Femenino"};
        String[] estadoAnimales = new String[]{"Seleccionar estado", "Muerto", "Vendido", "Activo", "Externo"};
        String[] raceAnimales = new String[]{"Seleccionar raza","Senepol", "Holstein", "SAC", "Cebu", "Gyr"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexoAnimales);
        sexAnimales.setAdapter(adapter);
        sexAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estadoAnimales);
        stateAnimales.setAdapter(adapter1);
        stateAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, raceAnimales);
        razaAnimales.setAdapter(adapter2);
        razaAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
}

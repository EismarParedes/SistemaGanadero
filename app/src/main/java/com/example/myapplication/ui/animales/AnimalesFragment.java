package com.example.myapplication.ui.animales;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class AnimalesFragment extends Fragment {

    private Spinner sexAnimales;
    private Spinner stateAnimales;
    private Spinner razaAnimales;
    private Spinner colorAnimales;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_animales, container, false);
        sexAnimales = rootView.findViewById(R.id.Spinner_sexAnim);
        stateAnimales = rootView.findViewById(R.id.Spinner_stateAnim);
        razaAnimales = rootView.findViewById(R.id.Spinner_razaAnim);
        colorAnimales = rootView.findViewById(R.id.Spinner_colorAnim);
        initspinnerfooter();
        return rootView;
    }

    private void initspinnerfooter() {
        String[] sexoAnimales = new String[]{"Seleccionar sexo", "Masculino", "Femenino"};
        String[] estadoAnimales = new String[]{"Seleccionar estado", "Muerto", "Vendido", "Activo", "Externo"};
        String[] raceAnimales = new String[]{"Seleccionar raza","Senepol", "Holstein", "SAC", "Cebu", "Gyr"};
        String[] coloreAnimales = new String[]{"Seleccionar color", "Rojo", "Negro", "Gris", "Perla", "Pardo"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sexoAnimales);
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, estadoAnimales);
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

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, raceAnimales);
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

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, coloreAnimales);
        colorAnimales.setAdapter(adapter3);
        colorAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

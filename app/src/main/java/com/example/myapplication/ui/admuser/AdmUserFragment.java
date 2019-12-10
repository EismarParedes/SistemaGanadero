package com.example.myapplication.ui.admuser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.R;

public class AdmUserFragment extends Fragment {

    private Spinner spinnerpermisos;
    private Button botonRegistroUsuario;
    private Button botonRegistroCancelar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_admuser, container, false);
        spinnerpermisos = rootView.findViewById(R.id.Spinner_permisosUser);
        initspinnerfooter();

        botonRegistroUsuario = rootView.findViewById(R.id.regUserSuccess);
        botonRegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrousuario();
            }
        });

        botonRegistroCancelar = rootView.findViewById(R.id.regUserCancel);
        botonRegistroCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });


        return rootView;
    }


    private void initspinnerfooter() {
        String[] permisos = new String[]{"Seleccione un permiso", "Administrador", "Gerente", "Almacen                                                             "};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
                permisos);
        spinnerpermisos.setAdapter(adaptador);
        spinnerpermisos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void registrousuario() {
        Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    private void cancelar() {
        Intent intent = new Intent(getActivity(), DrawerNavig.class);
        startActivity(intent);
    }
}
package com.example.myapplication.ui.chngpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeViewModel;

public class ChngPassFragment extends Fragment {

    private Button botonchangePass;
    private Button botonchangeCancelar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_chngpass, container, false);

        botonchangePass = viewRoot.findViewById(R.id.chngpassSucces);
        botonchangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioContras();
            }
        });

        botonchangeCancelar = viewRoot.findViewById(R.id.chngpassCancel);
        botonchangeCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        return viewRoot;
    }

    private void cambioContras() {
        Toast.makeText(getActivity(), "Cambio de contraseña exitoso", Toast.LENGTH_SHORT).show();
    }

    private void cancelar(){
        Intent intent = new Intent(getActivity(), DrawerNavig.class);
        startActivity(intent);
    }


}
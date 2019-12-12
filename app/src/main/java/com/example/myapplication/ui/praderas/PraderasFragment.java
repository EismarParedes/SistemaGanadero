package com.example.myapplication.ui.praderas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.R;

public class PraderasFragment extends Fragment {

    private CardView cardAforo;
    private CardView cardPotrero;
    private CardView cardVegetales;

    public PraderasFragment(){
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_praderas, container, false);
        cardAforo = rootView.findViewById(R.id.aforosCard);
        cardPotrero = rootView.findViewById(R.id.potrerosCard);
        cardVegetales = rootView.findViewById(R.id.vegetalesCard);

        cardAforo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DatosAforos.class));
            }
        });

        cardPotrero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DatosPotreros.class));
            }
        });

        cardVegetales.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DatosVegetales.class));
            }
        });

        return rootView;
    }
}
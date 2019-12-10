package com.example.myapplication.ui.animales;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.R;

public class animalesHomeFragment extends Fragment {

    private CardView cardAnimales;
    private CardView cardRazas;

    public animalesHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_animales_home, container, false);

        cardAnimales = rootView.findViewById(R.id.animalsCard);

        cardAnimales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnimalesFragment.class);
                startActivity(intent);
            }
        });


        cardRazas = rootView.findViewById(R.id.razasCard);
        return rootView;
    }
}

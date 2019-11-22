package com.example.myapplication.ui.nomina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class NominaFragment extends Fragment {

    private NominaViewModel nominaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nominaViewModel =
                ViewModelProviders.of(this).get(NominaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nomina, container, false);
        final TextView textView = root.findViewById(R.id.text_nomina);
        nominaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
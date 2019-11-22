package com.example.myapplication.ui.admuser;

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

public class AdmUserFragment extends Fragment {

    private AdmUserViewModel admUserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        admUserViewModel =
                ViewModelProviders.of(this).get(AdmUserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admuser, container, false);
        final TextView textView = root.findViewById(R.id.text_admuser);
        admUserViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
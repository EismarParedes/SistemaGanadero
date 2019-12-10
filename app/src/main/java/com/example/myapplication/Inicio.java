package com.example.myapplication;

import android.app.Application;
import android.content.Intent;

import com.example.myapplication.ui.profile.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        if(mUser != null){
            startActivity(new Intent(Inicio.this, MainActivity.class));

        }
    }
}

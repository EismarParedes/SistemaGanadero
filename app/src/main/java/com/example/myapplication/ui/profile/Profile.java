package com.example.myapplication.ui.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser usuario;
    private String correo;
    private TextView correoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        auth = FirebaseAuth.getInstance();
        usuario = auth.getCurrentUser();
        correo = usuario.getEmail();

        correoUsuario = (TextView)findViewById(R.id.profileEmail);

        correoUsuario.setText(correo);
    }
}

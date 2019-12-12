package com.example.myapplication.ui.admuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdmUserFragment extends Fragment {

    private TextInputEditText username;
    private TextInputEditText userpass;
    private Button botonRegistroUsuario;
    private Button botonRegistroCancelar;
    private FirebaseAuth mAuth;

    public AdmUserFragment(){
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_admuser, container, false);

        mAuth = FirebaseAuth.getInstance();

        username = rootView.findViewById(R.id.usernameUser);
        userpass = rootView.findViewById(R.id.userPasswordUser);
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


    private void registrousuario() {
        String user = username.getText().toString().trim();
        String pass = userpass.getText().toString().trim();

        if(user.isEmpty()){
            username.setError("El correo es requerido");
            username.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            userpass.setError("La contraseña es requerido");
            userpass.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            username.setError("Formato de correo requerido");
            username.requestFocus();
        }

        if(pass.length()<6){
            userpass.setError("La contraseña debe ser mayor de 6 caracteres");
        }

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    private void cancelar() {
        Intent intent = new Intent(getActivity(), DrawerNavig.class);
        startActivity(intent);
    }
}
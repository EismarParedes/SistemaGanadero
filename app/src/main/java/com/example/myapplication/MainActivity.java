package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText username;
    private EditText password;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.button_open_home);
        username = (EditText)findViewById(R.id.editText_user);
        password = (EditText)findViewById(R.id.editText_pass);

        progress = new ProgressDialog(this);
        progress.setMessage("Iniciando sesion...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                mAuth.signInWithEmailAndPassword(username.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progress.dismiss();
                            startActivity(new Intent(MainActivity.this, DrawerNavig.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Usuario o contraseña erroneos",
                                    Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });


        if(username.getText().toString().length() == 0){
            username.setError("Nombre de usuario requerido");
        }


        if(password.getText().toString().length() == 0){
            password.setError("Contraseña requerida");
        }

    }

}

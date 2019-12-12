package com.example.myapplication.ui.animales;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Raza;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RazasRegistro extends AppCompatActivity {

    private TextInputEditText raza;
    private TextInputEditText descripcion;
    private Button botonSuccess;
    private Button botonCancelar;
    private DatabaseReference databaseRaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razas_registro);

        databaseRaza = FirebaseDatabase.getInstance().getReference("razas");

        raza = findViewById(R.id.txtRaza);
        descripcion = findViewById(R.id.txtDescripcion);
        botonSuccess = findViewById(R.id.registroRazaSucces);
        botonCancelar = findViewById(R.id.registroRazaCancel);

        botonSuccess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                agregarNuevaRaza();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RazasRegistro.super.onBackPressed();
            }
        });
    }

    private void agregarNuevaRaza() {
        String razaType = raza.getText().toString().trim();
        String description = descripcion.getText().toString().trim();

        if(!TextUtils.isEmpty(razaType) && !TextUtils.isEmpty(description)){
            String id = databaseRaza.push().getKey();
            Raza raza = new Raza(id, razaType, description);
            databaseRaza.child(id).setValue(raza);
            limpiar();
            Toast.makeText(this, "Raza agregada con exito", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        this.raza.setText("");
        this.descripcion.setText("");
    }
}

package com.example.myapplication.ui.praderas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Vegetal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroVegetales extends AppCompatActivity {

    private TextInputEditText vegetacion;
    private TextInputEditText descripcion;
    private DatabaseReference databaseVegetales;
    private Button vegetalCancel;
    private Button vegetalSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vegetales);

        databaseVegetales = FirebaseDatabase.getInstance().getReference("vegetales");

        vegetacion = findViewById(R.id.txtVegetacion);
        descripcion = findViewById(R.id.txtVegDescripcion);
        vegetalSuccess = findViewById(R.id.registroVegetalSucces);
        vegetalCancel = findViewById(R.id.registroVegetalCancel);

        vegetalSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoVegetal();
            }
        });

        vegetalCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroVegetales.super.onBackPressed();
            }
        });
    }

    private void agregarNuevoVegetal() {
        String vegetacionType = vegetacion.getText().toString().trim();
        String descripcionVegetal = descripcion.getText().toString().trim();

        if(!TextUtils.isEmpty(vegetacionType) && !TextUtils.isEmpty(descripcionVegetal)){
            String id = databaseVegetales.push().getKey();
            Vegetal vegetal = new Vegetal(vegetacionType, descripcionVegetal);
            databaseVegetales.child(id).setValue(vegetal);
            limpiar();
            Toast.makeText(this, "Vegetal agregado con exito", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        this.vegetacion.setText("");
        this.descripcion.setText("");
    }
}

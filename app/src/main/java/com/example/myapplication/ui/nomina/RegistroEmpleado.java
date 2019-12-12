package com.example.myapplication.ui.nomina;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Empleado;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroEmpleado extends AppCompatActivity {

    private TextInputEditText emplCedu;
    private TextInputEditText emplName;
    private Spinner emplSex;
    private Spinner emplState;
    private Button empleadoCancel;
    private Button empleadoSuccess;
    private DatabaseReference databaseEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleado);

        databaseEmpleado= FirebaseDatabase.getInstance().getReference("empleados");

        emplCedu = findViewById(R.id.txtEmplCedula);
        emplName = findViewById(R.id.txtEmplName);
        emplSex = findViewById(R.id.Spinner_emplSex);
        emplState = findViewById(R.id.Spinner_emplState);
        empleadoSuccess = findViewById(R.id.registroEmpleadoSucces);
        empleadoCancel = findViewById(R.id.registroEmpleadoCancel);
        initspinner();

        empleadoSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoEmpleado();
            }
        });

        empleadoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroEmpleado.super.onBackPressed();
            }
        });
    }

    private void agregarNuevoEmpleado() {
        String cedulaEmpleado = emplCedu.getText().toString().trim();
        String nombreEmpleado = emplName.getText().toString().trim();
        String sexoEmpleado = emplSex.getSelectedItem().toString();
        String estadoEmpleado = emplState.getSelectedItem().toString();

        if(!TextUtils.isEmpty(cedulaEmpleado) && (!TextUtils.isEmpty(nombreEmpleado))){
            String id = databaseEmpleado.push().getKey();
            Empleado empleado = new Empleado(cedulaEmpleado, nombreEmpleado, sexoEmpleado, estadoEmpleado);

            databaseEmpleado.child(id).setValue(empleado);
            limpiar();
            Toast.makeText(this, "Empleado agregado con exito", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        this.emplCedu.setText("");
        this.emplName.setText("");
    }

    private void initspinner() {
        String[] sexo = new String[]{"Seleccionar sexo", "Masculino", "Femenino"};
        String[] estado = new String[]{"Seleccionar estado", "Activo", "Inactivo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexo);
        emplSex.setAdapter(adapter);
        emplSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        emplState.setAdapter(adapter2);
        emplState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}

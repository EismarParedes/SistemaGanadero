package com.example.myapplication.ui.praderas;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Aforo;
import com.example.myapplication.R;
import com.example.myapplication.ui.animales.AnimalesFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistroAforos extends AppCompatActivity {

    private Spinner afoPotrero;
    private Spinner afoVegetal;
    private Spinner afoEmpleado;
    private Button aforoCancel;
    private Button aforoSuccess;
    private DatabaseReference databaseAforos;
    private DatabaseReference databasePotreros;
    private DatabaseReference databaseVegetales;
    private DatabaseReference databaseEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_aforos);

        databaseAforos = FirebaseDatabase.getInstance().getReference("aforos");
        databasePotreros = FirebaseDatabase.getInstance().getReference();
        databaseVegetales = FirebaseDatabase.getInstance().getReference();
        databaseEmpleados = FirebaseDatabase.getInstance().getReference();

        aforoSuccess = findViewById(R.id.registroAforoSucces);
        aforoCancel = findViewById(R.id.registroAforoCancel);
        initspinner();

        aforoSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoAforo();
            }
        });

        aforoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroAforos.super.onBackPressed();
            }
        });
    }

    private void agregarNuevoAforo() {
        String potrero = afoPotrero.getSelectedItem().toString();
        String vegetacion = afoVegetal.getSelectedItem().toString();
        String empleado = afoEmpleado.getSelectedItem().toString();

        String id = databaseAforos.push().getKey();
        Aforo aforo = new Aforo(potrero, vegetacion, empleado);

        databaseAforos.child(id).setValue(aforo);
        Toast.makeText(this, "Potrero agregado con exito", Toast.LENGTH_LONG).show();
    }

    private void initspinner() {

        databasePotreros.child("potreros").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> potrerosList = new ArrayList<String>();

                for(DataSnapshot potrerosSnap : dataSnapshot.getChildren()){
                    String potreros = potrerosSnap.child("area").getValue(String.class);
                    if(potreros != null){
                        potrerosList.add(potreros);
                    }
                }
                afoPotrero = findViewById(R.id.Spinner_afoPotrero);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistroAforos.this,
                        android.R.layout.simple_spinner_item, potrerosList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                afoPotrero.setAdapter(adapter);
                afoPotrero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseVegetales.child("vegetales").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> vegetalesList = new ArrayList<String>();

                for(DataSnapshot vegetalesSnap : dataSnapshot.getChildren()){
                    String vegetacion = vegetalesSnap.child("vegetacion").getValue(String.class);
                    if(vegetacion != null){
                        vegetalesList.add(vegetacion);
                    }
                }
                afoVegetal = findViewById(R.id.Spinner_afoVegetal);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RegistroAforos.this,
                        android.R.layout.simple_spinner_item, vegetalesList);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                afoVegetal.setAdapter(adapter1);
                afoVegetal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseEmpleados.child("empleados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> empleadosList = new ArrayList<String>();

                for(DataSnapshot empleadosSnap : dataSnapshot.getChildren()){
                    String empleados = empleadosSnap.child("nombre").getValue(String.class);
                    if(empleados != null){
                        empleadosList.add(empleados);
                    }
                }
                afoEmpleado = findViewById(R.id.Spinner_afoEmple);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RegistroAforos.this,
                        android.R.layout.simple_spinner_item, empleadosList);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                afoEmpleado.setAdapter(adapter2);
                afoEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

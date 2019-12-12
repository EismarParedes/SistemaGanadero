package com.example.myapplication.ui.animales;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Animal;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnimalesFragment extends AppCompatActivity {

    private TextInputEditText nomAnimal;
    private Spinner sexAnimales;
    private Spinner stateAnimales;
    private Spinner razaAnimaless;
    private Button botonSuccess;
    private Button botonCancelar;
    private DatabaseReference databaseAnimal;
    private DatabaseReference databaseRaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animales);

        databaseAnimal = FirebaseDatabase.getInstance().getReference("animales");
        databaseRaza = FirebaseDatabase.getInstance().getReference();

        nomAnimal = findViewById(R.id.nombAnimal);
        sexAnimales = findViewById(R.id.Spinner_sexAnim);
        stateAnimales = findViewById(R.id.Spinner_stateAnim);
        botonSuccess = findViewById(R.id.registroGanadoSucces);
        botonCancelar = findViewById(R.id.registroGanadoCancel);
        initspinner();

        botonSuccess.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                agregarNuevoAnimal();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimalesFragment.super.onBackPressed();
            }
        });
    }

    private void agregarNuevoAnimal() {
        String nombreAnimal = nomAnimal.getText().toString().trim();
        String sexoAnimal = sexAnimales.getSelectedItem().toString();
        String razaAnimal = razaAnimaless.getSelectedItem().toString();
        String estadoAnimal = stateAnimales.getSelectedItem().toString();

        if(!TextUtils.isEmpty(nombreAnimal)){
            String id = databaseAnimal.push().getKey();
            Animal animal = new Animal(nombreAnimal, sexoAnimal, razaAnimal, estadoAnimal);

            databaseAnimal.child(id).setValue(animal);
            limpiar();
            Toast.makeText(this, "Animal agregado con exito", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debes llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        this.nomAnimal.setText("");
    }

    private void initspinner() {
        String[] sexoAnimales = new String[]{"Macho", "Hembra"};
        String[] estadoAnimales = new String[]{"Muerto", "Vendido", "Activo", "Externo"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexoAnimales);
        sexAnimales.setAdapter(adapter);
        sexAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estadoAnimales);
        stateAnimales.setAdapter(adapter1);
        stateAnimales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        databaseRaza.child("razas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> razasList = new ArrayList<String>();

                for(DataSnapshot razasSnap : dataSnapshot.getChildren()){
                    String razas = razasSnap.child("razaType").getValue(String.class);
                    if(razas != null){
                        razasList.add(razas);
                    }
                }
                razaAnimaless = findViewById(R.id.Spinner_razaAnim);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AnimalesFragment.this,
                        android.R.layout.simple_spinner_item, razasList);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                razaAnimaless.setAdapter(adapter2);
                razaAnimaless.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

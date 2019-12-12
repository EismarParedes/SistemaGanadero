package com.example.myapplication.ui.praderas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.Vegetal;
import com.example.myapplication.VegetalList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosVegetales extends AppCompatActivity {

    private ListView listViewVegetales;
    private FloatingActionButton newVegetal;
    private DatabaseReference databaseVegetales;
    private List<Vegetal> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_vegetales);

        listViewVegetales = findViewById(R.id.listVegetales);
        databaseVegetales = FirebaseDatabase.getInstance().getReference("vegetales");

        listado = new ArrayList<>();

        newVegetal = findViewById(R.id.floatNewVegetal);
        newVegetal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatosVegetales.this, RegistroVegetales.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseVegetales.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot vegetalSnapshot : dataSnapshot.getChildren()){
                    Vegetal vegetal = vegetalSnapshot.getValue(Vegetal.class);
                    listado.add(vegetal);
                }

                VegetalList adapter = new VegetalList(DatosVegetales.this, listado);
                listViewVegetales.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.myapplication.ui.animales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Raza;
import com.example.myapplication.RazaList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosRazas extends AppCompatActivity {

    private ListView listViewRazas;
    private FloatingActionButton add;
    private DatabaseReference databaseRaza;
    private List<Raza> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razas);

        listViewRazas = findViewById(R.id.listRazas);
        databaseRaza = FirebaseDatabase.getInstance().getReference("razas");

        listado = new ArrayList<>();

        add = (FloatingActionButton) findViewById(R.id.floatNewRaza);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatosRazas.this, RazasRegistro.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRaza.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot razaSnapshot : dataSnapshot.getChildren()){
                    Raza razas = razaSnapshot.getValue(Raza.class);
                    listado.add(razas);
                }

                RazaList adapter = new RazaList(DatosRazas.this, listado);
                listViewRazas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
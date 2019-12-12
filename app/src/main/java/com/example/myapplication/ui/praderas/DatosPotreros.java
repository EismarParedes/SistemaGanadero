package com.example.myapplication.ui.praderas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Potrero;
import com.example.myapplication.PotreroList;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosPotreros extends AppCompatActivity {

    private ListView listViewPotreros;
    private DatabaseReference databasePotreros;
    private FloatingActionButton newPotrero;
    private List<Potrero> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_potreros);

        listViewPotreros = findViewById(R.id.listPotreros);
        databasePotreros = FirebaseDatabase.getInstance().getReference("potreros");

        listado = new ArrayList<>();

        newPotrero = findViewById(R.id.floatNewPotrero);
        newPotrero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatosPotreros.this, RegistroPotreros.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databasePotreros.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot potrerosSnapshot : dataSnapshot.getChildren()){
                    Potrero potrero = potrerosSnapshot.getValue(Potrero.class);
                    listado.add(potrero);
                }

                PotreroList adaptar = new PotreroList(DatosPotreros.this, listado);
                listViewPotreros.setAdapter(adaptar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

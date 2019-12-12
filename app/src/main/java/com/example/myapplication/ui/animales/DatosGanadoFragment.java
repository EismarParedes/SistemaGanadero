package com.example.myapplication.ui.animales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Animal;
import com.example.myapplication.AnimalList;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosGanadoFragment extends AppCompatActivity {

    private ListView listViewAnimales;
    private DatabaseReference databaseAnimales;
    private FloatingActionButton add;
    private List<Animal> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_datos_ganado);

        listViewAnimales = findViewById(R.id.listAnimales);
        databaseAnimales = FirebaseDatabase.getInstance().getReference("animales");

        listado = new ArrayList<>();

        add = (FloatingActionButton)findViewById(R.id.floatNewAnimal);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatosGanadoFragment.this, AnimalesFragment.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseAnimales.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot animalSnapshot : dataSnapshot.getChildren()){
                    Animal animal = animalSnapshot.getValue(Animal.class);
                    listado.add(animal);
                }

                AnimalList adaptar = new AnimalList(DatosGanadoFragment.this, listado);
                listViewAnimales.setAdapter(adaptar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

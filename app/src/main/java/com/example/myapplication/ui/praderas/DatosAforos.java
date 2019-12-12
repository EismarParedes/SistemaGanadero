package com.example.myapplication.ui.praderas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Aforo;
import com.example.myapplication.AforoList;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosAforos extends AppCompatActivity {

    private ListView listViewAforos;
    private DatabaseReference databaseAforos;
    private FloatingActionButton newAforo;
    private List<Aforo> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_aforos);

        listViewAforos = findViewById(R.id.listAforos);
        databaseAforos = FirebaseDatabase.getInstance().getReference("aforos");

        listado = new ArrayList<>();

        newAforo = findViewById(R.id.floatNewAforo);
        newAforo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatosAforos.this, RegistroAforos.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseAforos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot aforosSnapshot : dataSnapshot.getChildren()){
                    Aforo aforo = aforosSnapshot.getValue(Aforo.class);
                    listado.add(aforo);
                }

                AforoList adaptar = new AforoList(DatosAforos.this, listado);
                listViewAforos.setAdapter(adaptar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.myapplication.ui.nomina;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Empleado;
import com.example.myapplication.EmpleadoList;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NominaFragment extends Fragment {

    private ListView listViewEmpleado;
    private DatabaseReference databaseEmpleados;
    private FloatingActionButton newEmpleado;
    private List<Empleado> listado;

    public NominaFragment(){
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nomina, container, false);

        listViewEmpleado = rootView.findViewById(R.id.listEmpleados);
        databaseEmpleados = FirebaseDatabase.getInstance().getReference("empleados");

        listado = new ArrayList<>();

        newEmpleado = rootView.findViewById(R.id.floatNewEmpleado);
        newEmpleado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegistroEmpleado.class));
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseEmpleados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listado.clear();

                for(DataSnapshot empleadoSnapshot : dataSnapshot.getChildren()){
                    Empleado empleado = empleadoSnapshot.getValue(Empleado.class);
                    listado.add(empleado);
                }

                EmpleadoList adaptar = new EmpleadoList(getActivity(), listado);
                listViewEmpleado.setAdapter(adaptar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
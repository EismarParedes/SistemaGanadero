package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EmpleadoList extends ArrayAdapter<Empleado> {

    private Activity context;
    private List<Empleado> list;
    private TextView cedula;
    private TextView nombre;
    private TextView sexo;
    private TextView estado;
    private Empleado empleados;

    public EmpleadoList(Activity context, List<Empleado> list){
        super(context, R.layout.list_empleados, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_empleados, null, true);

        cedula = listViewItem.findViewById(R.id.cedulaEmplistado);
        nombre = listViewItem.findViewById(R.id.nombreEmplistado);
        sexo = listViewItem.findViewById(R.id.sexoEmplistado);
        estado = listViewItem.findViewById(R.id.estadoEmplistado);

        empleados = list.get(position);

        cedula.setText(empleados.getCedula());
        nombre.setText(empleados.getNombre());
        sexo.setText(empleados.getSexo());
        estado.setText(empleados.getEstado());

        return listViewItem;
    }
}

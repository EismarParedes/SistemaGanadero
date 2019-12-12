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

public class PotreroList extends ArrayAdapter<Potrero> {

    private Activity context;
    private List<Potrero> list;
    private TextView area;
    private TextView capacidad;
    private TextView estado;
    private TextView observacion;
    private Potrero potreros;

    public PotreroList(Activity context, List<Potrero> list){
        super(context, R.layout.list_potreros, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_potreros, null, true);

        area = listViewItem.findViewById(R.id.areaPotlistado);
        capacidad = listViewItem.findViewById(R.id.capacidadPotlistado);
        estado = listViewItem.findViewById(R.id.estadoPotlistado);
        observacion = listViewItem.findViewById(R.id.observacionPotlistado);

        potreros = list.get(position);

        area.setText(potreros.getArea());
        capacidad.setText(potreros.getCapacidad());
        estado.setText(potreros.getEstado());
        observacion.setText(potreros.getObservacion());

        return listViewItem;
    }
}

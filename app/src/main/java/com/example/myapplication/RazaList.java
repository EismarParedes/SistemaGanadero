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

public class RazaList extends ArrayAdapter<Raza> {

    private Activity context;
    private List<Raza> list;
    private TextView raza;
    private TextView descripcion;
    private Raza razas;

    public RazaList(Activity context, List<Raza> list){
        super(context, R.layout.list_raza, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_raza, null, true);

        raza = listViewItem.findViewById(R.id.razalistado);
        descripcion = listViewItem.findViewById(R.id.descripcionlistado);

        razas = list.get(position);

        raza.setText(razas.getRazaType());
        descripcion.setText(razas.getRazaDescripcion());

        return listViewItem;
    }
}
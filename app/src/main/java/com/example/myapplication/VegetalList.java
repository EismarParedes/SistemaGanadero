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

public class VegetalList extends ArrayAdapter<Vegetal> {

    private Activity context;
    private List<Vegetal> list;
    private TextView vegetacion;
    private TextView descripcion;
    private Vegetal vegetales;

    public VegetalList(Activity context, List<Vegetal> list){
        super(context, R.layout.list_vegetales, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_vegetales, null, true);

        vegetacion = listViewItem.findViewById(R.id.vegetacionlistado);
        descripcion = listViewItem.findViewById(R.id.descripcionVegetalListado);

        vegetales = list.get(position);

        vegetacion.setText(vegetales.getVegetacion());
        descripcion.setText(vegetales.getDescripcion());

        return listViewItem;
    }
}

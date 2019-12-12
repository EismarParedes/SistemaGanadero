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

public class AforoList extends ArrayAdapter<Aforo> {

    private Activity context;
    private List<Aforo> list;
    private TextView potrero;
    private TextView vegetacion;
    private TextView empleado;
    private Aforo aforos;

    public AforoList(Activity context, List<Aforo> list){
        super(context, R.layout.list_aforos, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_aforos, null, true);

        potrero = listViewItem.findViewById(R.id.potAforolistado);
        vegetacion = listViewItem.findViewById(R.id.vegAforolistado);
        empleado = listViewItem.findViewById(R.id.emplAforolistado);

        aforos = list.get(position);

        potrero.setText(aforos.getPotrero());
        vegetacion.setText(aforos.getVegetacion());
        empleado.setText(aforos.getEmpleado());

        return listViewItem;
    }
}

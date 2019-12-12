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

public class AnimalList extends ArrayAdapter<Animal> {

    private Activity context;
    private List<Animal> listadoAnimales;
    private TextView nombreAnimal;
    private TextView sexAnimal;
    private TextView razaAnimal;
    private TextView estadoAnimal;
    private Animal animales;

    public AnimalList(Activity context, List<Animal> listadoAnimales){
        super(context, R.layout.list_animales, listadoAnimales);
        this.context = context;
        this.listadoAnimales = listadoAnimales;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_animales, null, true);

        nombreAnimal = listViewItem.findViewById(R.id.nombreAnimalListado);
        sexAnimal = listViewItem.findViewById(R.id.sexAnimalListado);
        razaAnimal = listViewItem.findViewById(R.id.razassAnimalListado);
        estadoAnimal = listViewItem.findViewById(R.id.estadoAnimalListado);

        animales = listadoAnimales.get(position);

        nombreAnimal.setText(animales.getNombre());
        sexAnimal.setText(animales.getSexo());
        razaAnimal.setText(animales.getRaza());
        estadoAnimal.setText(animales.getEstado());

        return listViewItem;
    }
}

package com.example.myapplication;

public class Vegetal {
    private String vegetacion;
    private String descripcion;

    public Vegetal(){

    }

    public Vegetal(String vegetacion, String descripcion) {
        this.vegetacion = vegetacion;
        this.descripcion = descripcion;
    }

    public String getVegetacion() {
        return vegetacion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

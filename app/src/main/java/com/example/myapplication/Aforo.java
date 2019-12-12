package com.example.myapplication;

public class Aforo {
    private String potrero;
    private String vegetacion;
    private String empleado;

    public Aforo(){

    }

    public Aforo(String potrero, String vegetacion, String empleado) {
        this.potrero = potrero;
        this.vegetacion = vegetacion;
        this.empleado = empleado;
    }

    public String getPotrero() {
        return potrero;
    }

    public String getVegetacion() {
        return vegetacion;
    }

    public String getEmpleado() {
        return empleado;
    }
}

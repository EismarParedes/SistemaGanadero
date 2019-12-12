package com.example.myapplication;

public class Animal {

    private String nombre;
    private String sexo;
    private String raza;
    private String estado;

    public Animal(){

    }

    public Animal(String nombre, String sexo, String razS, String estado) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.raza = raza;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getRaza() {
        return raza;
    }

    public String getEstado() {
        return estado;
    }
}

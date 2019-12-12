package com.example.myapplication;

public class Empleado {

    private String cedula;
    private String nombre;
    private String sexo;
    private String estado;

    public Empleado(){

    }

    public Empleado(String cedula, String nombre, String sexo, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sexo = sexo;
        this.estado = estado;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstado() {
        return estado;
    }
}

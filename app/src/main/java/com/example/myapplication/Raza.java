package com.example.myapplication;

public class Raza {
    private String razaID;
    private String razaType;
    private String razaDescripcion;

    public Raza() {

    }
    public Raza(String razaID, String razaType, String razaDescripcion) {
        this.razaID = razaID;
        this.razaType = razaType;
        this.razaDescripcion = razaDescripcion;
    }

    public String getRazaID() {
        return razaID;
    }

    public String getRazaType() {
        return razaType;
    }

    public String getRazaDescripcion() {
        return razaDescripcion;
    }
}

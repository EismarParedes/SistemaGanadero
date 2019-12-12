package com.example.myapplication;

public class Potrero {
    private String area;
    private String capacidad;
    private String estado;
    private String observacion;

    public Potrero(){

    }

    public Potrero(String area, String capacidad, String estado, String observacion) {
        this.area = area;
        this.capacidad = capacidad;
        this.estado = estado;
        this.observacion = observacion;
    }

    public String getArea() {
        return area;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public String getObservacion() {
        return observacion;
    }
}

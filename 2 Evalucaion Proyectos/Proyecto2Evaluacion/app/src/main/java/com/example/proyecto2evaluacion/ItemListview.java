package com.example.proyecto2evaluacion;

public class ItemListview {
    private String uriFoto;
    private String dni;
    private String nombre;
    private String tipo;
    private String cantIncidencia;
    public ItemListview(String imgFoto, String dni, String nombre, String tipo, String cantIncidencia) {
        this.uriFoto = imgFoto;
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantIncidencia = cantIncidencia;

    }

    public String getUriFoto() {
        return uriFoto;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCantIncidencia() {
        return cantIncidencia;
    }
}

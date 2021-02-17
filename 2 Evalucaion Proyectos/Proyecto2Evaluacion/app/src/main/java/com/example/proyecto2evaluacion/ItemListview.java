package com.example.proyecto2evaluacion;

public class ItemListview {
    private int imgFoto;
    private String dni;
    private String nombre;
    private String tipo;
    private String cantIncidencia;
    public ItemListview(int imgFoto, String dni, String nombre, String tipo, String cantIncidencia) {
        this.imgFoto = imgFoto;
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantIncidencia = cantIncidencia;

    }

    public int getImgFoto() {
        return imgFoto;
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

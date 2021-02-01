package com.example.gridviewpersonalizado;

import android.widget.BaseAdapter;

import java.io.Serializable;

public class Ciudad {

    private String nombre;
    private Integer id;
    private Integer imagen;

    public Ciudad(String nombre, Integer id, Integer imagen){
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}

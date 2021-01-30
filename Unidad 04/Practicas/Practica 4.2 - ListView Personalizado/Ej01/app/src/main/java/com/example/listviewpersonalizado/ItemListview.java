package com.example.listviewpersonalizado;

public class ItemListview {

    private int imgFoto;
    private String titulo;
    private String contenido;

    public ItemListview(int imgFoto, String titulo, String contenido) {
        this.imgFoto = imgFoto;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public int getImgFoto() {
        return this.imgFoto;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getContenido(){
        return this.contenido;
    }
}

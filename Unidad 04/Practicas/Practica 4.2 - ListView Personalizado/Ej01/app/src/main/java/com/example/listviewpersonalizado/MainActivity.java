package com.example.listviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCursos;
    private Adaptador adaptador;
    private TextView tituloLv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCursos = (ListView) findViewById(R.id.lvCursos);

        tituloLv = new TextView(this);
        tituloLv.setText(R.string.lvTitle);
        tituloLv.setBackgroundColor(Color.parseColor("#F48E8E"));
        tituloLv.setTextColor(Color.parseColor("#FFFFFF"));
        tituloLv.setPadding(10,10,10,10);
        tituloLv.setTextSize(16);
        tituloLv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tituloLv.setTypeface(tituloLv.getTypeface(), Typeface.BOLD);

        lvCursos.addHeaderView(tituloLv);
        adaptador = new Adaptador(this, GetArrayItems());
        lvCursos.setAdapter(adaptador);
    }

    private ArrayList<ItemListview> GetArrayItems(){
        ArrayList<ItemListview> listCursos = new ArrayList<>();
        listCursos.add(new ItemListview(R.drawable.ic_sistemasinformaticos,"Sistemas Informaticos","1º - 192 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_basesdedatos,"Bases de Datos","1º - 192 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_programacion,"Programacion","1º - 256 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_lenguajedemarcas,"Lenguajes de Marcas","1º - 128 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_entornodedesarrollos,"Entorno de Desarrollo","1º - 96 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_fol,"FOL","1º - 96 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_dwec,"Desarrollo Web en Entorno Cliente","2º - 126 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_dwes,"Desarrollo Web en Entorno Servidor","2º - 168 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_daw,"Despliegue de Aplicaciones Web","2º - 63 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_diw,"Diseño de Interfaces Web","2º - 126 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_proyectodaw,"Proyecto de Desarrollo de Apps Web","2º - 40 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_eie,"Empresa e Iniciativa Emprendedora","2º - 84 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_android,"HLC - Android","2º - 63 Horas"));
        listCursos.add(new ItemListview(R.drawable.ic_fct,"FCT","2º - 370 Horas"));
        return listCursos;
    }
}
package com.example.gridviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView grid;
    private AdaptadorDeCiudades adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (GridView) findViewById(R.id.grid);

        adaptador = new AdaptadorDeCiudades(this, GetArrayItems());
        grid.setAdapter(adaptador);
    }

    private ArrayList<Ciudad> GetArrayItems(){
        ArrayList<Ciudad> listCursos = new ArrayList<>();
        listCursos.add(new Ciudad("Berlin",1,R.drawable.berlin));
        listCursos.add(new Ciudad("Dubai",2,R.drawable.dubai));
        listCursos.add(new Ciudad("Estocolmo",3,R.drawable.estocolmo));
        listCursos.add(new Ciudad("Hong Kong",4,R.drawable.hong_kong));
        listCursos.add(new Ciudad("Londres",5,R.drawable.londres));
        listCursos.add(new Ciudad("Montreal",6,R.drawable.montreal));
        listCursos.add(new Ciudad("Nueva York",7,R.drawable.nueva_york));
        listCursos.add(new Ciudad("San Francisco",8,R.drawable.san_francisco));
        listCursos.add(new Ciudad("Singapur",9,R.drawable.singapur));
        listCursos.add(new Ciudad("Tokio",10,R.drawable.tokio));
        return listCursos;
    }
}
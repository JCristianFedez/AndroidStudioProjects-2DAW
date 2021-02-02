package com.example.gridviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CiudadDetalle extends AppCompatActivity {

    private ImageView img;
    private TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad_detalle);

        img = (ImageView) findViewById(R.id.img);
        nombre = (TextView) findViewById(R.id.nombre);

        Ciudad obj = (Ciudad) getIntent().getExtras().getSerializable("Objeto");
        img.setImageResource(obj.getImagen());
        nombre.setText(obj.getNombre().toString());

    }
}
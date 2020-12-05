package com.example.unidad2_tarea04_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Variables de los botones y el texto
    private TextView miSalida;
    private Button botonHola;
    private Button botonAdios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        miSalida=(TextView) findViewById(R.id.texto_a_editar);
        botonHola=(Button) findViewById(R.id.buttonHola);
        botonAdios=(Button) findViewById(R.id.buttonAdios);

        botonHola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miSalida.setText("Hola");
            }
        });

        botonAdios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miSalida.setText("Adios");
            }
        });
    }
}
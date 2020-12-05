package com.example.ud2_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    final static int CODIGO_CREAR=1;
    private int secuencial;//para el id que voy asignando a los alumnos
    private Gson gson;
    //Para los diferentes elementos del IU
    private Button boton;
    private LinearLayout inferior;

    //LayoutInflater inflador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson=new Gson();
        secuencial=1;
        //inflador=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        inferior=findViewById(R.id.inferior);
        boton=findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentoCrear=new Intent(MainActivity.this,Alumnoactivity.class);

                //Paso el el numero secuencial que sera la ID del alumno
                intentoCrear.putExtra("secuencial",secuencial);
                startActivityForResult(intentoCrear,CODIGO_CREAR);
            }
        });
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODIGO_CREAR){
            if(resultCode==RESULT_OK && data.hasExtra("datos")){
                //SE crea un alumno nuevo con los datos enviados en gson
                Alumno nuevo=gson.fromJson(data.getStringExtra("datos"),Alumno.class);

                //View vista=inflador.inflate(R.layout.linea,null);//se puede hacer de las dos formas
                //Creo una vista con la estructura del layout linea y introduzco los datos
                View vista=getLayoutInflater().inflate(R.layout.linea,null);//Más cómodo así
                ((TextView)vista.findViewById(R.id.idalumno)).setText(String.valueOf(nuevo.getId()));
                ((TextView)vista.findViewById(R.id.nya)).setText(nuevo.getApellido1()+
                        " "+nuevo.getApellido2()+", "+nuevo.getNombre());
                ((TextView)vista.findViewById(R.id.nacimiento)).setText(String.valueOf(nuevo.getAnio()));

                //Paso al linearLayour de la lista de los usuarios la vista creada
                inferior.addView(vista);

                Toast.makeText(this, "Nuevo alumno grabado", Toast.LENGTH_SHORT).show();
                secuencial++;
            }else {
                Toast.makeText(this, "Proceso de inserción cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
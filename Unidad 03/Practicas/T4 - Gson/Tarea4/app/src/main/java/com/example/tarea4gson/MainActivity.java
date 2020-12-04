package com.example.tarea4gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button agreeAlumn;
    private TextView tvLista;
    final static int CODIGO_CREAR=1;
    private int secuencial;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLista=(TextView) findViewById(R.id.tvLista);
        agreeAlumn=(Button) findViewById(R.id.btnAgreeAlumn);
        agreeAlumn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgreeAlumn:
                Intent intent = new Intent(this, formAlumnos.class);
                intent.putExtra("secuencial",secuencial);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==CODIGO_CREAR){

            if(resultCode==RESULT_OK && data.hasExtra("datos")){
                Alumno alumnoNuevo = gson.fromJson(data.getStringExtra("datos"), Alumno.class);
                //View vista=getLayoutInflater().inflate(R.layout.linea,null);//linea
                tvLista.setText(String.valueOf(alumnoNuevo.getId()));

                //inferior.addView(vista);
                secuencial++;
                //tostada "El alumno se ha creado de forma correcta"
            }

        }else{

        }
    }

}
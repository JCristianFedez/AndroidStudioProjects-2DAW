package com.example.tarea4gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class formAlumnos extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etAp1;
    private EditText etAp2;
    private EditText etYear;
    private Gson gson;
    private Button btnAgregar;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alumnos);

        etName=(EditText) findViewById(R.id.etName);
        etAp1=(EditText) findViewById(R.id.etAp1);
        etAp2=(EditText) findViewById(R.id.etAp2);
        etYear=(EditText) findViewById(R.id.etYear);

        btnAgregar=(Button)findViewById(R.id.btnAgregar);

        gson=new Gson();
        Bundle extras = getIntent().getExtras();

        btnAgregar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                //Creo alumon (Class) Pasando los datos del formulario
                String nombre=etName.getText().toString();
                String apellido1=etAp1.getText().toString();
                String apellido2=etAp2.getText().toString();
                int anio=Integer.parseInt(etYear.getText().toString());

                Alumno alumno = new Alumno(1,nombre,apellido1,apellido2,anio);

                String alumnStr = gson.toJson(alumno);


                //Terminamos la actividad llamando al MainActivity
                Intent volver = new Intent();
                volver.putExtra("datos",alumnStr);
                setResult(RESULT_OK,volver);
                finish();
                break;
        }
    }

}
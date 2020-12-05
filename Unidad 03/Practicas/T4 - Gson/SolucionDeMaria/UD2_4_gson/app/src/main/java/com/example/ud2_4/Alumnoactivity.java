package com.example.ud2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class Alumnoactivity extends AppCompatActivity {

    private int numero=0;
    private TextView idalumno;
    private EditText nombre, apellido1, apellido2,anio;
    private Button aceptar;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnoactivity);

        gson=new Gson();

        Bundle extras=getIntent().getExtras();
        if(extras==null){
            Toast.makeText(this, "Datos no recibidos", Toast.LENGTH_SHORT).show();
            return;
        }

        //Recojo el id enviado desde el main ("Es un numero secuencial")
        numero=extras.getInt("secuencial");
        if(numero==0){
            Toast.makeText(this, "Secuencial recibido erróneo", Toast.LENGTH_SHORT).show();
            return;
        }
        idalumno=findViewById(R.id.idalumno);
        idalumno.setText(String.valueOf(numero));//Doy al idalumno el valor del numero secuencial
        //para que lo muestre en pantalla

        nombre=findViewById(R.id.nombre);
        apellido1=findViewById(R.id.apellido1);
        apellido2=findViewById(R.id.apellido2);
        anio=findViewById(R.id.anio);

        aceptar =findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obligo a informar los campos
                if(nombre.getText().toString().trim().equals("") ||
                    apellido1.getText().toString().trim().equals("") ||
                    anio.getText().toString().trim().equals("")){
                    Toast.makeText(Alumnoactivity.this, "Completa los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Instancia un nuevo alumno
                Alumno alumno=new Alumno(numero,nombre.getText().toString(),
                        apellido1.getText().toString(),apellido2.getText().toString(),
                        Integer.parseInt(anio.getText().toString()));
                //Lo pasas a un string Json
                String cadenaAlumno=gson.toJson(alumno);
                //Terminas la activity devolviendo al punto de llamada
                Intent retorno=new Intent();
                retorno.putExtra("datos",cadenaAlumno);
                setResult(RESULT_OK,retorno);
                finish();//Termino la actividad
            }
        });
    }
}
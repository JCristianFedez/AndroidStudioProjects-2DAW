package com.example.fernandezcristian_practica_sqlite01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class AndroidBaseDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_base_datos);

        //Abrimos la base de datos 'DBAlumnos' en modo escritura
        AlumnosSQLiteHelper aldbh =
                new AlumnosSQLiteHelper(this,"DBAlumnos",null,1);

        SQLiteDatabase db = aldbh.getWritableDatabase();

        //Si se ha abierto la base de datos correctamente
        if(db!=null){

            //Cerramos la base de datos
            db.close();
        }
    }
}
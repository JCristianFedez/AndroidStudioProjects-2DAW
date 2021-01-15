package com.example.fernandezcristian_practica_sqlite01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AndroidBaseDatos extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_base_datos);

        //Abrimos la base de datos 'DBAlumnos' en modo escritura
        AlumnosSQLiteHelper aldbh =
                new AlumnosSQLiteHelper(this, "DBAlumnos", null, 1);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        db = aldbh.getWritableDatabase();


        ContentValues querry = new ContentValues();
        String codigo=getIntent().getStringExtra("code");
        String nombre=getIntent().getStringExtra("name");
        String action=getIntent().getStringExtra("action");


        if(db!=null){
            switch (action) {
                case "insert":
                    if(nombre.length()>0 && codigo.length()>0) {
                        querry.put("codigo",codigo);
                        querry.put("nombre",nombre);
                        db.insert("Alumnos",null,querry);
                        Toast.makeText(getApplicationContext(),"Alumno "+nombre+" añadido" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Falto introducir nombre o codigo" ,Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "upload":
                    if(nombre.length()>0 && codigo.length()>0) {
                        querry.put("nombre",nombre);
                        db.update("Alumnos",querry,"codigo="+codigo,null);
                        Toast.makeText(getApplicationContext(),"Alumno "+nombre+" actualizado" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Falto introducir nombre o codigo" ,Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "delete":
                    if(codigo.length()>0) {
                        db.delete("Alumnos","codigo="+codigo,null);
                        Toast.makeText(getApplicationContext(),"Alumno con codigo "+codigo+" eliminado" ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Falto introducir codigo" ,Toast.LENGTH_SHORT).show();
                    }
                    break;
            }


            db.close();
        }


        //Si se ha abierto la base de datos correctamente
//        if (db != null) {
//            String insertQuerry;
//            String editQuerry;
//            String delQuerry;
//            int codigo;
//            String nombre;

/////////////INSERTAR REGISTRO//////////////////
//
//            //FORMA 1
//            codigo = 1;
//            nombre = "Alumno " + 1;
//            insertQuerry = "INSERT INTO Alumnos (codigo, nombre) VALUES (" + codigo + "," + nombre + ")";
//            db.execSQL(insertQuerry);

            //FORMA 2
//          ContentValues nuevoRegistro = new ContentValues();
//          nuevoRegistro.put("codigo", i);
//          nuevoRegistro.put("nombre","Alumno "+i);
//          db.insert("Usuarios", null, nuevoRegistro);


/////////////////ACTUALIZAR REGISTRO///////////////////////////
            //Forma 1
//            editQuerry = "UPDATE Alumnos SET nombre='AlumnoModificado' WHERE codigo=" + codigo;
//            db.execSQL(editQuerry);

            //Forma 2
//            ContentValues editRegistro = new ContentValues();
//            editRegistro.put("nombre","AlumnoModificado2");
//            db.update("Alumnos",editRegistro,"codigo=1",null);


/////////////ELIMINAR REGISTRO////////////////////////////////////
            //Forma 1
//            delQuerry = "DELETE FROM Alumnos WHERE codigo=" + codigo;
//            db.execSQL(delQuerry);

            //Forma 2
//            db.delete("Alumnos", "codigo=1", null);


            //Cerramos la base de datos
//            db.close();
//        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
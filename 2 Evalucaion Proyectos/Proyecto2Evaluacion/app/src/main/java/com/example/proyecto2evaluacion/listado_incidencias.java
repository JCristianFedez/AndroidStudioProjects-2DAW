package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class listado_incidencias extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView tvListadoIncidencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_incidencias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UsuarioSQLiteHelper usdbh =
                new UsuarioSQLiteHelper(this,"DBIncidencia",null,1);

        db = usdbh.getWritableDatabase();

        tvListadoIncidencias = (TextView) findViewById(R.id.tvListadoIncidencias);
        if(db != null){
            Cursor c;
            String[] args;
            tvListadoIncidencias.setText("");
            c = db.rawQuery("SELECT nombre, dni, perfil FROM usuario", null);

            if(c.moveToFirst()){
                do{
                    String nombre = c.getString(0);
                    String dni = c.getString(1);
                    int perfil = c.getInt(2);
                    String admin = (perfil==1)?"Administrador":"Usuario";
                    tvListadoIncidencias.append(" " + dni + " - " + nombre + " - " + admin +"\n");
                }while(c.moveToNext());
            }

            if(tvListadoIncidencias.getText().toString() == ""){
                tvListadoIncidencias.setText("No registros en la Base de datos");
            }

            db.close();
        }
    }

}
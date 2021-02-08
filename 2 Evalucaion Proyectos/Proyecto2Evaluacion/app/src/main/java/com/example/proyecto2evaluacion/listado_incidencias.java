package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class listado_incidencias extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView tvListadoIncidencias;

    private static final String USER_TABLE_NAME = "usuario";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private static final String INC_TABLE_NAME = "incidencia";
    private static final String INC_DNI = "dni";
    private static final String INC_FECHA_INICIO = "fecha_inicio";
    private static final String INC_OBSER = "observacion";
    private static final String INC_RESPONSABLE = "dni_responsable";
    private static final String INC_ESTADO = "estado";
    private static final String INC_FECHA_FIN = "fecha_resolucion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_incidencias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ProyectoSQLiteHelper prdbh =
                new ProyectoSQLiteHelper(this,"DBProyecto",null,1);

        db = prdbh.getWritableDatabase();

        tvListadoIncidencias = (TextView) findViewById(R.id.tvListadoIncidencias);
        if(db != null){
            Cursor c;
            String[] args;
            tvListadoIncidencias.setText("");

            //TODO: MOSTRAR EL NOMBRE DEL RESPONSABLE NO EL DNI

            c = db.rawQuery("SELECT " +INC_DNI+", "+INC_FECHA_INICIO+", "+INC_ESTADO+", "+INC_RESPONSABLE+
                    " FROM "+INC_TABLE_NAME, null);

            if(c.moveToFirst()){
                do{
                    String inc_dni = c.getString(0);
                    String fecha = c.getString(1);
                    String estado = (c.getInt(2) == 1)?"Resuelta":"No resuelta";
                    String responsable = c.getString(3);
                    tvListadoIncidencias.append(
                            "DNI: "+inc_dni+" | Fecha: "+fecha+" | Estado: "+estado+" | Responsable: "+responsable+"\n"
                    );
                }while(c.moveToNext());
            }

            if(tvListadoIncidencias.getText().toString() == ""){
                tvListadoIncidencias.setText("No registros en la Base de datos");
            }

            db.close();
        }
    }

}
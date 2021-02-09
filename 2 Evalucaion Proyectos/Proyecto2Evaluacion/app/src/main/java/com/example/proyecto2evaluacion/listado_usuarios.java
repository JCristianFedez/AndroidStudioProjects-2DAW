package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class listado_usuarios extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView tvListadoUsuarios;
    private ProyectoSQLiteHelper prdbh;
    private Cursor c;

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
        setContentView(R.layout.activity_listado_usuarios);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        muestraUsuario();

    }

    private void muestraUsuario(){
        prdbh = new ProyectoSQLiteHelper(this,"DBProyecto",null,1);

        db = prdbh.getReadableDatabase();

        tvListadoUsuarios = (TextView) findViewById(R.id.tvListadoUsuarios);
        if(db != null){
            String[] args;
            tvListadoUsuarios.setText("");

            //TODO: MOSTRAR BIEN LOS DATOS
            c = db.rawQuery(
                    "SELECT "+USER_NOM+", "+USER_DNI+", "+USER_PERFIL+" FROM "+USER_TABLE_NAME, null
            );

            if(c.moveToFirst()){
                do{
                    String nombre = c.getString(0);
                    String dni = c.getString(1);
                    int perfil = c.getInt(2);
                    String admin = (perfil==1)?"Administrador":"Usuario";
                    tvListadoUsuarios.append(" " + dni + " - " + nombre + " - " + admin +"\n");
                    tvListadoUsuarios.append("\n");
                }while(c.moveToNext());
            }

            if(tvListadoUsuarios.getText().toString().equals("")){
                tvListadoUsuarios.setText("No registros en la Base de datos");
            }

            db.close();
            c.close();
        }
    }
}
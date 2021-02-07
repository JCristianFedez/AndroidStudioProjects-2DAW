package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class listado_usuarios extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        UsuarioSQLiteHelper usdbh =
                new UsuarioSQLiteHelper(this,"DBUsuario",null,1);

        db = usdbh.getWritableDatabase();

        tvResultado = (TextView) findViewById(R.id.tvListadoUsuarios);
        if(db != null){
            Cursor c;
            String[] args;
            tvResultado.setText("");

            //TODO: MOSTRAR BIEN LOS DATOS
            c = db.rawQuery("SELECT nombre, dni, perfil FROM usuario", null);

            if(c.moveToFirst()){
                do{
                    String nombre = c.getString(0);
                    String dni = c.getString(1);
                    int perfil = c.getInt(2);
                    String admin = (perfil==1)?"Administrador":"Usuario";
                    tvResultado.append(" " + dni + " - " + nombre + " - " + admin +"\n");
                }while(c.moveToNext());
            }

            if(tvResultado.getText().toString() == ""){
                tvResultado.setText("No registros en la Base de datos");
            }

            db.close();
        }

    }
}
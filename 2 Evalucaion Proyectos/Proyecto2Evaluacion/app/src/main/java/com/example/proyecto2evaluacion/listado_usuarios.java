package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class listado_usuarios extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private TextView tvListadoUsuarios;
    private ProyectoSQLiteHelper prdbh;
    private Cursor c;

    private EditText etNombreFiltrar;
    private EditText etDniFiltrar;
    private Button btnEliminarUsuario;
    private Button btnModificarUsuario;
    private Button btnFiltrarUsuario;
    private Button btnEliminarFiltro;

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

        etDniFiltrar = (EditText) findViewById(R.id.etDniFiltrar);
        etNombreFiltrar = (EditText) findViewById(R.id.etNombreFiltrar);

        btnFiltrarUsuario = (Button) findViewById(R.id.btnFiltrarUsuario);
        btnEliminarFiltro = (Button) findViewById(R.id.btnEliminarFiltroUsuario);
        btnModificarUsuario = (Button) findViewById(R.id.btnModificarUsuario);
        btnEliminarUsuario = (Button) findViewById(R.id.btnEliminarUsuario);

        btnFiltrarUsuario.setOnClickListener(this);
        btnEliminarFiltro.setOnClickListener(this);
        btnModificarUsuario.setOnClickListener(this);
        btnEliminarUsuario.setOnClickListener(this);

        muestraUsuario();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFiltrarUsuario:
                muestraUsuario();
                break;

            case R.id.btnEliminarFiltroUsuario:
                eliminarFiltro();
                muestraUsuario();
                break;

            case R.id.btnModificarUsuario:
                break;

            case R.id.btnEliminarUsuario:
                break;
        }
    }

    private void muestraUsuario(){
        prdbh = new ProyectoSQLiteHelper(this,"DBProyecto",null,1);

        db = prdbh.getReadableDatabase();

        tvListadoUsuarios = (TextView) findViewById(R.id.tvListadoUsuarios);
        if(db != null){
            String[] args;
            tvListadoUsuarios.setText("");
            String queryConCantIncidencias = "SELECT "
                    + USER_TABLE_NAME + "." + USER_NOM + ", "
                    + USER_TABLE_NAME + "." + USER_DNI + ", "
                    + USER_TABLE_NAME + "." + USER_PERFIL + ", "
                    + " Count(DISTINCT " + INC_TABLE_NAME + "." + INC_DNI + ") AS Incidencias"
                    + " FROM " + USER_TABLE_NAME
                    + " INNER JOIN " + INC_TABLE_NAME + " ON " + INC_TABLE_NAME + "." + INC_RESPONSABLE + "=" + USER_TABLE_NAME + "." +USER_DNI
                    + " GROUP BY " + USER_TABLE_NAME + "." + USER_NOM + ", " + USER_TABLE_NAME + "." + USER_DNI;

            c = db.rawQuery(queryConCantIncidencias, null);

            if(c.moveToFirst()){
                do{
                    String nombre = c.getString(0);
                    String dni = c.getString(1);
                    int perfil = c.getInt(2);
                    int cant = c.getInt(3);
                    String admin = (perfil==1)?"Administrador":"Usuario";
                    tvListadoUsuarios.append(" DNI: " + dni + " | Nombre: " + nombre + " | Tipo:  " + admin +" | Cant Incidencias: " + cant +"\n");
                    tvListadoUsuarios.append("\n");
                }while(c.moveToNext());
            }

            if(tvListadoUsuarios.getText().toString().equals("")){
                tvListadoUsuarios.setText("No hay registros en la Base de datos");
            }

            db.close();
            c.close();
        }
    }

    /**
     * Devuevlve el filtro
     *
     * @return
     */
    //TODO: Terminar filtro por dni y nombre + modificar + eliminar
    private String filtro() {
        String nombreFiltrar = etNombreFiltrar.getText().toString();
        String dniFiltrar = etDniFiltrar.getText().toString();

        String filtro = "";

        if(nombreFiltrar.equals("")){
            if(dniFiltrar.equals("")){

            }else{

            }
        }else{
            if(dniFiltrar.equals("")){

            }
        }
//        switch (estado) {
//            case "Resuelta":
//                if (nombreFiltrar.equals("")) {
//                    filtro = " WHERE " + INC_ESTADO + "=1";
//                } else {
//                    filtro = " WHERE " + INC_ESTADO + "=1 AND " + INC_FECHA_INICIO + "='" + nombreFiltrar + "'";
//                }
//                break;
//            case "No Resuelta":
//                if (nombreFiltrar.equals("")) {
//                    filtro = " WHERE " + INC_ESTADO + "=0";
//                } else {
//                    filtro = " WHERE " + INC_ESTADO + "=0 AND " + INC_FECHA_INICIO + "='" + nombreFiltrar + "'";
//                }
//                break;
//            default:
//                if (!nombreFiltrar.equals("")) {
//                    filtro = " WHERE " + INC_FECHA_INICIO + "='" + nombreFiltrar + "'";
//                }
//        }

        return filtro;
    }

    /**
     * Elimino el filtro
     */
    private void eliminarFiltro(){
        etDniFiltrar.setText("");
        etNombreFiltrar.setText("");
    }
}
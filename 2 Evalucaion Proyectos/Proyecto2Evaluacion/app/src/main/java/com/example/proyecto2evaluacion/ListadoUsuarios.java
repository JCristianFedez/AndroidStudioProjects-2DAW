package com.example.proyecto2evaluacion;

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

public class ListadoUsuarios extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private ListView lvListaUsuarios;
    private ProyectoSQLiteHelper prdbh;
    private Cursor c;

    private EditText etNombreFiltrar;
    private EditText etDniFiltrar;
    private Button btnEliminarUsuario;
    private Button btnModificarUsuario;
    private Button btnFiltrarUsuario;
    private Button btnEliminarFiltro;

    private static final String USER_TABLE_NAME = "usuario";
    private static final String USER_ID = "id";
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

    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvListaUsuarios = (ListView) findViewById(R.id.lvListaUsuarios);

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
                actualizarUsuario(etDniFiltrar.getText().toString(),etNombreFiltrar.getText().toString());
                eliminarFiltro();
                muestraUsuario();

                break;

            case R.id.btnEliminarUsuario:
                eliminaUsuario(etDniFiltrar.getText().toString());
                eliminarFiltro();
                muestraUsuario();
                break;
        }
    }

    /**
     * Muestra un listado con todos los usuarios
     */
    private void muestraUsuario(){
        String filtro = filtro();
        prdbh = new ProyectoSQLiteHelper(this,"DBProyecto",null,1);

        db = prdbh.getReadableDatabase();

//        tvListadoUsuarios = (TextView) findViewById(R.id.tvListadoUsuarios);
        if(db != null){
            String[] args;
            Boolean vacia = true;
            lvListaUsuarios.setAdapter(null);
            String queryConCantIncidencias = "SELECT "
                    + USER_TABLE_NAME + "." + USER_NOM + ", "
                    + USER_TABLE_NAME + "." + USER_DNI + ", "
                    + USER_TABLE_NAME + "." + USER_PERFIL + ", "
                    + " Count(" + INC_TABLE_NAME + "." + INC_DNI + ") AS Incidencias,"
                    + USER_TABLE_NAME + "." + USER_FOTO
                    + " FROM " + USER_TABLE_NAME
                    + " LEFT JOIN " + INC_TABLE_NAME + " ON " + INC_TABLE_NAME + "." + INC_DNI + "=" + USER_TABLE_NAME + "." +USER_DNI
                    + filtro
                    + " GROUP BY " + USER_TABLE_NAME + "." + USER_NOM + ", " + USER_TABLE_NAME + "." + USER_DNI;

            c = db.rawQuery(queryConCantIncidencias, null);

            if(c.moveToFirst()){
                ArrayList<ItemListview> listUsuarios = new ArrayList<>();
                vacia=false;
                do{
                    String nombre = c.getString(0);
                    String dni = c.getString(1);
                    int perfil = c.getInt(2);
                    int cant = c.getInt(3);
                    String uriFoto = c.getString(4);
                    String admin = (perfil==1)?"Administrador":"Usuario";
                    listUsuarios.add(new ItemListview(uriFoto,dni,nombre,admin,String.valueOf(cant)));
                }while(c.moveToNext());
                adaptador = new Adaptador(this, listUsuarios);
                lvListaUsuarios.setAdapter(adaptador);

            }

            if(vacia){
                Toast.makeText(this, "No hay registros en la base de datos", Toast.LENGTH_SHORT).show();
            }


            db.close();
            c.close();
        }
    }

    /**
     * Elimina al usuario con dni <code>dni</code>
     * @param dni
     */
    private void eliminaUsuario(String dni){
        if(dni.equals("")){
            Toast.makeText(this, "Debe introducir DNI del usuario a eliminar", Toast.LENGTH_SHORT).show();
        }else{
            db = prdbh.getReadableDatabase();
            String[] args = new String[]{dni};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() != 0) {
                db.delete(USER_TABLE_NAME, USER_DNI + "=?", args);
                Toast.makeText(this, "Usuario con dni " + dni + " eliminado", Toast.LENGTH_SHORT).show();

                //Elimino las incidencias que ha tenido el usuario
                db.delete(INC_TABLE_NAME,INC_DNI + "=?",args);

                //TODO: Comprobar si el usuaroi eliminado es Administrador que todaas las incidencias en las que es responsable se cambien a "Ninguno"

            } else {
                Toast.makeText(this, "No existe ningun usuario con dicho DNI", Toast.LENGTH_SHORT).show();
            }
            c.close();
            db.close();
        }
    }

    /**
     * Cambia el nombre del usuario con <code>dni</code> a <code>nuevoNombre</code>
     * @param dni
     * @param nuevoNombre
     */
    private void actualizarUsuario(String dni, String nuevoNombre){
        if(dni.equals("") || nuevoNombre.equals("")){
            Toast.makeText(this, "Debe introducir DNI y nombre", Toast.LENGTH_SHORT).show();
        }else{
            db = prdbh.getReadableDatabase();
            String[] args = new String[]{dni};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() != 0) {
                ContentValues cv = new ContentValues();
                cv.put(USER_NOM,nuevoNombre);
                db.update(USER_TABLE_NAME,cv,USER_DNI + "=?",args);
                Toast.makeText(this, "Usuario con dni " + dni + " ahora se llama " + nuevoNombre, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "N existe ningun usuario con dicho DNI", Toast.LENGTH_SHORT).show();
            }
            c.close();
            db.close();
        }
    }
    /**
     * Devuevlve el filtro
     *
     * @return
     */
    private String filtro() {
        String nombreFiltrar = etNombreFiltrar.getText().toString();
        String dniFiltrar = etDniFiltrar.getText().toString();

        String filtro = "";

        if(!nombreFiltrar.equals("")){
            if(!dniFiltrar.equals("")){
                filtro = " WHERE " + USER_TABLE_NAME + "." + USER_DNI + "='" + dniFiltrar +"'"
                        + " AND " +  USER_TABLE_NAME + "." +USER_NOM + "='" + nombreFiltrar +"'";
            }else{
                filtro = " WHERE " + USER_TABLE_NAME + "." + USER_NOM + "='" + nombreFiltrar +"'";
            }
        }else{
            if(!dniFiltrar.equals("")){
                filtro = " WHERE " +  USER_TABLE_NAME + "." + USER_DNI + "='" + dniFiltrar +"'";
            }
        }

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
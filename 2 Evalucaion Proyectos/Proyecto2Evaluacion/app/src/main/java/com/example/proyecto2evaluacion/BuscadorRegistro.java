package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BuscadorRegistro extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private Cursor c;
    private ProyectoSQLiteHelper prdbh;

    private static final String USER_TABLE_NAME = "usuario";
    private static final String USER_ID = "id";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private static final String REG_TABLE_NAME = "registro";
    private static final String REG_ID = "id";
    private static final String REG_NOMBRE = "nombre";
    private static final String REG_FECHA = "fecha";
    private static final String REG_ID_USER = "id_usuario";
    private static final String REG_DESC = "descripcion";
    private static final String REG_ACTIVO = "activo";

    private Button btnBuscarRegistros;
    private EditText etDniUsuarioRegistro;
    private Spinner spActivoRegistro;
    private TextView tvListadoRegistros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_registro);

        btnBuscarRegistros = (Button) findViewById(R.id.btnBuscarRegistros);
        etDniUsuarioRegistro = (EditText) findViewById(R.id.etDniUsuarioRegistro);
        spActivoRegistro = (Spinner) findViewById(R.id.spActivoRegistro);
        tvListadoRegistros = (TextView) findViewById(R.id.tvListadoRegistros);
        tvListadoRegistros.setMovementMethod(new ScrollingMovementMethod());

        loadSpinerActivo(spActivoRegistro);
        btnBuscarRegistros.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBuscarRegistros:
                mostrarDatos();
                break;
        }
    }

    private void mostrarDatos() {
        String filtro = filtro();
        prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);
        db = prdbh.getReadableDatabase();
        if (db != null) {
            String[] args;
            tvListadoRegistros.setText("");

            if(etDniUsuarioRegistro.getText().toString().equals("")
                    ||spActivoRegistro.getSelectedItem().toString().equals("Selecciona Actividad")){
                Toast.makeText(this, "Al no introducir criterios se muestran todos los registros", Toast.LENGTH_SHORT).show();
            }

            String queryConNombreUsuario = "SELECT "
                    + USER_TABLE_NAME + "." + USER_NOM + ", "
                    + USER_TABLE_NAME + "." + USER_AP + ", "
                    + REG_TABLE_NAME + "." + REG_NOMBRE + ", "
                    + REG_TABLE_NAME + "." + REG_FECHA + ", "
                    + REG_TABLE_NAME + "." + REG_DESC
                    + " FROM " + REG_TABLE_NAME
                    + " INNER JOIN " + USER_TABLE_NAME + " ON " + USER_TABLE_NAME + "." + USER_ID + "=" + REG_TABLE_NAME + "." + REG_ID_USER
                    + filtro;

            c = db.rawQuery(queryConNombreUsuario, null);

            if (c.moveToFirst()) {
                do {
                    String user_name = c.getString(0);
                    String user_ap = c.getString(1);
                    String reg_name = c.getString(2);
                    String reg_fecha = c.getString(3);
                    String reg_desc = c.getString(4);
                    tvListadoRegistros.append(
                            "USER-NAME: " + user_name + " | USER-AP: " + user_ap
                            + "\n"
                            + "REG-NAME: " + reg_name + " | REG-Fecha: " + reg_fecha
                            + "\n"
                            + "REG-DESC: " + reg_desc + "\n\n"
                    );
                    tvListadoRegistros.append("\n\n");

                } while (c.moveToNext());
            }

            if (tvListadoRegistros.getText().toString().equals("")) {
                tvListadoRegistros.setText("No hay registros en la Base de datos");
            }
            c.close();
            db.close();
        }
    }

    private String filtro() {
        prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);
        db = prdbh.getReadableDatabase();
        int id = 0;

        String filtro = "";
        String activo = spActivoRegistro.getSelectedItem().toString();

        switch (activo) {
            case "Activo":
                filtro = " WHERE " + REG_ACTIVO + "=1";
                break;
            case "No Activo":
                filtro = " WHERE " + REG_ACTIVO + "=0";
                break;
        }

        if(!etDniUsuarioRegistro.getText().toString().equals("")){
            if(db != null){
                // Consigo el ID del usuario
                String querryIdUsuario = "SELECT "
                        + USER_ID
                        + " FROM " + USER_TABLE_NAME
                        + " WHERE " + USER_DNI + "='" + etDniUsuarioRegistro.getText().toString() +"'";

                c = db.rawQuery(querryIdUsuario, null);

                if (c.moveToFirst()) {
                    do {
                        id = c.getInt(0);
                    } while (c.moveToNext());
                }

                if(filtro.equals("")){
                    filtro += " WHERE " + REG_TABLE_NAME + "." + REG_ID_USER + "='" + id + "'";
                }else{
                    filtro += " AND " + REG_TABLE_NAME + "." + REG_ID_USER + "='" + id + "'";

                }
                c.close();
                db.close();
            }
        }


        return filtro;
    }

    private void loadSpinerActivo(Spinner spinner) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Selecciona Actividad");

        list.add("Activo");
        list.add("No Activo");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, list
        );

        spinner.setAdapter(dataAdapter);

    }

}
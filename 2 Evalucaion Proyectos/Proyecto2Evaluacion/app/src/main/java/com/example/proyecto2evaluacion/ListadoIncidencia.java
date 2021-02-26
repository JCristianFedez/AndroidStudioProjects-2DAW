package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoIncidencia extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private Cursor c;
    private ProyectoSQLiteHelper prdbh;

    private TextView tvListadoIncidencias;
    private EditText etFiltroFecha;
    private Spinner spFiltroEstado;
    private Button btnFiltroIncidencia;
    private Button btnFiltroIncidenciaVaciar;

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
    private static final String INC_ID = "id";
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

        tvListadoIncidencias = (TextView) findViewById(R.id.tvListadoIncidencias);
        spFiltroEstado = (Spinner) findViewById(R.id.spFiltroEstado);
        etFiltroFecha = (EditText) findViewById(R.id.etFiltroFecha);
        btnFiltroIncidencia = (Button) findViewById(R.id.btnFiltroIncidencia);
        btnFiltroIncidenciaVaciar = (Button) findViewById(R.id.btnFiltroIncidenciaVaciar);

        etFiltroFecha.setOnClickListener(this);
        btnFiltroIncidencia.setOnClickListener(this);
        btnFiltroIncidenciaVaciar.setOnClickListener(this);

        loadSpinnerData(spFiltroEstado);
        mostrarDatos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etFiltroFecha:
                showDatePickerDialog(etFiltroFecha);
                break;

            case R.id.btnFiltroIncidencia:
                mostrarDatos();
                break;
            case R.id.btnFiltroIncidenciaVaciar:
                eliminarFiltro();
                mostrarDatos();
                break;
        }
    }

    /**
     * Muestro datos de incidencias
     */
    private void mostrarDatos() {
        String filtro = filtro();
        prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);

        db = prdbh.getReadableDatabase();

        if (db != null) {
            String[] args;
            tvListadoIncidencias.setText("");

            String query = "SELECT "
                    + INC_DNI + ", "
                    + INC_FECHA_INICIO + ", "
                    + INC_ESTADO + ", "
                    + INC_RESPONSABLE
                    + " FROM " + INC_TABLE_NAME
                    + filtro;

            String queryConNombreUsuario = "SELECT "
                    + INC_TABLE_NAME + "." + INC_DNI + ", "
                    + INC_TABLE_NAME + "." + INC_FECHA_INICIO + ", "
                    + INC_TABLE_NAME + "." + INC_ESTADO + ", "
                    + USER_TABLE_NAME + "." + USER_NOM
                    + " FROM " + INC_TABLE_NAME
                    + " LEFT JOIN " + USER_TABLE_NAME + " ON " + USER_TABLE_NAME + "." + USER_DNI + "=" + INC_TABLE_NAME + "." +INC_RESPONSABLE
                    + filtro;

            c = db.rawQuery(queryConNombreUsuario, null);

            if (c.moveToFirst()) {
                do {
                    String inc_dni = c.getString(0);
                    String fecha = c.getString(1);
                    String estado = (c.getInt(2) == 1) ? "Resuelta" : "No resuelta";
                    String responsable = c.getString(3);
                    tvListadoIncidencias.append(
                            "DNI: " + inc_dni + " | Fecha: " + fecha + " | Estado: " + estado + " | Responsable: " + responsable + "\n"
                    );
                    tvListadoIncidencias.append("\n\n");

                } while (c.moveToNext());
            }

            if (tvListadoIncidencias.getText().toString().equals("")) {
                tvListadoIncidencias.setText("No hay registros en la Base de datos");
            }
            c.close();
            db.close();
        }
    }

    /**
     * Funcion para cargar datos en el spinner desde la base de datos
     */
    private void loadSpinnerData(Spinner spinner) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Estado");

        prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);
        db = prdbh.getReadableDatabase();

        c = db.rawQuery(
                "SELECT DISTINCT " + INC_ESTADO + " FROM " + INC_TABLE_NAME
                , null
        );

        if (c.moveToFirst()) {
            do {
                list.add((c.getInt(0) == 1) ? "Resuelta" : "No Resuelta");//adding 1nd column data
            } while (c.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, list
        );

        spinner.setAdapter(dataAdapter);

        c.close();
        db.close();

    }


    /**
     * Devuevlve el filtro
     *
     * @return
     */
    private String filtro() {
        String fechaFiltrar = etFiltroFecha.getText().toString();
        String filtro = "";

        String estado = spFiltroEstado.getSelectedItem().toString();

        switch (estado) {
            case "Resuelta":
                if (fechaFiltrar.equals("")) {
                    filtro = " WHERE " + INC_ESTADO + "=1";
                } else {
                    filtro = " WHERE " + INC_ESTADO + "=1 AND " + INC_FECHA_INICIO + "='" + fechaFiltrar + "'";
                }
                break;
            case "No Resuelta":
                if (fechaFiltrar.equals("")) {
                    filtro = " WHERE " + INC_ESTADO + "=0";
                } else {
                    filtro = " WHERE " + INC_ESTADO + "=0 AND " + INC_FECHA_INICIO + "='" + fechaFiltrar + "'";
                }
                break;
            default:
                if (!fechaFiltrar.equals("")) {
                    filtro = " WHERE " + INC_FECHA_INICIO + "='" + fechaFiltrar + "'";
                }
        }

        return filtro;
    }

    /**
     * Elimino el filtro
     */
    private void eliminarFiltro(){
        etFiltroFecha.setText("");
        spFiltroEstado.setSelection(0);
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;
                editText.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

}
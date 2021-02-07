package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class alta_incidencia extends AppCompatActivity implements View.OnClickListener {

    private static final String TABLE_NAME = "incidencia";
    private static final String INC_DNI = "dni";
    private static final String INC_FECHA_INICIO = "fecha_inicio";
    private static final String INC_OBSER = "observacion";
    private static final String INC_RESPONSABLE = "dni_responsable";
    private static final String INC_ESTADO = "estado";
    private static final String INC_FECHA_FIN = "fecha_resolucion";

    private SQLiteDatabase dbUsuario;
    private SQLiteDatabase dbIncidencia;
    private ContentValues querry;

    private EditText etDniIncidencia;
    private EditText etFechaInicio;
    private EditText etFechaFin;
    private EditText etObservaciones;
    private EditText etDniResponsable;
    private CheckBox cbResuelta;
    private Button btnInsertarIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_incidencia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        etDniIncidencia = (EditText) findViewById(R.id.etDniIncidencia);
        etFechaInicio = (EditText) findViewById(R.id.etFechaInicio);
        etFechaFin = (EditText) findViewById(R.id.etFechaFin);
        etObservaciones = (EditText) findViewById(R.id.etObservaciones);
        etDniResponsable = (EditText) findViewById(R.id.etDniResponsable);
        cbResuelta = (CheckBox) findViewById(R.id.cbResuelta);
        btnInsertarIncidencia = (Button) findViewById(R.id.btnInsertarIncidencia);

        btnInsertarIncidencia.setOnClickListener(this);
        etFechaInicio.setOnClickListener(this);
        etFechaFin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etFechaInicio:
                showDatePickerDialog(etFechaInicio);
                break;

            case R.id.etFechaFin:
                showDatePickerDialog(etFechaFin);
                break;

            case R.id.btnInsertarIncidencia:
                insertarIncidencia(v);
                break;
        }
    }

    public void insertarIncidencia(View v) {
        //TODO: No permitir que la fecha de fin sea menor a la fecha de inicio
        UsuarioSQLiteHelper indbh = new UsuarioSQLiteHelper(this, "DBUsuario", null, 1);
        dbUsuario = indbh.getWritableDatabase();

        IncidenciaSQLiteHelper usdbh = new IncidenciaSQLiteHelper(this, "DBIncidencia", null, 1);
        dbIncidencia = usdbh.getWritableDatabase();

        querry = new ContentValues();

        if (dbIncidencia != null && dbUsuario != null) {
            Cursor c;
            String[] args;
            Snackbar snackbar;

            if (cbResuelta.isChecked() && etFechaFin.getText().toString().equals("")) {

                snackbar = Snackbar.make(v, "Completa los datos", Snackbar.LENGTH_LONG);
                snackbar.show();

            } else if (!cbResuelta.isChecked() && !etFechaFin.getText().toString().equals("")) {
                snackbar = Snackbar.make(v, "Datos invalidos, no puede estar no resuelta y tener fecha fin", Snackbar.LENGTH_LONG);
                snackbar.show();

            } else if (etDniIncidencia.getText().toString().trim().equals("") ||
                    etFechaInicio.getText().toString().trim().equals("") ||
                    etObservaciones.getText().toString().trim().equals("") ||
                    etDniResponsable.getText().toString().trim().equals("")) {
                //Obligar a introducir todos los datos
                snackbar = Snackbar.make(v, "Completa los datos", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {

                args = new String[]{etDniResponsable.getText().toString()};
                c = dbUsuario.rawQuery(" SELECT * FROM usuario WHERE dni=? ", args);
                if (c.getCount() == 0) {
                    //Compruebo que el responsable existe
                    snackbar = Snackbar.make(v, "Rsponsable con DNI   " + etDniResponsable.getText().toString() + " no existente", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {

                    args = new String[]{etDniIncidencia.getText().toString()};
                    c = dbIncidencia.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE dni=? ", args);
                    if (c.getCount() != 0) {
                        //Compruebo que el DNI de la incidencia no existe
                        snackbar = Snackbar.make(v, "Incidencia con DNI  " + etDniIncidencia.getText().toString() + " ya existente", Snackbar.LENGTH_LONG);
                        snackbar.show();

                    } else {

                        int resuelta = (cbResuelta.isChecked()) ? 1 : 0;
                        querry.put(INC_DNI, etDniIncidencia.getText().toString());
                        querry.put(INC_FECHA_INICIO, etFechaInicio.getText().toString());
                        querry.put(INC_FECHA_FIN, etFechaFin.getText().toString());
                        querry.put(INC_OBSER, etObservaciones.getText().toString());
                        querry.put(INC_RESPONSABLE, etDniResponsable.getText().toString());
                        querry.put(INC_ESTADO, resuelta);
                        dbIncidencia.insert(TABLE_NAME, null, querry);
                        snackbar = Snackbar.make(v, "Incidencia con DNI  " + etDniIncidencia.getText().toString() + " añadido", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        vaciarEditText();
                    }
                }
                c.close();
            }
            dbIncidencia.close();
            dbUsuario.close();
        }

    }

    public void vaciarEditText() {
        etDniIncidencia.setText("");
        etFechaInicio.setText("");
        etFechaFin.setText("");
        etObservaciones.setText("");
        etDniResponsable.setText("");
        cbResuelta.setChecked(false);
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
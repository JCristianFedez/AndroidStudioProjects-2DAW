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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AltaIncidencia extends AppCompatActivity implements View.OnClickListener {

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

    private SQLiteDatabase db;
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
                etFechaInicio.setText("");
                showDatePickerDialog(etFechaInicio);
                break;

            case R.id.etFechaFin:
                etFechaFin.setText("");
                showDatePickerDialog(etFechaFin);
                break;

            case R.id.btnInsertarIncidencia:
                insertarIncidencia(v);
                break;
        }
    }

    private void insertarIncidencia(View v) {
        ProyectoSQLiteHelper prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);
        db = prdbh.getWritableDatabase();

        querry = new ContentValues();

        if (db != null) {
            Cursor c;
            String[] args;
            Snackbar snackbar;


            //Si tiene fecha de fin pero no esta resuelta
            if (!cbResuelta.isChecked() && !etFechaFin.getText().toString().equals("")) {
                snackbar = Snackbar.make(v, "Datos invalidos, no puede estar no resuelta y tener fecha fin", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                return;
            }

            // Si esta resuelta pero no tiene fecha de fin
            if (cbResuelta.isChecked() && etFechaFin.getText().toString().equals("")) {
                snackbar = Snackbar.make(v, "Si la incidencia esta resuelta tiene que tener fecha de fin", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                return;
            }

            // Compruebo que la fecha de fin sea posterior a la de inicio
            if(!etFechaInicio.getText().toString().trim().equals("") && !etFechaFin.getText().toString().trim().equals("")){
                SimpleDateFormat formatEntrada = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date inicio = formatEntrada.parse(etFechaInicio.getText().toString());
                    Date fin = formatEntrada.parse(etFechaFin.getText().toString());
                    if(inicio.after(fin)){
                        snackbar = Snackbar.make(v, "La fecha de fin debe ser posterior a la de inicio", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        db.close();
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            //Obligar a introducir todos los datos
            if (etDniIncidencia.getText().toString().trim().equals("") ||
                    etFechaInicio.getText().toString().trim().equals("") ||
                    etObservaciones.getText().toString().trim().equals("") ||
                    etDniResponsable.getText().toString().trim().equals("")) {
                snackbar = Snackbar.make(v, "Completa los datos", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                return;
            }

            //Compruebo que el existe un usuario con dicho DNI
            args = new String[]{etDniIncidencia.getText().toString()};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "No existe usuario con DNI  " + etDniIncidencia.getText().toString(), Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            //Compruebo que el responsable existe
            args = new String[]{etDniResponsable.getText().toString()};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "Rsponsable con DNI " + etDniResponsable.getText().toString() + " no existente", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            // Compruebo que el usuario responsable sea de tipo administrador
            args = new String[]{etDniResponsable.getText().toString(),"1"};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? AND " + USER_PERFIL + "=?", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "El usuario con " + etDniResponsable.getText().toString() + " no es administrador, por lo que no puede ser responsable", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }


            int resuelta = (cbResuelta.isChecked()) ? 1 : 0;
            querry.put(INC_DNI, etDniIncidencia.getText().toString());
            querry.put(INC_FECHA_INICIO, etFechaInicio.getText().toString());
            querry.put(INC_FECHA_FIN, etFechaFin.getText().toString());
            querry.put(INC_OBSER, etObservaciones.getText().toString());
            querry.put(INC_RESPONSABLE, etDniResponsable.getText().toString());
            querry.put(INC_ESTADO, resuelta);
            db.insert(INC_TABLE_NAME, null, querry);
            snackbar = Snackbar.make(v, "Incidencia añadida", Snackbar.LENGTH_LONG);
            snackbar.show();
            vaciarEditText();
            c.close();
            db.close();
        }

    }

    private void vaciarEditText() {
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
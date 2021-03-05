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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AltaRegistro extends AppCompatActivity implements View.OnClickListener {

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

    private SQLiteDatabase db;
    private ContentValues querry;

    private TextView tvDatosUsuarioRegistro;
    private EditText etDniRegistroUsuario;
    private EditText etNombreRegistro;
    private EditText etFechaRegistro;
    private EditText etDescripcionRegistro;
    private CheckBox cbActivo;
    private Button btnInsertarRegistro;
    private ImageButton btnBuscarUsuarioDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_registro);

        tvDatosUsuarioRegistro = (TextView) findViewById(R.id.tvDatosUsuarioRegistro);

        etDniRegistroUsuario = (EditText) findViewById(R.id.etDniRegistroUsuario);
        etNombreRegistro = (EditText) findViewById(R.id.etNombreRegistro);
        etFechaRegistro = (EditText) findViewById(R.id.etFechaRegistro);
        etDescripcionRegistro = (EditText) findViewById(R.id.etDescripcionRegistro);

        cbActivo = (CheckBox) findViewById(R.id.cbActivo);

        btnInsertarRegistro = (Button) findViewById(R.id.btnInsertarRegistro);
        btnBuscarUsuarioDni = (ImageButton) findViewById(R.id.btnBuscarUsuarioDni);

        btnInsertarRegistro.setOnClickListener(this);
        btnBuscarUsuarioDni.setOnClickListener(this);
        etFechaRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etFechaRegistro:
                etFechaRegistro.setText("");
                showDatePickerDialog(etFechaRegistro);
                break;
            case R.id.btnInsertarRegistro:
                insertarIncidencia(v);
                break;
            case R.id.btnBuscarUsuarioDni:
                mostrarDatosUsuario(etDniRegistroUsuario.getText().toString(),v);
                break;
        }
    }

    private void mostrarDatosUsuario(String dni, View v){
        etDescripcionRegistro.setText("");
        ProyectoSQLiteHelper prdbh = new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);
        db = prdbh.getReadableDatabase();
        querry = new ContentValues();

        if (db != null) {
            Cursor c;
            String[] args;
            Snackbar snackbar;


            //Obligar a introducir todos los datos
            if (dni.equals("")) {
                snackbar = Snackbar.make(v, "Completa los datos", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                return;
            }

            //Compruebo que el existe un usuario con dicho DNI
            args = new String[]{etDniRegistroUsuario.getText().toString()};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "No existe usuario con DNI " + etDniRegistroUsuario.getText().toString(), Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            // Compruebo que el usuario sea de tipo administrador
            args = new String[]{etDniRegistroUsuario.getText().toString(),"1"};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? AND " + USER_PERFIL + "=?", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "El usuario con " + etDniRegistroUsuario.getText().toString() + " no es administrador", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            String querryNombreUsuario = "SELECT "
                    + USER_NOM + ", "
                    + USER_AP
                    + " FROM " + USER_TABLE_NAME
                    + " WHERE " + USER_DNI + "='" + dni+"'";

            c = db.rawQuery(querryNombreUsuario, null);

            if (c.moveToFirst()) {
                do {
                    String nombre = c.getString(0);
                    String apellidos = c.getString(1);
                    tvDatosUsuarioRegistro.append(
                            "NOMBRE: " + nombre + " | APELLIDOS: "+ apellidos +"\n"
                    );

                } while (c.moveToNext());
            }

            c.close();
            db.close();
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
            int id = 0;


            //Obligar a introducir todos los datos
            if (etDniRegistroUsuario.getText().toString().trim().equals("") ||
                    etNombreRegistro.getText().toString().trim().equals("") ||
                    etFechaRegistro.getText().toString().trim().equals("") ||
                    etDescripcionRegistro.getText().toString().trim().equals("")) {
                snackbar = Snackbar.make(v, "Completa los datos", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                return;
            }

            //Compruebo que el existe un usuario con dicho DNI
            args = new String[]{etDniRegistroUsuario.getText().toString()};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? ", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "No existe usuario con DNI  " + etDniRegistroUsuario.getText().toString(), Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            // Compruebo que el usuario sea de tipo administrador
            args = new String[]{etDniRegistroUsuario.getText().toString(),"1"};
            c = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_DNI + "=? AND " + USER_PERFIL + "=?", args);
            if (c.getCount() == 0) {
                snackbar = Snackbar.make(v, "El usuario con " + etDniRegistroUsuario.getText().toString() + " no es administrador", Snackbar.LENGTH_LONG);
                snackbar.show();
                db.close();
                c.close();
                return;
            }

            String querryIdUsuario = "SELECT "
                    + USER_ID
                    + " FROM " + USER_TABLE_NAME
                    + " WHERE " + USER_DNI + "='" + etDniRegistroUsuario.getText().toString() +"'";

            c = db.rawQuery(querryIdUsuario, null);

            if (c.moveToFirst()) {
                do {
                    id = c.getInt(0);
                } while (c.moveToNext());
            }

            int activa = (cbActivo.isChecked()) ? 1 : 0;
            querry.put(REG_ID_USER, id);
            querry.put(REG_NOMBRE, etNombreRegistro.getText().toString());
            querry.put(REG_FECHA, etFechaRegistro.getText().toString());
            querry.put(REG_DESC, etDescripcionRegistro.getText().toString());
            querry.put(REG_ACTIVO, activa);
            db.insert(REG_TABLE_NAME, null, querry);
            Toast.makeText(this, "Registro añadido", Toast.LENGTH_SHORT).show();
            vaciarEditText();
            c.close();
            db.close();
        }

    }

    private void vaciarEditText() {
        etDniRegistroUsuario.setText("");
        etDescripcionRegistro.setText("");
        etFechaRegistro.setText("");
        etNombreRegistro.setText("");
        tvDatosUsuarioRegistro.setText("");
        cbActivo.setChecked(false);
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
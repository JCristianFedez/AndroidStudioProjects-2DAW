package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class alta_usuario extends AppCompatActivity implements View.OnClickListener {

    private static final String TABLE_NAME = "usuario";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etDni;
    private EditText etUsuari;
    private EditText etPassword;
    private Button btnInsertarUsuario;
    private Button btnElegirImagen;
    private RadioGroup rgPerfil;
    private RadioButton rbPerfilSeleccionado;
    private SQLiteDatabase db;
    private ContentValues querry;

    //TODO: Recojer imagen al pulsar el boton y guardarla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etDni = (EditText) findViewById(R.id.etDni);
        etUsuari = (EditText) findViewById(R.id.etUsuari);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnInsertarUsuario = (Button) findViewById(R.id.btnInsertarIncidencia);
        btnElegirImagen = (Button) findViewById(R.id.btnElegirImagen);
        rgPerfil = (RadioGroup) findViewById(R.id.rgPerfil);
        btnElegirImagen.setOnClickListener(this);
        btnInsertarUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnElegirImagen:
                break;
            case R.id.btnInsertarIncidencia:
                insertarUsuario();
                break;
        }
    }

    public void insertarUsuario() {
        ProyectoSQLiteHelper prdbh =
                new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);

        db = prdbh.getWritableDatabase();

        querry = new ContentValues();

        if (db != null) {
            Cursor c;
            String[] args;

            int radioSelectId = rgPerfil.getCheckedRadioButtonId();
            rbPerfilSeleccionado = (RadioButton) findViewById(radioSelectId);
            if (radioSelectId == -1) {
                Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();

                //Obligar a introducir todos los datos
            } else if (etNombre.getText().toString().trim().equals("") ||
                        etApellidos.getText().toString().trim().equals("") ||
                        etDni.getText().toString().trim().equals("") ||
                        etUsuari.getText().toString().trim().equals("") ||
                        etPassword.getText().toString().trim().equals("") ||
                        rbPerfilSeleccionado.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();

                } else {
                    int perfil = (rbPerfilSeleccionado.getText().toString().equals("Admin")) ? 1 : 0;
                    args = new String[]{etDni.getText().toString()};
                    c = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE "+USER_DNI+"=? ", args);
                    int a = c.getCount();
                    if (c.getCount() == 0) {
                        querry.put(USER_DNI, etDni.getText().toString());
                        querry.put(USER_NOM, etNombre.getText().toString());
                        querry.put(USER_AP, etApellidos.getText().toString());
                        querry.put(USER_US, etUsuari.getText().toString());
                        querry.put(USER_PASS, etPassword.getText().toString());
                        querry.put(USER_PERFIL, perfil);
                        db.insert(TABLE_NAME, null, querry);
                        Toast.makeText(getApplicationContext(), "usuario " + etNombre.getText().toString() + " añadido", Toast.LENGTH_SHORT).show();
                        vaciarEditText();

                    } else {
                        Toast.makeText(getApplicationContext(), "usuario con dni  " + etDni.getText().toString() + " ya existente", Toast.LENGTH_SHORT).show();
                    }
                c.close();
                }
            db.close();
        }
    }

    public void vaciarEditText() {
        etNombre.setText("");
        etApellidos.setText("");
        etDni.setText("");
        etUsuari.setText("");
        etPassword.setText("");
        rgPerfil.clearCheck();
    }

}
package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListaUsuarios;
    private Button btnListaIncidencias;
    private Button btnBuscadorRegistro;
    private Button btnAltaUsuario;
    private Button btnAltaIncidencia;
    private Button btnAltaRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListaUsuarios = (Button) findViewById(R.id.btnListaUsuarios);
        btnListaIncidencias = (Button) findViewById(R.id.btnListaIncidencias);
        btnBuscadorRegistro = (Button) findViewById(R.id.btnBuscadorRegistro);
        btnAltaUsuario = (Button) findViewById(R.id.btnAltaUsuario);
        btnAltaIncidencia = (Button) findViewById(R.id.btnAltaIncidencia);
        btnAltaRegistro = (Button) findViewById(R.id.btnAltaRegistro);

        btnListaUsuarios.setOnClickListener(this);
        btnListaIncidencias.setOnClickListener(this);
        btnBuscadorRegistro.setOnClickListener(this);
        btnAltaUsuario.setOnClickListener(this);
        btnAltaIncidencia.setOnClickListener(this);
        btnAltaRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnListaUsuarios:
                intent = new Intent(this, ListadoUsuarios.class);
                startActivity(intent);
                break;

            case R.id.btnListaIncidencias:
                intent = new Intent(this, ListadoIncidencia.class);
                startActivity(intent);
                break;

            case R.id.btnBuscadorRegistro:
                intent = new Intent(this, BuscadorRegistro.class);
                startActivity(intent);
                break;

            case R.id.btnAltaUsuario:
                intent = new Intent(this, AltaUsuario.class);
                startActivity(intent);
                break;

            case R.id.btnAltaIncidencia:
                intent = new Intent(this, AltaIncidencia.class);
                startActivity(intent);
                break;

            case R.id.btnAltaRegistro:
                intent = new Intent(this, AltaRegistro.class);
                startActivity(intent);
                break;
        }
    }
}
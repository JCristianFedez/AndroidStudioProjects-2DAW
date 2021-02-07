package com.example.proyecto2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListaUsuarios;
    private Button btnListaIncidencias;
    private Button btnAltaUsuario;
    private Button btnAltaIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListaUsuarios = (Button) findViewById(R.id.btnListaUsuarios);
        btnListaIncidencias = (Button) findViewById(R.id.btnListaIncidencias);
        btnAltaUsuario = (Button) findViewById(R.id.btnAltaUsuario);
        btnAltaIncidencia = (Button) findViewById(R.id.btnAltaIncidencia);

        btnListaUsuarios.setOnClickListener(this);
        btnListaIncidencias.setOnClickListener(this);
        btnAltaUsuario.setOnClickListener(this);
        btnAltaIncidencia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnListaUsuarios:
                intent = new Intent(this, listado_usuarios.class);
                startActivity(intent);
                break;
            case R.id.btnListaIncidencias:
                intent = new Intent(this, listado_incidencias.class);
                startActivity(intent);
                break;
            case R.id.btnAltaUsuario:
                intent = new Intent(this, alta_usuario.class);
                startActivity(intent);
                break;
            case R.id.btnAltaIncidencia:
                intent = new Intent(this, alta_incidencia.class);
                startActivity(intent);
                break;
        }
    }
}
package com.example.fernandezcristian_practica_sqlite01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInsert;
    private Button btnDelete;
    private Button btnUpdate;
    private Button btnBuscar;
    private EditText etName;
    private EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsertar);
        btnDelete = (Button) findViewById(R.id.btnDel);
        btnUpdate = (Button) findViewById(R.id.btnAct);
        btnBuscar = (Button) findViewById(R.id.btnConsulta);

        etCode = (EditText) findViewById(R.id.etCodigo);
        etName = (EditText) findViewById(R.id.etNombre);

        etCode.setText("");
        etName.setText("");

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AndroidBaseDatos.class);
        String action = "none";
        String code = etCode.getText().toString();
        String name = etName.getText().toString();
        String datosFaltantes = "";

        switch (v.getId()) {
            case R.id.btnInsertar:
                action = "insert";
                if (code.length() <= 0 && name.length() <= 0) {
                    datosFaltantes = "Falto introducir nombre y codigo";
                } else if (code.length() <= 0) {
                    datosFaltantes = "Falto introducir codigo";
                } else if (name.length() <= 0) {
                    datosFaltantes = "Falto introducir nombre";
                }
                break;

            case R.id.btnAct:
                action = "upload";
                if (code.length() <= 0 && name.length() <= 0) {
                    datosFaltantes = "Falto introducir nombre y codigo";
                } else if (code.length() <= 0) {
                    datosFaltantes = "Falto introducir codigo";
                } else if (name.length() <= 0) {
                    datosFaltantes = "Falto introducir nombre";
                }
                break;

            case R.id.btnDel:
                action = "delete";
                if (code.length() <= 0) {
                    datosFaltantes = "Debe introducir codigo del alumno a eliminar";
                }

                break;
            case R.id.btnConsulta:
                action = "buscar";
        }



        //Envio a la siguiente actividad o muestro datos faltantes
        if (datosFaltantes == "") {
            intent.putExtra("action", action);
            intent.putExtra("code", code);
            intent.putExtra("name", name);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), datosFaltantes, Toast.LENGTH_SHORT).show();

        }
    }
}
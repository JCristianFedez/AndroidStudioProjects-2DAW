package com.example.proyectoprimertrimestre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class DatosUsuarios extends AppCompatActivity implements View.OnClickListener {

    private final static int CODIGO_CREAR = 1;
    private Gson gson;
    private TextView tvNameYApell;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDireccion;
    private TextView tvFechaNacimiento;
    private TextView tvSexo;
    private TextView tvIntereses;
    private EditText etSug1;
    private EditText etSug2;
    private EditText etSug3;
    private Button btnBacktoForm;
    private Button btnEnviarValoracion;
    private Button btnSug;
    private AlertDialog.Builder myBuilder;
    private String newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuarios);
        gson = new Gson();

        tvNameYApell = (TextView) findViewById(R.id.tvNameYApell);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        tvFechaNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvSexo = (TextView) findViewById(R.id.tvSexo);
        tvIntereses = (TextView) findViewById(R.id.tvIntereses);

        etSug1 = (EditText) findViewById(R.id.etSug1);
        etSug2 = (EditText) findViewById(R.id.etSug2);
        etSug3 = (EditText) findViewById(R.id.etSug3);

        btnBacktoForm = (Button) findViewById(R.id.btnBacktoForm);
        btnEnviarValoracion = (Button) findViewById(R.id.btnEnviarValoracion);
        btnSug = (Button) findViewById(R.id.btnSug);

        btnBacktoForm.setOnClickListener(this);
        btnEnviarValoracion.setOnClickListener(this);
        btnSug.setOnClickListener(this);
        myBuilder = new AlertDialog.Builder(this);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "Datos no recibidos", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //SE crea un usuario nuevo con los datos enviados en gson
            Usuario user = gson.fromJson(getIntent().getStringExtra("datos"), Usuario.class);

            tvNameYApell.setText("Nombre: " + user.getNombre() + ", " + user.getApellidos());
            tvTelefono.setText("Tfn: " + String.valueOf(user.getTelefono()));
            tvEmail.setText("Email: " + user.getEmail());
            tvDireccion.setText("Adress: " + user.getDireccion());
            tvFechaNacimiento.setText("Nacimiento: " + user.getDireccion());
            tvSexo.setText("Sexo: " + user.getSexo());
            tvIntereses.setText("Intereses: " + user.getIntereses());

//            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBacktoForm:
                Intent back = new Intent(this, AltaUsuario.class);
                startActivity(back);
                finish();
                break;
            case R.id.btnEnviarValoracion:
                Usuario user = gson.fromJson(getIntent().getStringExtra("datos"), Usuario.class);


                //Paso a un strin Gson
                String cadenaUser = gson.toJson(user);

                //Envio el usuario
                Intent enviar1 = new Intent(this, MainActivity.class);
                //Intent enviar = new Intent(this,MainActivity.class);
                enviar1.putExtra("datos", cadenaUser);
                startActivity(enviar1);
                break;
            case R.id.btnSug:
                if (etSug1.getText().toString().trim().equals("") ||
                        etSug2.getText().toString().trim().equals("") ||
                        etSug3.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                newName = "";
                if (etSug1.getText().toString().length() < 2) {
                    newName += etSug1.getText().toString();
                } else {
                    newName += etSug1.getText().toString().substring(0, 2);
                }

                if (etSug2.getText().toString().length() < 2) {
                    newName += etSug2.getText().toString();
                } else {
                    newName += etSug2.getText().toString().substring(0, 2);
                }

                if (etSug3.getText().toString().length() < 2) {
                    newName += etSug3.getText().toString();
                } else {
                    newName += etSug3.getText().toString().substring(0, 2);
                }


                etSug1.setText("");
                etSug2.setText("");
                etSug3.setText("");

                String title = "Quiere actualizar su nombre";

                myBuilder.setMessage("Nuevo nombre: " + newName).setTitle(title);
                myBuilder.setCancelable(false);//NO dejo cerrar la ventana

                myBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {//Boton Si
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();Cerrar APP
                        tvNameYApell.setText("Nombre: " + newName);
                    }
                });

                myBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {//Boton No
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

                //Creo la caja de dialogo
                AlertDialog alert = myBuilder.create();

                alert.show();
                break;
        }
    }

}
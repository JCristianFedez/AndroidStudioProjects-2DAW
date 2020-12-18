package com.example.proyectoprimertrimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

public class AltaUsuario extends AppCompatActivity implements View.OnClickListener {

    private final static int CODIGO_CREAR = 1;
    private Button btnBacktoInicio;
    private Button btnEnviarDatos;
    private Button btnVerDatos;
    private EditText etNombre;
    private EditText etApellidos;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDireccion;
    private EditText etFechaNacimiento;
    private RadioGroup rgSexo;
    private RadioButton rbSexoSeleccionado;
    private CheckBox cbCine;
    private CheckBox cbArte;
    private CheckBox cbDeporte;
    private Gson gson;
    private int radioSelectId;
    private String intereses;
    private String auxIntereses;
    private String cadenaUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);

        gson = new Gson();

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);

        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        cbCine = (CheckBox) findViewById(R.id.cbCine);
        cbArte = (CheckBox) findViewById(R.id.cbArte);
        cbDeporte = (CheckBox) findViewById(R.id.cbDeporte);

        btnBacktoInicio = (Button) findViewById(R.id.btnVerDatos);
        btnEnviarDatos = (Button) findViewById(R.id.btnEnviarDatos);
        btnVerDatos=(Button)findViewById(R.id.btnVerDatos);


        etFechaNacimiento.setOnClickListener(this);
        btnBacktoInicio.setOnClickListener(this);
        btnVerDatos.setOnClickListener(this);
        btnEnviarDatos.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etFechaNacimiento:
                showDatePickerDialog();
                break;

            case R.id.btnBackToInicio2:
                Intent intent = new Intent(this, MainActivity.class);
                finish(); //Cierro este activity y entro al alta
                startActivity(intent);
                break;

            case R.id.btnEnviarDatos:
                int radioSelectId = rgSexo.getCheckedRadioButtonId();
                rbSexoSeleccionado = (RadioButton) findViewById(radioSelectId);
//                Toast.makeText(this,
//                        rbSexoSeleccionado.getText(), Toast.LENGTH_SHORT).show();


                String intereses = "";
                String auxIntereses = "";//Servira para poner las comas y los espacios entre los intereses
                if (cbCine.isChecked()) {
                    intereses += auxIntereses + "Cine";
                    auxIntereses = ", ";
                }
                if (cbArte.isChecked()) {
                    intereses += auxIntereses + "Arte";
                    auxIntereses = ", ";
                }
                if (cbDeporte.isChecked()) {
                    intereses += auxIntereses + "Deportes";
                }
//                Toast.makeText(this,
//                        intereses, Toast.LENGTH_SHORT).show();

                //Obligar a introducir todos los datos
                if (etNombre.getText().toString().trim().equals("") ||
                        etApellidos.getText().toString().trim().equals("") ||
                        etTelefono.getText().toString().trim().equals("") ||
                        etEmail.getText().toString().trim().equals("") ||
                        etDireccion.getText().toString().trim().equals("") ||
                        etFechaNacimiento.getText().toString().trim().equals("") ||
                        rbSexoSeleccionado.getText().toString().trim().equals("") ||
                        intereses.trim().equals("")) {
                    Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Instancio nuevo Usuario
                Usuario user = new Usuario(etNombre.getText().toString(),
                        etApellidos.getText().toString(),
                        etEmail.getText().toString(),
                        Integer.parseInt(etTelefono.getText().toString()),
                        etDireccion.getText().toString(),
                        etFechaNacimiento.getText().toString(),
                        rbSexoSeleccionado.getText().toString(),
                        intereses);

                //Paso a un strin Gson
                cadenaUser = gson.toJson(user);

                //Envio el usuario
                Intent enviar = new Intent(this, MainActivity.class);
                //Intent enviar = new Intent(this,MainActivity.class);
                enviar.putExtra("datos", cadenaUser);
                startActivity(enviar);
                //finish();
                break;

            case R.id.btnVerDatos:
                radioSelectId = rgSexo.getCheckedRadioButtonId();
                rbSexoSeleccionado = (RadioButton) findViewById(radioSelectId);
//                Toast.makeText(this,
//                        rbSexoSeleccionado.getText(), Toast.LENGTH_SHORT).show();


                intereses = "";
                auxIntereses = "";//Servira para poner las comas y los espacios entre los intereses
                if (cbCine.isChecked()) {
                    intereses += auxIntereses + "Cine";
                    auxIntereses = ", ";
                }
                if (cbArte.isChecked()) {
                    intereses += auxIntereses + "Arte";
                    auxIntereses = ", ";
                }
                if (cbDeporte.isChecked()) {
                    intereses += auxIntereses + "Deportes";
                }
//                Toast.makeText(this,
//                        intereses, Toast.LENGTH_SHORT).show();

                //Obligar a introducir todos los datos
                if (etNombre.getText().toString().trim().equals("") ||
                        etApellidos.getText().toString().trim().equals("") ||
                        etTelefono.getText().toString().trim().equals("") ||
                        etEmail.getText().toString().trim().equals("") ||
                        etDireccion.getText().toString().trim().equals("") ||
                        etFechaNacimiento.getText().toString().trim().equals("") ||
                        rbSexoSeleccionado.getText().toString().trim().equals("") ||
                        intereses.trim().equals("")) {
                    Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Instancio nuevo Usuario
                Usuario user1 = new Usuario(etNombre.getText().toString(),
                        etApellidos.getText().toString(),
                        etEmail.getText().toString(),
                        Integer.parseInt(etTelefono.getText().toString()),
                        etDireccion.getText().toString(),
                        etFechaNacimiento.getText().toString(),
                        rbSexoSeleccionado.getText().toString(),
                        intereses);

                //Paso a un strin Gson
                cadenaUser = gson.toJson(user1);

                //Envio el usuario
                Intent enviar1 = new Intent(this, DatosUsuarios.class);
                //Intent enviar = new Intent(this,MainActivity.class);
                enviar1.putExtra("datos", cadenaUser);
                startActivity(enviar1);
                //finish();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                etFechaNacimiento.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }
}
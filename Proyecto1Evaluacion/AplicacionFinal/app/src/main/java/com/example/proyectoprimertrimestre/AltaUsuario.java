package com.example.proyectoprimertrimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class AltaUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText etFechaNacimiento;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);

        etFechaNacimiento=(EditText)findViewById(R.id.etFechaNacimiento);
        btnBack=(Button)findViewById(R.id.btnBack);

        etFechaNacimiento.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etFechaNacimiento:
                showDatePickerDialog();
                break;

            case R.id.btnBack:
                Intent intent = new Intent(this, MainActivity.class);
                finish(); //Cierro este activity y entro al alta
                startActivity(intent);
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etFechaNacimiento.setText(selectedDate);
            }
        });

        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }
}
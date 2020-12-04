package com.example.unidad03_tarea02_ejercicio04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDay;
    private RadioGroup rgDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDay = (TextView) findViewById(R.id.tvDia);
        rgDays = (RadioGroup) findViewById(R.id.rgDays);

        rgDays.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbLunes: tvDay.setText("Lunes seleccionado"); break;
                    case R.id.rbMartes: tvDay.setText("Martes seleccionado"); break;
                    case R.id.rbMiercoles: tvDay.setText("Miercoles seleccionado"); break;
                    case R.id.rbJueves: tvDay.setText("Jueves seleccionado"); break;
                    case R.id.rbViernes: tvDay.setText("Viernes seleccionado"); break;
                    case R.id.rbSabado: tvDay.setText("Sabado seleccionado"); break;
                    case R.id.rbDomingo: tvDay.setText("Domingo seleccionado"); break;
                }
            }
        });
    }
}
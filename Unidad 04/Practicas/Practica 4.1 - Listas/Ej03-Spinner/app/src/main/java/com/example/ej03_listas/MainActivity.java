package com.example.ej03_listas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCountrySelected;
    private Spinner spinnerCountries;
    private final String[] countries = new String[]
            {"España","Canada","Japon","Rusia","Uruguay",
            "Ecuador","Nueva Zelanda","Corea","Alemania","Noruega"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCountrySelected = (TextView) findViewById(R.id.tvCountrySelected);
        spinnerCountries = (Spinner) findViewById(R.id.spinnerCountries);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);
        spinnerCountries.setAdapter(adaptador);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvCountrySelected.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
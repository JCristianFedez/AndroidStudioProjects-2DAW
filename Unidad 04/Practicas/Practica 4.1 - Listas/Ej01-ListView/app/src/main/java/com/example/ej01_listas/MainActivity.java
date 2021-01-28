package com.example.ej01_listas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCountrySelected;
    private ListView lvCountries;
    private final String[] countries = new String[]
            {"España","Canada","Japon","Rusia","Uruguay",
            "Ecuador","Nueva Zelanda","Corea","Alemania","Noruega"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCountrySelected = (TextView) findViewById(R.id.tvCountrySelected);
        lvCountries = (ListView) findViewById(R.id.lvCountries);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,countries);
        lvCountries.setAdapter(adaptador);

        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvCountrySelected.setText(parent.getItemAtPosition(position).toString());
            }
        });
    }

}
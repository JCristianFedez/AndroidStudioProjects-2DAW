package com.example.unidad03_tarea02_ejercicio03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page03 extends AppCompatActivity {

    private Button btnBack03;
    private Button btnNext03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page03);


        btnBack03 = (Button) findViewById(R.id.btnBack03);
        btnNext03 = (Button) findViewById(R.id.btnNext03);

        btnBack03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(v.getContext(),Page02.class);
                startActivity(intentBack);
            }
        });
        btnNext03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),Page04.class);
                startActivity(intentNext);
            }
        });
    }
}
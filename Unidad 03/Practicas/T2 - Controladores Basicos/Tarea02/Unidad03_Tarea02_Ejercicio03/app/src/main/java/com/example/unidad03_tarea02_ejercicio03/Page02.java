package com.example.unidad03_tarea02_ejercicio03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page02 extends AppCompatActivity {

    private Button btnNext02;
    private Button btnBack02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page02);

        btnBack02 = (Button) findViewById(R.id.btnBack02);
        btnNext02 = (Button) findViewById(R.id.btnNext02);

        btnBack02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(v.getContext(),MainActivity.class);
                startActivity(intentBack);
            }
        });
        btnNext02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),Page03.class);
                startActivity(intentNext);
            }
        });
    }
}
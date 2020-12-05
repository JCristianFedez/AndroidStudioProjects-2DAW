package com.example.unidad3_tarea01_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GreenWhiteGreen extends AppCompatActivity {

    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_white_green);

        btnNext = (Button) findViewById(R.id.btnNext02);
        btnBack = (Button) findViewById(R.id.btnBack02);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),WhiteRedWhite.class);
                startActivity(intentNext);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(v.getContext(),MainActivity.class);
                startActivity(intentBack);
            }
        });
    }

}
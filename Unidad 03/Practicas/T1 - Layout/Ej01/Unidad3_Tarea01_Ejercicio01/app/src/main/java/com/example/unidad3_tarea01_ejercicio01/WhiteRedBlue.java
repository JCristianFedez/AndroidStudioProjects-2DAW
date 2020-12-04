package com.example.unidad3_tarea01_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WhiteRedBlue extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_red_blue);

        btnBack = (Button) findViewById(R.id.btnBack04);

        btnBack.setOnClickListener(v -> {
            Intent intentNext = new Intent(v.getContext(),WhiteRedWhite.class);
            startActivity(intentNext);
                }
        );
    }
}
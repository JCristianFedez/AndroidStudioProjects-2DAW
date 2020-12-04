package com.example.unidad3_tarea01_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WhiteRedWhite extends AppCompatActivity {

    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_red_white);

        btnNext = (Button) findViewById(R.id.btnNext04);
        btnBack = (Button) findViewById(R.id.btnBack04);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(), WhiteRedBlue.class);
                startActivity(intentNext);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(v.getContext(), GreenWhiteGreen.class);
                startActivity(intentBack);
            }
        });
    }
}
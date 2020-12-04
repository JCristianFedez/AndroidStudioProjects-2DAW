package com.example.unidad03_tarea02_ejercicio03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page04 extends AppCompatActivity {

    private Button btnBack04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page04);


        btnBack04 = (Button) findViewById(R.id.btnBack04);

        btnBack04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(v.getContext(),Page03.class);
                startActivity(intentBack);
            }
        });

    }
}
package com.example.unidad03_tarea02_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlueTextView extends AppCompatActivity {

    private Button btnNext02;
    private Button btnBack02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_text_view);

        btnNext02 = (Button) findViewById(R.id.btnNext02);
        btnBack02 = (Button) findViewById(R.id.btnBack02);

        btnNext02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),RedBlueTextView.class);
                startActivity(intentNext);
            }
        });
        btnBack02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),MainActivity.class);
                startActivity(intentNext);
            }
        });
    }
}
package com.example.unidad03_tarea02_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PurpleTextView extends AppCompatActivity {

    private Button btnBack04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purple_text_view);

        btnBack04 = (Button) findViewById(R.id.btnBack04);

        btnBack04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(), RedBlueTextView.class);
                startActivity(intentNext);
            }
        });
    }
}
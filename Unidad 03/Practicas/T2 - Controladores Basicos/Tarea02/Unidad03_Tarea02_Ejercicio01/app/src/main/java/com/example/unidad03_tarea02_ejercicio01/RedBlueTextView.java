package com.example.unidad03_tarea02_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RedBlueTextView extends AppCompatActivity {

    private TextView tvRedBlue;
    private String myString;
    private Button btnNext03;
    private Button btnBack03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_blue_text_view);

        tvRedBlue = (TextView) findViewById(R.id.textView03);
        myString = "<font color='red'>Texto de</font> <font color='blue'>prueba</font>";
        tvRedBlue.setText(Html.fromHtml(myString));

        btnNext03 = (Button) findViewById(R.id.btnNext03);
        btnBack03 = (Button) findViewById(R.id.btnBack03);

        btnNext03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),PurpleTextView.class);
                startActivity(intentNext);
            }
        });
        btnBack03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),BlueTextView.class);
                startActivity(intentNext);
            }
        });
    }
}
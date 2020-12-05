package com.example.unidad03_tarea02_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Page02 extends AppCompatActivity {

    private Button btnBack02;
    private Button btnMod02;
    private TextView tv02;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page02);

        btnBack02 = (Button) findViewById(R.id.btnBack02);
        btnMod02 = (Button) findViewById(R.id.btnMod02);
        tv02 = (TextView) findViewById(R.id.textView02);

        flag=true;

        btnBack02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(),MainActivity.class);
                startActivity(intentNext);
            }
        });

        btnMod02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    tv02.setTextColor(Color.parseColor("#ff1aba"));
                }else{
                    tv02.setTextColor(Color.parseColor("#17e600"));
                }
                flag=!flag;
            }
        });


    }
}
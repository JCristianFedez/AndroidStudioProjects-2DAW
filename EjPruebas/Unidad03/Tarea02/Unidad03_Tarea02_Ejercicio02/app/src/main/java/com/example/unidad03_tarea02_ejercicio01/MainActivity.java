package com.example.unidad03_tarea02_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnNext01;
    private Button btnMod01;
    private TextView tv01;
    private boolean flag;
    private String myStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext01 = (Button) findViewById(R.id.btnNext01);
        btnMod01 = (Button) findViewById(R.id.btnMod01);
        tv01 = (TextView) findViewById(R.id.textView01);

        flag=true;

        btnNext01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(v.getContext(), Page02.class);
                startActivity(intentNext);
            }
        });
    }
    public void cambiarColor(View v){
        if(flag){
            tv01.setTextColor(Color.parseColor("#ff0000"));
        }else{
            tv01.setTextColor(Color.parseColor("#0000ff"));
        }
        flag=!flag;
    }
}
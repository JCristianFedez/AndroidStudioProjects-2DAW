package com.example.unidad2_tarea04_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=(ImageView) findViewById(R.id.imageView_paisaje);
        sw=(Switch) findViewById(R.id.switchNoche);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    iv.setImageResource(R.drawable.noche);
                }else{
                    iv.setImageResource(R.drawable.dia);
                }
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv.getRotation()==0){
                    iv.setRotation(180);
                }else{
                    iv.setRotation(0);
                }
            }
        });
    }
}
package com.example.unidad2_tarea04_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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

        Drawable[] imagen=new Drawable[2];
        imagen[0]=getResources().getDrawable(R.drawable.noche);
        imagen[1]=getResources().getDrawable(R.drawable.dia);

        final boolean[] rotado = {false};//No se porque pero no podia con un boleano normal

        final TransitionDrawable transicionAparecer=new TransitionDrawable(imagen);
        iv.setImageDrawable(transicionAparecer);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    transicionAparecer.startTransition(1000);
                }else{
                    transicionAparecer.reverseTransition(1000);
                }
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                RotateAnimation animationRotation;
                if(rotado[0]){
                    rotado[0] =false;
                    animationRotation = new RotateAnimation(180, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }else{
                    rotado[0] =true;
                    animationRotation = new RotateAnimation(0, 180,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }
                animationRotation.setDuration(2000);
                animationRotation.setFillAfter(true);
                v.startAnimation(animationRotation);
                //animation.setRepeatCount(Animation.INFINITE);
                //animation.setRepeatMode(Animation.REVERSE);

            }
        });
    }
}
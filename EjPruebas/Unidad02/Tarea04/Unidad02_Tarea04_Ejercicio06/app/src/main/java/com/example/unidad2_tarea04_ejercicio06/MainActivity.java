package com.example.unidad2_tarea04_ejercicio06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivAndorid;
    AnimationDrawable animacion;
    Button myBtn;
    private boolean activo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivAndorid=(ImageView) findViewById(R.id.iV_Android);

        myBtn=(Button)findViewById(R.id.btn_Start);

        animacion=new AnimationDrawable();
        animacion.addFrame(getResources().getDrawable(R.drawable.android_quieto),100);
        animacion.addFrame(getResources().getDrawable(R.drawable.android_salto),100);

        animacion.setOneShot(false);//Si es true solo se ejecutara la animacion una vez
        ivAndorid.setImageDrawable(animacion);

    }


    public void ejercitar(View view) {
        if(activo) {
            activo=false;
            animacion.stop();
            ivAndorid.setImageResource(R.drawable.android_quieto);//Ago que se quede el mla imagen del muñeco parada
            ivAndorid.setImageDrawable(animacion);//Le vuelvo a dar la animacion
            myBtn.setText("Start");
        }else{
            activo=true;
            myBtn.setText("Stop");
            animacion.start();
        }
    }
}



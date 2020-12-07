package com.example.proyectoprimertrimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlta;
    private ImageView ivPet;
    private boolean rotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlta = (Button) findViewById(R.id.btnAlta);
        ivPet = (ImageView) findViewById(R.id.ivPet);

        btnAlta.setOnClickListener(this);
        ivPet.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlta:
                Intent intent = new Intent(this, AltaUsuario.class);
                finish(); //Cierro este activity y entro al alta
                startActivity(intent);
                break;

            case R.id.ivPet:
                RotateAnimation animacionRotar;
                if (rotate) {//Si  esta rotada la giro de 180 Grados a 360
                    rotate =!rotate;
                    animacionRotar = new RotateAnimation(180, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                } else {//Si no esta rotada la giro de 0 a 180 grados
                    rotate =! rotate;
                    animacionRotar = new RotateAnimation(0, 180,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }
                animacionRotar.setDuration(2000);
                animacionRotar.setFillAfter(true);
                v.startAnimation(animacionRotar);
                //animation.setRepeatCount(Animation.INFINITE);
                //animation.setRepeatMode(Animation.REVERSE);
                break;
        }
    }
}
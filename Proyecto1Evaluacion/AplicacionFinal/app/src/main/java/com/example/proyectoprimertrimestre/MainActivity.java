package com.example.proyectoprimertrimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlta;
    private ImageView ivPet;
    private boolean rotate = false;
    private final int DURATION = 2000;

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
//                finish(); //Cierro este activity y entro al alta
                startActivity(intent);
                break;

            case R.id.ivPet:
                //Lo desactivo para que no se pueda pulsar mientra hace la animacion
                ivPet.setEnabled(false);

                RotateAnimation animacionRotar;
                if (rotate) {//Si  esta rotada la giro de 180 Grados a 360
                    rotate = !rotate;
                    animacionRotar = new RotateAnimation(180, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                } else {//Si no esta rotada la giro de 0 a 180 grados
                    rotate = !rotate;
                    animacionRotar = new RotateAnimation(0, 180,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }
                animacionRotar.setDuration(DURATION);
                animacionRotar.setFillAfter(true);
                ivPet.startAnimation(animacionRotar);
                //animation.setRepeatCount(Animation.INFINITE);
                //animation.setRepeatMode(Animation.REVERSE);

                //Luego de terminar la animacion se vuelve a activar
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivPet.setEnabled(true);

                    }
                }, DURATION);
                break;
        }
    }
}
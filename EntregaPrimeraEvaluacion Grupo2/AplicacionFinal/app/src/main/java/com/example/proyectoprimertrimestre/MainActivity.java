package com.example.proyectoprimertrimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlta;
    private Button btnCalcular;
    private SeekBar sbEdad;
    private ImageView ivPet;
    private TextView tvEmailMain;
    private TextView tvTlfnMain;
    private TextView tvEdad;
    private TextView tvMinSeek;
    private TextView tvMaxSeek;
    private final int MIN_EDAD=16;
    private boolean rotate = false;
    private final int DURATION = 2000;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson=new Gson();

        btnAlta = (Button) findViewById(R.id.btnAlta);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        sbEdad=(SeekBar)findViewById(R.id.sbEdad);
        ivPet = (ImageView) findViewById(R.id.ivPet);
        tvEmailMain =(TextView)findViewById(R.id.tvEmailMain);
        tvTlfnMain =(TextView)findViewById(R.id.tvTlfnMain);
        tvEdad=(TextView)findViewById(R.id.tvEdad);
        tvMaxSeek=(TextView)findViewById(R.id.tvMaxSeek);
        tvMinSeek=(TextView)findViewById(R.id.tvMinSeek);

        btnAlta.setOnClickListener(this);
        ivPet.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);

        Bundle extras=getIntent().getExtras();
        if(extras==null){
            //Toast.makeText(this, "Datos no recibidos", Toast.LENGTH_SHORT).show();
            return;
        }else{
            //SE crea un usuario nuevo con los datos enviados en gson
            Usuario user=gson.fromJson(getIntent().getStringExtra("datos"),Usuario.class);
            tvTlfnMain.setText("Telefono: "+user.getTelefono());
            tvEmailMain.setText("Email: "+user.getEmail());
//            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        }

        sbEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                tvEdad.setText(progress+MIN_EDAD);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlta:
                Intent intent = new Intent(this, AltaUsuario.class);
//                finish(); //Cierro este activity y entro al alta
                startActivity(intent);
                break;

            case R.id.btnCalcular:
                int edad = sbEdad.getProgress()+MIN_EDAD;
                String numSuerte=String.valueOf(Math.floor(Math.random()*edad + 1));
                Toast.makeText(this, "Numero de la suerte: "+numSuerte, Toast.LENGTH_SHORT).show();
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
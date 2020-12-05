package com.example.unidad02_adicional_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarTiradas;
    private TextView tvCantTiradas;
    private TextView tvCountTiradas;
    private TextView tvMod1;
    private TextView tvMod2;
    private TextView tvMod3;
    private TextView tvMod4;
    private TextView tvMod5;
    private TextView tvMod6;
    private Button btnLanzar;
    private ImageSwitcher isDado;

    private Handler mHandler = new Handler();
    private final int MILI_SECOND = 750;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarTiradas = (SeekBar) findViewById(R.id.seekBarTiradas);

        tvCantTiradas = (TextView) findViewById(R.id.tvCantTiradas);
        tvCountTiradas = (TextView) findViewById(R.id.tvCountTirada);
        tvMod1 = (TextView) findViewById(R.id.tvMod1);
        tvMod2 = (TextView) findViewById(R.id.tvMod2);
        tvMod3 = (TextView) findViewById(R.id.tvMod3);
        tvMod4 = (TextView) findViewById(R.id.tvMod4);
        tvMod5 = (TextView) findViewById(R.id.tvMod5);
        tvMod6 = (TextView) findViewById(R.id.tvMod6);

        btnLanzar = (Button) findViewById(R.id.btnLanzar);

        isDado = (ImageSwitcher) findViewById(R.id.isDado);
        isDado.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(getApplicationContext());
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setLayoutParams(new
                        ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                return iv;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        isDado.setInAnimation(in);
        isDado.setOutAnimation(out);

        //SeekBar
        seekBarTiradas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //El mas 1 es porque el minimo es 1 y el maximo 100
                tvCantTiradas.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Lanzar
        btnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Las siguientes lineas son para resetear los marcadores
                    tvMod1.setText("0");
                    tvMod2.setText("0");
                    tvMod3.setText("0");
                    tvMod4.setText("0");
                    tvMod5.setText("0");
                    tvMod6.setText("0");
                    tvCountTiradas.setText("0");
                lanzar.run();
            }
        });
    }

    private Runnable lanzar = new Runnable() {
        @Override
        public void run() {
            int cont = Integer.valueOf(tvCountTiradas.getText().toString());
            int cantMax = Integer.valueOf(tvCantTiradas.getText().toString());
            int dadoValue = (int) (Math.random() * 6) + 1;
            int auxSumDado;//Variable para sumar 1 a el dado que haya salido
            seekBarTiradas.setEnabled(false);//Desabilito el SeekBar para que no lo pueda usar mientra se tiran los dados
            btnLanzar.setEnabled(false);//Desabilito el boton para que no lo pueda pulsar hasta que se tiran los dados

            if (cont < cantMax) {
                cont++;
                tvCountTiradas.setText(String.valueOf(cont));

                switch (dadoValue) {//Imprimo la imagen del dado
                    case 1:
                        isDado.setImageResource(R.drawable.dado1);
                        auxSumDado = Integer.valueOf(tvMod1.getText().toString());
                        auxSumDado++;
                        tvMod1.setText(String.valueOf(auxSumDado));
                        break;
                    case 2:
                        isDado.setImageResource(R.drawable.dado2);
                        auxSumDado = Integer.valueOf(tvMod2.getText().toString());
                        auxSumDado++;
                        tvMod2.setText(String.valueOf(auxSumDado));
                        break;
                    case 3:
                        isDado.setImageResource(R.drawable.dado3);
                        auxSumDado = Integer.valueOf(tvMod3.getText().toString());
                        auxSumDado++;
                        tvMod3.setText(String.valueOf(auxSumDado));
                        break;
                    case 4:
                        isDado.setImageResource(R.drawable.dado4);
                        auxSumDado = Integer.valueOf(tvMod4.getText().toString());
                        auxSumDado++;
                        tvMod4.setText(String.valueOf(auxSumDado));
                        break;
                    case 5:
                        isDado.setImageResource(R.drawable.dado5);
                        auxSumDado = Integer.valueOf(tvMod5.getText().toString());
                        auxSumDado++;
                        tvMod5.setText(String.valueOf(auxSumDado));
                        break;
                    case 6:
                        isDado.setImageResource(R.drawable.dado6);
                        auxSumDado = Integer.valueOf(tvMod6.getText().toString());
                        auxSumDado++;
                        tvMod6.setText(String.valueOf(auxSumDado));
                        break;
                }

                mHandler.postDelayed(this, MILI_SECOND);//Engargado del delay y la repeticion
            } else {//Final de la tirada
                tvCantTiradas.setText("1");//Reseteo la cantidad
                seekBarTiradas.setProgress(0);//Reseteo el SeekBar
                seekBarTiradas.setEnabled(true);//Cuando termina la tirada activo el SeekBar
                btnLanzar.setEnabled(true);//Cuando termina la tirada activo el boton
                mHandler.removeCallbacks(lanzar);//Paro el Handler
            }

        }
    };


}
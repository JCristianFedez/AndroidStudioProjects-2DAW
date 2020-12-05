package com.example.unidad03_tarea03_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToast;
    private Button btnSneekbar;
    private Button btnDialog;
    private TextView tvNum;
    private AlertDialog.Builder myBuilder;
    private StackTraceElement traza;
    private static final String ETIQUETA = " Mi DEPURACION :: " + MainActivity.class.getSimpleName();
    private int contadorLogs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(this);

        btnSneekbar = (Button) findViewById(R.id.btnSnackbars);
        btnSneekbar.setOnClickListener(this);

        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(this);

        tvNum = (TextView) findViewById(R.id.tvNum);

        myBuilder = new AlertDialog.Builder(this);
        //btnDialog.setOnClickListener(this::onClick);
        //Funcion en especifico podria cambiar onClick por el nombre de otra funcion

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToast:
                Toast.makeText(this, "Prueba tostada", Toast.LENGTH_SHORT).show();
                // getApplicationContext() = this;
                break;

            case R.id.btnSnackbars:
                btnSneekbar.setEnabled(false);
                Snackbar snack = Snackbar.make(v, "Prueba de Snackbar", Snackbar.LENGTH_INDEFINITE);

                snack.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.i("Snackbar", "Pulsada acción snackbar!");
                        btnSneekbar.setEnabled(true);
                    }
                });

                snack.show();
                break;

            case R.id.btnDialog:
                String titulo = "Prueba de dialogo";
                String mensaje = "¿Sumar 1?";

                myBuilder.setMessage(mensaje).setTitle(titulo);
                myBuilder.setCancelable(false);//NO dejo cerrar la ventana

                myBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {//Boton Si
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();Cerrar APP
                        int aux = Integer.valueOf(tvNum.getText().toString());
                        aux++;
                        tvNum.setText(String.valueOf(aux));
                    }
                });

                myBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {//Boton No
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

                //Creo la caja de dialogo
                AlertDialog alert = myBuilder.create();

                alert.show();

                break;
        }

    }

    public void muestraLog(StackTraceElement traza) {
        Log.i(ETIQUETA, (++contadorLogs) + " - " +
                traza.getMethodName() + " (" + traza.getClassName() + ")");
    }

//    public void onCreate(){
//        super.onCreate();
//        muestraLog(Thread.currentThread().getStackTrace()[2]);
//    }

    public void onStart() {
        super.onStart();
        muestraLog(Thread.currentThread().getStackTrace()[2]);
    }

    public void onResume() {
        super.onResume();
        muestraLog(Thread.currentThread().getStackTrace()[2]);
    }

    public void onPause() {
        super.onPause();
        muestraLog(Thread.currentThread().getStackTrace()[2]);
    }

    public void onStop() {
        super.onStop();
        muestraLog(Thread.currentThread().getStackTrace()[2]);
    }

    public void onDestroy() {
        super.onDestroy();
        muestraLog(Thread.currentThread().getStackTrace()[2]);
    }

}
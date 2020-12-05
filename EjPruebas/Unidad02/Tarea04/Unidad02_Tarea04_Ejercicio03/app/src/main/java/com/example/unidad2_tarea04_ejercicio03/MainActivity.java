package com.example.unidad2_tarea04_ejercicio03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvSol;
    private RadioGroup radioGroupOperaciones;
    private EditText num1;
    private EditText num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupOperaciones = (RadioGroup) findViewById(R.id.rbGrp_Oper);
        num1=(EditText) findViewById(R.id.primer_Numero);
        num2=(EditText) findViewById(R.id.segundo_Numero);
        tvSol=(TextView) findViewById(R.id.tv_Sol);

    }

    public void calcular (View view){
        try{//Por si no introduce valores que no de fallo
            Double valor1=Double.parseDouble(num1.getText().toString());
            Double valor2=Double.parseDouble(num2.getText().toString());
            Double total=0.0;

            switch(radioGroupOperaciones.getCheckedRadioButtonId()){//Cojo el radio seleccionado dentro del grupo
                case R.id.ra_Btn_Suma: total=valor1+valor2; break;
                case R.id.ra_Btn_Resta: total=valor1-valor2; break;
                case R.id.ra_Btn_Mult: total=valor1*valor2; break;
                case R.id.ra_Btn_Div: total=valor1/valor2; break;
                default:total=0.0;
            }

            tvSol.setText(total.toString());
        }catch(NumberFormatException e){//No se han introducido los dos numeros
            tvSol.setText("Faltan Datos");
        }catch(ArithmeticException e){//Por ejemplo division entre 0
            tvSol.setText("Error en la operacion");
        }
    }
}
package com.example.unidad2_tarea04_ejercicio04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, View.OnKeyListener {

    String strDia="00/";
    String strMes="00/";
    String strAno="0000";
    TextView tvDate;
    EditText eT_Year;
    NumberPicker numPickerMonth;
    SeekBar sKDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sKDay=(SeekBar) findViewById(R.id.seekBar_Dia);

        numPickerMonth = (NumberPicker) findViewById(R.id.numPickMes);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);

        tvDate =(TextView) findViewById(R.id.tV_Date);

        eT_Year=(EditText) findViewById(R.id.eTNum_Year);
        eT_Year.setOnKeyListener(this);//Activar cuando pulse una tecla

        //cambiar el formato del numberPicker Ej: 1=>01
        numPickerMonth.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        numPickerMonth.setOnValueChangedListener(this);

        //SeekBar dia
        sKDay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //El mas 1 es por que el minimo es 1 y el maximo 31, por lo que pongo maximo 30 y que se le sume 1
                strDia=String.valueOf(progress+1)+"/";
                tvDate.setText(strDia+strMes+strAno);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    //Number picket, Mes
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        strMes= String.valueOf(newVal)+"/";
        tvDate.setText(strDia+strMes+strAno);

    }


    //Edit text cambia Textview cuando se pulsa enter, Ano
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode==KeyEvent.KEYCODE_ENTER)){
            strAno= String.valueOf(eT_Year.getText());
            tvDate.setText(strDia+strMes+strAno);
        }
        return false;
    }


}
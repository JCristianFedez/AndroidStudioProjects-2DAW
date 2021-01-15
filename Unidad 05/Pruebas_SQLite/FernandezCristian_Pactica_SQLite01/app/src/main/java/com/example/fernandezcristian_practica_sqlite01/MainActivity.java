package com.example.fernandezcristian_practica_sqlite01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInsert;
    private Button btnDelete;
    private Button btnUpdate;
    private EditText etName;
    private EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsertar);
        btnDelete = (Button) findViewById(R.id.btnDel);
        btnUpdate = (Button) findViewById(R.id.btnAct);

        etCode = (EditText) findViewById(R.id.etCodigo);
        etName = (EditText) findViewById(R.id.etNombre);

        etCode.setText("");
        etName.setText("");

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AndroidBaseDatos.class);
        String action="none";
        String code=etCode.getText().toString();
        String name=etName.getText().toString();
        switch (v.getId()) {
            case R.id.btnInsertar:
                action="insert";
                break;

            case R.id.btnAct:
                action="upload";
                break;

            case R.id.btnDel:
                action="delete";
                break;
        }
        intent.putExtra("action",action);
        intent.putExtra("code",code);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
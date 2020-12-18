package com.example.proyectoprimertrimestre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class DatosUsuarios extends AppCompatActivity implements View.OnClickListener {

    private final static int CODIGO_CREAR = 1;
    private Gson gson;
    private TextView tvNameYApell;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDireccion;
    private TextView tvFechaNacimiento;
    private TextView tvSexo;
    private TextView tvIntereses;
    private Button btnBacktoForm;
    private Button btnEnviarValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuarios);
        gson=new Gson();

        tvNameYApell = (TextView) findViewById(R.id.tvNameYApell);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDireccion = (TextView) findViewById(R.id.tvDireccion);
        tvFechaNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvSexo=(TextView)findViewById(R.id.tvSexo);
        tvIntereses=(TextView)findViewById(R.id.tvIntereses);

        btnBacktoForm = (Button) findViewById(R.id.btnBacktoForm);
        btnEnviarValoracion = (Button) findViewById(R.id.btnEnviarValoracion);

        btnBacktoForm.setOnClickListener(this);
        btnEnviarValoracion.setOnClickListener(this);

        Bundle extras=getIntent().getExtras();
        if(extras==null){
            Toast.makeText(this, "Datos no recibidos", Toast.LENGTH_SHORT).show();
            return;
        }else{
            //SE crea un usuario nuevo con los datos enviados en gson
            Usuario user=gson.fromJson(getIntent().getStringExtra("datos"),Usuario.class);

            tvNameYApell.setText("Nombre: "+user.getNombre()+", "+user.getApellidos());
            tvTelefono.setText("Tfn: "+String.valueOf(user.getTelefono()));
            tvEmail.setText("Email: "+user.getEmail());
            tvDireccion.setText("Adress: "+user.getDireccion());
            tvFechaNacimiento.setText("Nacimiento: "+user.getDireccion());
            tvSexo.setText("Sexo: "+user.getSexo());
            tvIntereses.setText("Intereses: "+user.getIntereses());

//            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBacktoForm:
                Intent back = new Intent(this, AltaUsuario.class);
                startActivity(back);
                finish();
                break;
            case R.id.btnEnviarValoracion:
                Intent first = new Intent(this, MainActivity.class);
                startActivity(first);
                finish();
                break;
        }
    }

}
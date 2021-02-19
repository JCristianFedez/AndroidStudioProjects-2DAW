package com.example.proyecto2evaluacion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

public class AltaUsuario extends AppCompatActivity implements View.OnClickListener {

    private static final String TABLE_NAME = "usuario";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    private static final String CARPETA_PRINCIPAL = "misImagenesApp/"; // Directorio principal
    private static final String CARPETA_IMAGEN = "imagenes"; // Carpeta donde se guardaran als imagenes
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private String path; // Almacena la ruta de la imagen
    File fileImagen;
    Bitmap bitmap;

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etDni;
    private EditText etUsuari;
    private EditText etPassword;
    private ImageView ivPerfilUsuario;
    private Button btnInsertarUsuario;
    private Button btnElegirImagen;
    private RadioGroup rgPerfil;
    private RadioButton rbPerfilSeleccionado;
    private SQLiteDatabase db;
    private ContentValues querry;

    //TODO: Recojer imagen al pulsar el boton y guardarla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etDni = (EditText) findViewById(R.id.etDni);
        etUsuari = (EditText) findViewById(R.id.etUsuari);
        etPassword = (EditText) findViewById(R.id.etPassword);
        ivPerfilUsuario = (ImageView) findViewById(R.id.ivPerfilUsuario);
        btnInsertarUsuario = (Button) findViewById(R.id.btnInsertarIncidencia);
        btnElegirImagen = (Button) findViewById(R.id.btnElegirImagen);
        rgPerfil = (RadioGroup) findViewById(R.id.rgPerfil);
        btnElegirImagen.setOnClickListener(this);
        btnInsertarUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnElegirImagen:
                mostrarDialogoOpciones();
                break;
            case R.id.btnInsertarIncidencia:
                insertarUsuario();
                break;
        }
    }

    private void insertarUsuario() {
        ProyectoSQLiteHelper prdbh =
                new ProyectoSQLiteHelper(this, "DBProyecto", null, 1);

        db = prdbh.getWritableDatabase();

        querry = new ContentValues();

        if (db != null) {
            Cursor c;
            String[] args;

            int radioSelectId = rgPerfil.getCheckedRadioButtonId();
            rbPerfilSeleccionado = (RadioButton) findViewById(radioSelectId);
            if (radioSelectId == -1) {
                Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();

                //Obligar a introducir todos los datos
            } else if (etNombre.getText().toString().trim().equals("") ||
                        etApellidos.getText().toString().trim().equals("") ||
                        etDni.getText().toString().trim().equals("") ||
                        etUsuari.getText().toString().trim().equals("") ||
                        etPassword.getText().toString().trim().equals("") ||
                        rbPerfilSeleccionado.getText().toString().trim().equals("") ||
                        ivPerfilUsuario.getDrawable() == null) {
                    Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();

                } else {
                    int perfil = (rbPerfilSeleccionado.getText().toString().equals("Admin")) ? 1 : 0;
                    args = new String[]{etDni.getText().toString()};
                    c = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE "+USER_DNI+"=? ", args);
                    int a = c.getCount();
                    if (c.getCount() == 0) {
                        querry.put(USER_DNI, etDni.getText().toString());
                        querry.put(USER_NOM, etNombre.getText().toString());
                        querry.put(USER_AP, etApellidos.getText().toString());
                        querry.put(USER_US, etUsuari.getText().toString());
                        querry.put(USER_PASS, etPassword.getText().toString());
                        querry.put(USER_PERFIL, perfil);
                        db.insert(TABLE_NAME, null, querry);
                        Toast.makeText(getApplicationContext(), "usuario " + etNombre.getText().toString() + " añadido", Toast.LENGTH_SHORT).show();
                        vaciarEditText();

                    } else {
                        Toast.makeText(getApplicationContext(), "usuario con dni  " + etDni.getText().toString() + " ya existente", Toast.LENGTH_SHORT).show();
                    }
                c.close();
                }
            db.close();
        }
    }

    private void vaciarEditText() {
        etNombre.setText("");
        etApellidos.setText("");
        etDni.setText("");
        etUsuari.setText("");
        etPassword.setText("");
        rgPerfil.clearCheck();

        //Vaciar imagen
        ivPerfilUsuario.setImageResource(0);
    }

    private void mostrarDialogoOpciones(){
        final CharSequence[] opciones = {"Tomar Foto", "Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opcion");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(opciones[i].equals("Tomar Foto")){
                    abrirCamara();
                }else{
                    if(opciones[i].equals("Elegir de Galeria")){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialog.dismiss();
                    }
                }
            }
        });
        builder.create().show();
    }

    private void abrirCamara() {
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if(!isCreada){
            isCreada = miFile.mkdirs();
        }else{
            Long consecutivo = System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString() + ".jpg";

            // Indicamos la ruta de almacenamiento
            path = Environment.getExternalStorageDirectory()
                    + File.separator
                    + DIRECTORIO_IMAGEN
                    + File.separator
                    + nombre;

            fileImagen = new File(path);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            startActivityForResult(intent,COD_FOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath = data.getData();
                ivPerfilUsuario.setImageURI(miPath);
                break;

            case COD_FOTO:
                MediaScannerConnection.scanFile(this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path","" + path);
                            }
                        });

                bitmap = BitmapFactory.decodeFile(path);
                ivPerfilUsuario.setImageBitmap(bitmap);
                break;
        }
    }
}
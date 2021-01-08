package com.example.fernandezcristian_practica_sqlite01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AlumnosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Alumnos
    String sqlCreate = "CREATE TABLE Alumnos (codigo INTEGER, nombre TEXT)";

    public AlumnosSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Se elimina la tabla anterior y crearla de nuevo vacía con el nuevo formato.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Alumnos");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }
}

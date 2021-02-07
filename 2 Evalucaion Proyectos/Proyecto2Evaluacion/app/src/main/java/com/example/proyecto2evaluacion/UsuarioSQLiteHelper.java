package com.example.proyecto2evaluacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuarioSQLiteHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "usuario";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private SQLiteDatabase mDb;

    //SENTENTCIA SQL CREAR TABLA Usuario
    private static final String CREATE_USER_TABLE = "create table "
            + TABLE_NAME + " ("
            + USER_DNI + " TEXT PRIMARY KEY,"
            + USER_NOM + " TEXT,"
            + USER_AP + " TEXT,"
            + USER_US + " TEXT,"
            + USER_PASS + " TEXT,"
            + USER_PERFIL + " INTEGER,"
            + USER_FOTO + " BLOB"
            + ");";


    public UsuarioSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la tabla anterior y crearla de nuevo vacía con el nuevo formato.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //Se crea la nueva versión de la tabla
        db.execSQL(CREATE_USER_TABLE);
    }

}

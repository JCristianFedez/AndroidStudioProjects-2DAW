package com.example.proyecto2evaluacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProyectoSQLiteHelper extends SQLiteOpenHelper {
    private static final String USER_TABLE_NAME = "usuario";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private static final String INC_TABLE_NAME = "incidencia";
    private static final String INC_DNI = "dni";
    private static final String INC_FECHA_INICIO = "fecha_inicio";
    private static final String INC_OBSER = "observacion";
    private static final String INC_RESPONSABLE = "dni_responsable";
    private static final String INC_ESTADO = "estado";
    private static final String INC_FECHA_FIN = "fecha_resolucion";

    private SQLiteDatabase mDb;

    //TODO: Guardar imagen

    //SENTENTCIA SQL CREAR TABLA Usuario
    private static final String CREATE_USER_TABLE = "create table "
            + USER_TABLE_NAME + " ("
            + USER_DNI + " TEXT PRIMARY KEY,"
            + USER_NOM + " TEXT,"
            + USER_AP + " TEXT,"
            + USER_US + " TEXT,"
            + USER_PASS + " TEXT,"
            + USER_PERFIL + " INTEGER,"
            + USER_FOTO + " BLOB"
            + ");";

    //SENTENTCIA SQL CREAR TABLA Incidencia
    private static final String CREATE_INC_TABLE = "create table "
            + INC_TABLE_NAME + " ("
            + INC_DNI + " TEXT PRIMARY KEY,"
            + INC_FECHA_INICIO + " TEXT,"
            + INC_OBSER + " TEXT,"
            + INC_RESPONSABLE + " TEXT,"
            + INC_ESTADO + " INTEGER,"
            + INC_FECHA_FIN + " TEXT,"
            + "foreign key("+ INC_RESPONSABLE +") references "+ USER_TABLE_NAME +"("+ USER_DNI +")"
            + ");";

    public ProyectoSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la tabla anterior y crearla de nuevo vacía con el nuevo formato.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INC_TABLE_NAME);

        //Se crea la nueva versión de la tabla
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INC_TABLE);
    }

}
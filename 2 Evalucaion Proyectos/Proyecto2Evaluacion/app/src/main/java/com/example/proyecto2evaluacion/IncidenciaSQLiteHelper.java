package com.example.proyecto2evaluacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class IncidenciaSQLiteHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "incidencia";
    private static final String INC_DNI = "dni";
    private static final String INC_FECHA_INICIO = "fecha_inicio";
    private static final String INC_OBSER = "observacion";
    private static final String INC_RESPONSABLE = "dni_responsable";
    private static final String INC_ESTADO = "estado";
    private static final String INC_FECHA_FIN = "fecha_resolucion";

    private SQLiteDatabase mDb;

    //SENTENTCIA SQL CREAR TABLA Usuario
    private static final String CREATE_USER_TABLE = "create table "
            + TABLE_NAME + " ("
            + INC_DNI + " TEXT PRIMARY KEY,"
            + INC_FECHA_INICIO + " TEXT,"
            + INC_OBSER + " TEXT,"
            + INC_RESPONSABLE + " TEXT,"
            + INC_ESTADO + " INTEGER,"
            + INC_FECHA_FIN + " TEXT,"
            + "foreign key("+ INC_RESPONSABLE +") references usuario(dni)"
            + ");";

    public IncidenciaSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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

package com.example.proyecto2evaluacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProyectoSQLiteHelper extends SQLiteOpenHelper {
    private static final String USER_TABLE_NAME = "usuario";
    private static final String USER_ID = "id";
    private static final String USER_DNI = "dni";
    private static final String USER_NOM = "nombre";
    private static final String USER_AP = "apellidos";
    private static final String USER_US = "usuari";
    private static final String USER_PASS = "password";
    private static final String USER_PERFIL = "perfil";
    private static final String USER_FOTO = "foto";

    private static final String INC_TABLE_NAME = "incidencia";
    private static final String INC_ID = "id";
    private static final String INC_DNI = "dni";
    private static final String INC_FECHA_INICIO = "fecha_inicio";
    private static final String INC_OBSER = "observacion";
    private static final String INC_RESPONSABLE = "dni_responsable";
    private static final String INC_ESTADO = "estado";
    private static final String INC_FECHA_FIN = "fecha_resolucion";

    private static final String REG_TABLE_NAME = "registro";
    private static final String REG_ID = "id";
    private static final String REG_NOMBRE = "nombre";
    private static final String REG_FECHA = "fecha";
    private static final String REG_ID_USER = "id_usuario";
    private static final String REG_DESC = "descripcion";
    private static final String REG_ACTIVO = "activo";

    private SQLiteDatabase mDb;

    //NOTA: Al añadir la ID se me ha roto las forgein key

    //SENTENTCIA SQL CREAR TABLA Usuario
    private static final String CREATE_USER_TABLE = "create table "
            + USER_TABLE_NAME + " ("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_DNI + " TEXT,"
            + USER_NOM + " TEXT,"
            + USER_AP + " TEXT,"
            + USER_US + " TEXT,"
            + USER_PASS + " TEXT,"
            + USER_PERFIL + " INTEGER,"
            + USER_FOTO + " TEXT"
//            + ", PRIMARY KEY ("+USER_DNI+","+USER_ID+")"
            + ");";

    //SENTENTCIA SQL CREAR TABLA Incidencia
    private static final String CREATE_INC_TABLE = "create table "
            + INC_TABLE_NAME + " ("
            + INC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + INC_DNI + " TEXT,"
            + INC_FECHA_INICIO + " TEXT,"
            + INC_OBSER + " TEXT,"
            + INC_RESPONSABLE + " TEXT,"
            + INC_ESTADO + " INTEGER,"
            + INC_FECHA_FIN + " TEXT"
//            + ", foreign key("+ INC_DNI +") references "+ USER_TABLE_NAME +"("+ USER_DNI +")"
//            + "ON DELETE CASCADE "
//            + "ON UPDATE CASCADE, "
//            + "foreign key("+ INC_RESPONSABLE +") references "+ USER_TABLE_NAME +"("+ USER_DNI +")"
            + ");";

    //SENTENCIA SQL CREAR TABLA Registro
    private static final String CREATE_REG_TABLE = "create table "
            + REG_TABLE_NAME + " ("
            + REG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + REG_NOMBRE + " TEXT,"
            + REG_FECHA + " TEXT,"
            + REG_ID_USER + " INTEGER,"
            + REG_DESC + " TEXT,"
            + REG_ACTIVO + " INTEGER"
            + ");";


    public ProyectoSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INC_TABLE);
        db.execSQL(CREATE_REG_TABLE);
    }

    // Sirve para poder usar las claves foraneas y las eliminaciones y actualizaciones en cascada
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la tabla anterior y crearla de nuevo vacía con el nuevo formato.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INC_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REG_TABLE_NAME);


        //Se crea la nueva versión de la tabla
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INC_TABLE);
        db.execSQL(CREATE_REG_TABLE);
    }

}

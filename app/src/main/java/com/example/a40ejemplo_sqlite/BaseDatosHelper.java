package com.example.a40ejemplo_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatosHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de usuarios
    String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER, nombre TEXT, contrasenia TEXT)";   //esta es la sentencia SQL ANSI


    public BaseDatosHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Se ejecuta la sentencia SQL de creación de tabla ojo al string creado en el inicio de la clase
        // al llamar a la clase se crea una base de datos de inicio USUARIOS
        // hay que controlar las injecciones en la base de datos

        //Situaciones
        //Devolverá una conexión válida a la base de datos. Si existe la base de datos y la versión solicitada es la correcta por la aplicación.
        // será ejecutado solo en el caso de que la base de datos no se haya creado antes.


                db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //NOTA : por simplicidad del ejemplo aquí utilizamos directamente la opción de
        // eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato
        // Sin embargo, lo normal será que haya que migrar datos de la tabla antigua
        //a la nueva por lo que este métido debería ser más elaborado.
        //Se elimina la versión anterior de la tabla
        //Si existe la base de datos, pero no es correcta la versión, se hace el llamado al método Upgrade, previo a la implementación de sus funciones devuelve una conexión más actualizada.
          //      onCreate
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}

package com.example.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//se crea la base de datos y se maneja la base de datos
public class AdminBD extends SQLiteOpenHelper {
    public AdminBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {
        //creamos la tabla
        BaseDatos.execSQL("create table usuario(cedula int primary key, nombre text, telefono int)");
    }

    @Override //se usa en caso de que se realice una modificacion en la base de datos, detecta los cambios
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

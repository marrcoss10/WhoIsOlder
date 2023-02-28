package com.example.whoisolder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {
    public miBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //sqLiteDatabase.execSQL("CREATE TABLE Famosos('Codigo' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' VARCHAR(30), 'Edad' INTEGER)");
        //Rellenamos la bd
        /*sqLiteDatabase.execSQL("INSERT INTO Famosos ('Nombre','Edad') VALUES ('Messi',35)");
        sqLiteDatabase.execSQL("INSERT INTO Famosos ('Nombre','Edad') VALUES ('Cristiano',38)");
        sqLiteDatabase.execSQL("INSERT INTO Famosos ('Nombre','Edad') VALUES ('Pen.Cruz',48)");
        sqLiteDatabase.execSQL("INSERT INTO Famosos ('Nombre','Edad') VALUES ('Ant.Banderas',62)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //No hay operaciones
    }
}

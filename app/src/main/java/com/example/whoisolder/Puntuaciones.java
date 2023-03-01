package com.example.whoisolder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Puntuaciones extends AppCompatActivity {

    ArrayList<String> listapuntos = new ArrayList<>();
    ArrayAdapter adaptador;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        cargarPuntos();

        list = findViewById(R.id.lista);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String borrar = listapuntos.get(i);
                String nombre = borrar.split(" ")[0];
                String puntos = borrar.split(" ")[0];
                listapuntos.remove(i);
                eliminarBD(nombre,puntos);
                adaptador.notifyDataSetChanged();

            }
        });

        Button share = findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri web = Uri.parse("https://www.instagram.com/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,web);
                startActivity(webIntent);
            }
        });
    }
    public void cargarPuntos(){
        listapuntos.clear();

        miBD gestor = new miBD(this,"Puntos",null,1);
        SQLiteDatabase bd = gestor.getWritableDatabase();
        //bd.execSQL("DELETE FROM Puntuaciones"); /*Para borrar todos los datos*/
        Cursor c = bd.rawQuery("SELECT Nombre,Puntos FROM Puntuaciones",null);
        while(c.moveToNext()){
            listapuntos.add(c.getString(0) + " " + c.getInt(1));
        }
        c.close();
        bd.close();
        adaptador = new ArrayAdapter<String>(Puntuaciones.this, android.R.layout.simple_list_item_1, listapuntos);
        list = findViewById(R.id.lista);
        list.setAdapter(adaptador);
    }

    public void eliminarBD(String nombre, String puntos){
        miBD gestor = new miBD(this,"Puntos",null,1);
        SQLiteDatabase bd = gestor.getWritableDatabase();
        bd.delete("Puntuaciones","Nombre=? AND Puntos=?",new String[]{nombre,puntos});
        bd.close();
    }
}
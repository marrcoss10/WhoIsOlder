package com.example.whoisolder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Puntuaciones extends AppCompatActivity {

    ArrayList<String> listapuntos = new ArrayList<>();
    int[] imagenes = {R.drawable.usuario};
    AdaptadorListView adaptador;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);
        //Se llama a la funcion de cargar las puntuaciones de la base de datos
        cargarPuntos();
        //Se recogen las variables del layout
        list = findViewById(R.id.lista);
        //Cuando pulse durante un tiempo un item, borra el item de la lista y de la base de datos
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String borrar = listapuntos.get(i);
                String nombre = borrar.split(" ")[0];
                String puntos = borrar.split(" ")[1];
                listapuntos.remove(i);
                eliminarBD(nombre,puntos);
                adaptador.notifyDataSetChanged();
                return false;
            }
        });
        //Se recogen las variables del layout
        Button share = findViewById(R.id.btn_share);
        Button foto = findViewById(R.id.btnFoto);
        //Se abre instagram en google para poder compartir la puntuacion
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri web = Uri.parse("https://www.instagram.com/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,web);
                startActivity(webIntent);
            }
        });
        //Se abre la camara para poder sacar la foto
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }
    public void cargarPuntos(){
        //Se inicializa la lista de las puntuaciones
        listapuntos.clear();
        //Se recogen todas las puntuaciones de la base de datoss
        miBD gestor = new miBD(this,"Puntos",null,1);
        SQLiteDatabase bd = gestor.getWritableDatabase();
        //bd.execSQL("DELETE FROM Puntuaciones"); /*Para borrar todos los datos*/
        Cursor c = bd.rawQuery("SELECT Nombre,Puntos FROM Puntuaciones",null);
        while(c.moveToNext()){
            listapuntos.add(c.getString(0) + " " + c.getInt(1));
        }
        c.close();
        bd.close();
        adaptador = new AdaptadorListView(getApplicationContext(),listapuntos,imagenes);
        list = findViewById(R.id.lista);
        list.setAdapter(adaptador);
    }
    //Se elimina de la base de datos la fila con el nombre y puntos que ha dejado pulsado el usuario
    public void eliminarBD(String nombre, String puntos){
        miBD gestor = new miBD(this,"Puntos",null,1);
        SQLiteDatabase bd = gestor.getWritableDatabase();
        bd.delete("Puntuaciones","Nombre=? AND Puntos=?",new String[]{nombre,puntos});
        bd.close();
    }

    //Funcion para abrir la camara
    public void abrirCamara(){
        Intent intCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intCam,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            ImageView fot = findViewById(R.id.foto);
            fot.setImageBitmap(imgBitmap);
        }
    }
}
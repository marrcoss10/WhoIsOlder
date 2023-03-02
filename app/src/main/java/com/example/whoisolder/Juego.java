package com.example.whoisolder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego extends AppCompatActivity {
    ArrayList<String> nombres = new ArrayList<String>();
    HashMap<String,Integer> map = new HashMap<String, Integer>();
    Integer x;
    Integer y;
    int points;
    String p;
    TextView pt, name, f1, f2;
    ImageView img1, img2;
    Boolean cargar = true;
    NotificationManager elManager;
    NotificationCompat.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        if (savedInstanceState!= null)
        {
            //Recuperar valores cuando gira
            points = savedInstanceState.getInt("punt");
            cargar = savedInstanceState.getBoolean("bool");
            nombres = savedInstanceState.getStringArrayList("lista");
            x = savedInstanceState.getInt("x");
            y = savedInstanceState.getInt("y");
        }

        //Realizar todo no necesario para las noticifaciones
        elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(Juego.this,"Mi Notificacion");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED) {
                //PEDIR EL PERMISO
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 11);
            }
            NotificationChannel channel = new NotificationChannel("Mi Notificacion","Mi Notificacion", NotificationManager.IMPORTANCE_DEFAULT);
            elManager.createNotificationChannel(channel);
        }


        builder.setContentTitle("Fin del juego");
        builder.setContentText("Has perdido, vuelve a intentarlo!");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logoapppeque));
        builder.setSmallIcon(android.R.drawable.stat_sys_warning);
        builder.setAutoCancel(true);
        //Recoger todos las variables del layout
        name = findViewById(R.id.hola);
        pt = findViewById(R.id.puntos);
        pt.setText(String.valueOf(points));

        p = String.valueOf(points);
        pt.setText(p);
        //Se recogen las variables de la pantalla anterior
        Bundle bundle = getIntent().getExtras();
        String dato = bundle.getString("nombre");
        name.setText(dato);
        //Se llama a la funcion de cargar los datos de la bd en el hashmap
        cargarHashmap();
        //Si es la primar vez se cargan los nombres aleatorios
        if(cargar){
            cargarNombresAleatorios();
        }
        //Si se ha girado se recogen los datos de las variables guardadas
        else{
            f1 = findViewById(R.id.famoso1);
            f2 = findViewById(R.id.famoso2);
            f1.setText(nombres.get(x));
            f2.setText(nombres.get(y));
            img1 = findViewById(R.id.img1);
            img2 = findViewById(R.id.img2);
            actualizarImagen(img1,x);
            actualizarImagen(img2,y);

        }
        Button boton1 = findViewById(R.id.btn_1);
        Button boton2 = findViewById(R.id.btn_2);
        boton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                comprobar_mayor(1,nombres.get(x),nombres.get(y));
            }
        });

        boton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                comprobar_mayor(2,nombres.get(x),nombres.get(y));
            }
        });

    }

    public void cargarNombresAleatorios(){
        cargar = false;
        f1 = findViewById(R.id.famoso1);
        f2 = findViewById(R.id.famoso2);
        Integer i = nombres.size();
        x = (int) Math.floor(Math.random()*i);
        y = (int) Math.floor(Math.random()*i);
        boolean salir = false;
        while(!salir){
            if(y != x){
                salir = true;
            }
            else{
                y = (int) Math.floor(Math.random()*i);
            }
        }
        f1.setText(nombres.get(x));
        f2.setText(nombres.get(y));

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        actualizarImagen(img1,x);
        actualizarImagen(img2,y);
    }

    public void actualizarImagen(ImageView img, int i){
        String fam = nombres.get(i);
        switch (fam){
            case "Messi":
                img.setImageResource(R.drawable.messi);
                break;
            case "Cristiano":
                img.setImageResource(R.drawable.cristiano);
                break;
            case "Pen.Cruz":
                img.setImageResource(R.drawable.penelope);
                break;
            case "Ant.Banderas":
                img.setImageResource(R.drawable.antoniobanderas);
                break;
            case "Jen.Lopez":
                img.setImageResource(R.drawable.jl);
                break;
            case "Bel.Rueda":
                img.setImageResource(R.drawable.belenrueda);
                break;
            case "Cr.Pedroche":
                img.setImageResource(R.drawable.pedroche);
                break;
            case "Pablo Motos":
                img.setImageResource(R.drawable.motos);
                break;
            case "Alej.Sanz":
                img.setImageResource(R.drawable.alesanz);
                break;
            case "Coronado":
                img.setImageResource(R.drawable.coronado);
                break;
            case "Sanchez":
                img.setImageResource(R.drawable.sanchez);
                break;
            case "Rajoy":
                img.setImageResource(R.drawable.rajoy);
                break;
            case "Broncano":
                img.setImageResource(R.drawable.broncano);
                break;
            case "Risto":
                img.setImageResource(R.drawable.risto);
                break;
            case "Bustamante":
                img.setImageResource(R.drawable.bustamante);
                break;
            case "Chenoa":
                img.setImageResource(R.drawable.chenoa);
                break;
            case "Beckham":
                img.setImageResource(R.drawable.beckham);
                break;
            default:
                break;
        }
    }

    public void cargarHashmap(){
        miBD gestor = new miBD(this,"Famosos",null,2);
        SQLiteDatabase bd = gestor.getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT Nombre,Edad FROM Famosos",null);
        while(c.moveToNext()){
            map.put(c.getString(0),c.getInt(1));
            nombres.add(c.getString(0));
        }
        c.close();
        bd.close();
    }

    public void comprobar_mayor(int i, String x, String y){
        Integer x1 = map.get(x);
        Integer x2 = map.get(y);
        if(x1 >= x2){
            if(i == 1){
                pt = findViewById(R.id.puntos);
                points = Integer.parseInt(pt.getText().toString())+1;
                pt.setText(String.valueOf(points));
                cargarNombresAleatorios();
            }
            else{
                Intent intentpuntuaciones = new Intent(Juego.this, Puntuaciones.class);
                TextView n = findViewById(R.id.hola);
                String nom = n.getText().toString();

                miBD gestor = new miBD(this,"Puntos",null,1);
                SQLiteDatabase bd = gestor.getWritableDatabase();
                //bd.execSQL("INSERT INTO Puntuaciones ('Nombre','Puntos') VALUES (nombre,puntos)");
                ContentValues modificaciones = new ContentValues();
                modificaciones.put("Nombre",nom);
                modificaciones.put("Puntos",points);
                bd.insert("Puntuaciones",null,modificaciones);
                bd.close();

                elManager.notify(1,builder.build());

                startActivity(intentpuntuaciones);
                finish();
            }
        }
        else if(x1 <= x2){
            if(i == 2){
                pt = findViewById(R.id.puntos);
                points = Integer.parseInt(pt.getText().toString()) + 1;
                pt.setText(String.valueOf(points));
                cargarNombresAleatorios();
            }
            else{
                Intent intentpuntuaciones = new Intent(Juego.this, Puntuaciones.class);
                TextView n = findViewById(R.id.hola);
                String nom = n.getText().toString();

                miBD gestor = new miBD(this,"Puntos",null,1);
                SQLiteDatabase bd = gestor.getWritableDatabase();
                //bd.execSQL("INSERT INTO Puntuaciones ('Nombre','Puntos') VALUES (nombre,puntos)");
                ContentValues modificaciones = new ContentValues();
                modificaciones.put("Nombre",nom);
                modificaciones.put("Puntos",points);
                bd.insert("Puntuaciones",null,modificaciones);
                bd.close();

                elManager.notify(1,builder.build());

                startActivity(intentpuntuaciones);
                finish();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("punt", points);
        savedInstanceState.putBoolean("bool",cargar);
        savedInstanceState.putStringArrayList("lista",nombres);
        savedInstanceState.putInt("x",x);
        savedInstanceState.putInt("y",y);
    }
}
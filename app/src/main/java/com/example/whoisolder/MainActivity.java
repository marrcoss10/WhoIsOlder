package com.example.whoisolder;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jugar = findViewById(R.id.btn_jugar);
        Button instrucciones = findViewById(R.id.btn_instrucciones);
        Button puntuaciones = findViewById(R.id.btn_puntuaciones);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentregistro = new Intent(MainActivity.this, Registrarse.class);
                startActivity(intentregistro);
            }
        });

        instrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoInstrucciones dialogo = new DialogoInstrucciones();
                dialogo.show(getSupportFragmentManager(),"instrucciones");
            }
        });

        puntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentpuntuaciones = new Intent(MainActivity.this, Puntuaciones.class);
                startActivity(intentpuntuaciones);
            }
        });
    }
}
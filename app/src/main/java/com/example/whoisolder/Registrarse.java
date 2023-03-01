package com.example.whoisolder;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        Button comenzar = findViewById(R.id.btnStart);

        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = findViewById(R.id.editName);
                String name = nombre.getText().toString();

                Intent intentjuego = new Intent(Registrarse.this, Juego.class);
                intentjuego.putExtra("nombre",name); //Guardamos en variable global
                startActivity(intentjuego);
                finish();
            }
        });
    }
}
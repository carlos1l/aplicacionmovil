package com.example.aplicacionmovil.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionmovil.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAddRemission, btnViewRemission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddRemission = findViewById(R.id.btnAddRemission);
        btnViewRemission = findViewById(R.id.btnViewRemission);

        btnAddRemission.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddRemissionActivity.class))
        );

        btnViewRemission.setOnClickListener(v -> {
            // Aquí podrías abrir una lista de remisiones o un detalle puntual
            // Ejemplo: startActivity(new Intent(MainActivity.this, RemissionDetailActivity.class));
        });
    }
}

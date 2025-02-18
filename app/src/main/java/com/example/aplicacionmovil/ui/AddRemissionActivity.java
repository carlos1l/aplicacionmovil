package com.example.aplicacionmovil.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionmovil.R;
import com.example.aplicacionmovil.model.DBHelper;

public class AddRemissionActivity extends AppCompatActivity {

    private EditText etCode, etValue;
    private Button btnAdd;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remission);

        dbHelper = new DBHelper(this);

        etCode = findViewById(R.id.etCode);
        etValue = findViewById(R.id.etValue);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            String code = etCode.getText().toString().trim();
            String value = etValue.getText().toString().trim();

            if (TextUtils.isEmpty(code) || TextUtils.isEmpty(value)) {
                Toast.makeText(AddRemissionActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.insertRemission(code, value);
            if (success) {
                Toast.makeText(AddRemissionActivity.this, "Remisión agregada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddRemissionActivity.this, "Error al agregar la remisión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

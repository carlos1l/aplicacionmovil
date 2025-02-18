package com.example.aplicacionmovil.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionmovil.R;
import com.example.aplicacionmovil.model.DBHelper;

public class RemissionDetailActivity extends AppCompatActivity {

    private TextView tvRemissionCode, tvRemissionValue;
    private Button btnDelete; // si lo deseas
    private DBHelper dbHelper;
    private int remissionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remission_detail);

        tvRemissionCode = findViewById(R.id.tvRemissionCode);
        tvRemissionValue = findViewById(R.id.tvRemissionValue);
        btnDelete = findViewById(R.id.btnDelete); // si existe en el XML
        dbHelper = new DBHelper(this);

        // Recupera el ID de la remisión
        remissionId = getIntent().getIntExtra("remission_id", -1);
        if (remissionId == -1) {
            Toast.makeText(this, "Remisión inválida", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            loadRemissionDetails(remissionId);
        }

        // Eliminar remisión
        btnDelete.setOnClickListener(v -> {
            boolean deleted = dbHelper.deleteRemission(remissionId);
            if (deleted) {
                Toast.makeText(this, "Remisión eliminada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar la remisión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRemissionDetails(int id) {
        Cursor cursor = dbHelper.getRemissionById(id);
        if (cursor != null && cursor.moveToFirst()) {
            String code = cursor.getString(cursor.getColumnIndexOrThrow("code"));
            String value = cursor.getString(cursor.getColumnIndexOrThrow("value"));
            tvRemissionCode.setText("Código: " + code);
            tvRemissionValue.setText("Valor: " + value);
            cursor.close();
        } else {
            Toast.makeText(this, "No se encontró la remisión", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.aplicacionmovil.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aplicacionmovil.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionmovil.R;
import com.example.aplicacionmovil.model.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            // 1. Caso usuario fijo "admin" / "1234"
            if ("admin".equals(username) && "1234".equals(password)) {
                Toast.makeText(this, "Login exitoso (admin)", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            // 2. Caso validación en base de datos
            else if (dbHelper.validateUser(username, password)) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            // 3. Credenciales inválidas
            else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );
    }
}

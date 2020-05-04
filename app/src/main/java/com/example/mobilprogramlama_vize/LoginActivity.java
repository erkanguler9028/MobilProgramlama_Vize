package com.example.mobilprogramlama_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.usernameEditText);
        edtPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    Toast.makeText(LoginActivity.this,"Giriş başarılı",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                if (!username.equals("admin") || !password.equals("admin")) {
                    Toast.makeText(LoginActivity.this,"Kullanıcı adı veya şifre hatalı!",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}

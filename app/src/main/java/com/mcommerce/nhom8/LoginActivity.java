package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout inpEmail_Login, inpPass_Login;
    Button btnLogin;
    TextView txtSignUpNow_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkview();
        addEvent();
    }

    private void addEvent() {
        txtSignUpNow_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void linkview() {
        btnLogin = findViewById(R.id.btnLogin);
        inpEmail_Login = findViewById(R.id.inpEmail_Login);
        inpPass_Login = findViewById(R.id.inpPass_Login);
        txtSignUpNow_login = findViewById(R.id.txtSignUpNow_login);

    }
}
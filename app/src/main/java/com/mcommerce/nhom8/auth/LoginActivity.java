package com.mcommerce.nhom8.auth;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inpEmail_Login, inpPass_Login;
    private Button btnLogin;
    private TextView txtSignUpNow_login, txtForgotPassword_login;
    private ProgressDialog progressDialog;
    private LinearLayout llThamQuan_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        linkview();
        addEvent();
    }

    private void addEvent() {

        txtSignUpNow_login.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));

        llThamQuan_login.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
        txtForgotPassword_login.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class)));

        btnLogin.setOnClickListener(v -> {
            if (inpPass_Login.getEditText().getText().toString().trim().equals("") || inpEmail_Login.getEditText().getText().toString().trim().equals("")){
                inpPass_Login.setErrorEnabled(true); inpPass_Login.setError("Vui lòng nhập mật khẩu");
                inpEmail_Login.setErrorEnabled(true); inpEmail_Login.setError("Vui lòng nhập email");
            } else {
                inpPass_Login.setErrorEnabled(false);
                inpEmail_Login.setErrorEnabled(false);

                String email = inpEmail_Login.getEditText().getText().toString().trim();
                String password = inpPass_Login.getEditText().getText().toString().trim();
                verifyAndLogIn(email, password);
            }
        });
    }

    private void verifyAndLogIn(String email, String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, task -> {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Đăng nhập không thành công.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void linkview() {
        btnLogin = findViewById(R.id.btnLogin);
        inpEmail_Login = findViewById(R.id.inpEmail_Login);
        inpPass_Login = findViewById(R.id.inpPass_Login);
        txtSignUpNow_login = findViewById(R.id.txtSignUpNow_login);
        txtForgotPassword_login = findViewById(R.id.txtForgotPassword_login);
        llThamQuan_login = findViewById(R.id.llThamQuan_login);
    }
}
package com.mcommerce.nhom8.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inpEmail_Login, inpPass_Login;
    private Button btnLogin;
    private TextView txtSignUpNow_login, txtForgotPassword_login;;
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inpPass_Login.getEditText().getText().toString().trim().equals("") || inpEmail_Login.getEditText().getText().toString().trim().equals("")){
                    inpPass_Login.setErrorEnabled(true); inpPass_Login.setError("Vui lòng nhập mật khẩu");
                    inpEmail_Login.setErrorEnabled(true); inpEmail_Login.setError("Vui lòng nhập email");
                } else {
                    inpPass_Login.setErrorEnabled(false);
                    inpEmail_Login.setErrorEnabled(false);

                    String email = inpEmail_Login.getEditText().getText().toString().trim();
                    String password = inpPass_Login.getEditText().getText().toString().trim();
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        User mUser = snapshot.getValue(User.class);
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Đăng nhập không thành công.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        txtForgotPassword_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
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
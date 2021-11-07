package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    TextView txtDieuKhoanDichVu_signup, txtChinhSachBaoMat_signup, txtDangNhap_signup;
    TextInputLayout inpHoTen_signup,
                    inpEmailSdt_signup,
                    inpMatKhau_signup,
                    inpNhapLaiMatKhau_signup;
    Button btnTiepTuc_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        linkViews();
        addEvent();
    }

    private void addEvent() {
        inpEmailSdt_signup.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null){
                    if (checkValidateEmail(s.toString())) {
                        inpEmailSdt_signup.setEndIconDrawable(R.drawable.ic_heart);
                        inpEmailSdt_signup.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                        inpEmailSdt_signup.setErrorEnabled(false);
                    }
                    else{
                        inpEmailSdt_signup.setErrorEnabled(true);
                        inpEmailSdt_signup.setError("Email không hợp lệ");
                    }
                }
                if (s == null) {
                    inpEmailSdt_signup.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        inpMatKhau_signup.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    if (!checkValidatePassword(s.toString())) {
                        inpMatKhau_signup.setErrorEnabled(true);
                        inpMatKhau_signup.setError("Mật khẩu phải có ít nhất một ký tự số, 1 ký tự hoa, 1 ký tự thường và 1 ký tự đặc biệc thuộc @,#,$,%,!");
                    } else {
                        inpMatKhau_signup.setErrorEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inpNhapLaiMatKhau_signup.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = inpMatKhau_signup.getEditText().getText().toString();
                 if (s != null) {
                    if (!password.equals(s.toString())){
                        inpNhapLaiMatKhau_signup.setErrorEnabled(true);
                        inpNhapLaiMatKhau_signup.setError("Mật khẩu không đúng");
                    }
                    else {
                        inpNhapLaiMatKhau_signup.setErrorEnabled(false);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnTiepTuc_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inpEmailSdt_signup.getEditText().getText().toString().trim();
                String password = inpMatKhau_signup.getEditText().getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private boolean checkValidatePassword(String password){
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        return password.matches(regex);
    }

    private boolean checkValidateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private void linkViews() {
        txtDieuKhoanDichVu_signup = findViewById(R.id.txtDieuKhoanDichVu_signup);
        txtDieuKhoanDichVu_signup.setPaintFlags(txtDieuKhoanDichVu_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtChinhSachBaoMat_signup = findViewById(R.id.txtChinhSachBaoMat_signup);
        txtChinhSachBaoMat_signup.setPaintFlags(txtChinhSachBaoMat_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtDangNhap_signup = findViewById(R.id.txtDangNhap_signup);
        txtDangNhap_signup.setPaintFlags(txtDangNhap_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        inpHoTen_signup = findViewById(R.id.inpHoTen_signup);
        inpEmailSdt_signup = findViewById(R.id.inpEmailSdt_signup);
        inpMatKhau_signup = findViewById(R.id.inpMatKhau_signup);
        inpNhapLaiMatKhau_signup = findViewById(R.id.inpNhapLaiMatKhau_signup);

        btnTiepTuc_signup = findViewById(R.id.btnTiepTuc_signup);

/*        edtHoTen_signup = findViewById(R.id.edtHoTen_signup);
        edtEmailSdt_signup = findViewById(R.id.edtEmailSdt_signup);
        edtMatKhau_signup = findViewById(R.id.edtMatKhau_signup);
        edtNhapLaiMatKhau_signup = findViewById(R.id.edtNhapLaiMatKhau_signup);*/
    }
}
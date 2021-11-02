package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    TextView txtDieuKhoanDichVu_signup, txtChinhSachBaoMat_signup, txtDangNhap_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        linkViews();
    }

    private void linkViews() {
        txtDieuKhoanDichVu_signup = findViewById(R.id.txtDieuKhoanDichVu_signup);
        txtDieuKhoanDichVu_signup.setPaintFlags(txtDieuKhoanDichVu_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtChinhSachBaoMat_signup = findViewById(R.id.txtChinhSachBaoMat_signup);
        txtChinhSachBaoMat_signup.setPaintFlags(txtChinhSachBaoMat_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtDangNhap_signup = findViewById(R.id.txtDangNhap_signup);
        txtDangNhap_signup.setPaintFlags(txtDangNhap_signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
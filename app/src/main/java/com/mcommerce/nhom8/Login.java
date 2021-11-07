package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout txtILEmail, txtILPass;
    EditText edtEmail, edtPass;
    Button btnLogin;
    boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLogin);
        txtILEmail = findViewById(R.id.txtILEmail_Login);
        txtILPass = findViewById(R.id.txtILPass_Login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        String email="admin";
        String pass="admin";
        final int[] counter = {5};

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

//                if (email.isEmpty() || pass.isEmpty())
//                {
//                    Toast.makeText(MainActivity.this, "Điền thông tin còn thiếu", Toast.LENGTH_SHORT).show();
//                }else {
//                    isValid = validate(email,pass);
//                    if(!isValid)
//                    {
//                        counter[0]--;
//                        Toast.makeText(MainActivity.this, "Điền thông tin còn thiếu", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//                if (edtEmail.getText().toString().equals("admin") && edtPass.getText().toString().equals("admin"))
//                Toast.makeText(MainActivity.this,"Trứng xin chào!!",Toast.LENGTH_SHORT).show();}
//            else
//                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
        });
//
//
//    }
//    private boolean validate(String email,String pass)
//    {
//     if(email.equals(edtEmail) && pass.equals(edtPass))
//     {return true;}
//        return false;
    }
}
package com.mcommerce.nhom8.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mcommerce.interfaces.CheckEmailExisted;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    private TextView txtDieuKhoanDichVu_aSignUp, txtChinhSachBaoMat_aSignUp, txtDangNhap_aSignUp;
    private TextInputLayout inpHoTen_aSignUp,
            inpEmail_aSignUp,
            inpMatKhau_aSignUp,
            inpPhone_aSignUp,
            inpNhapLaiMatKhau_aSignUp;
    private CheckBox chkAccept;
    private Button btnTiepTuc_aSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        linkViews();
        addEvent();
    }

    private void linkViews() {
        txtDieuKhoanDichVu_aSignUp =findViewById(R.id.txtDieuKhoanDichVu_aSignUp);
        txtDieuKhoanDichVu_aSignUp.setPaintFlags(txtDieuKhoanDichVu_aSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtChinhSachBaoMat_aSignUp =findViewById(R.id.txtChinhSachBaoMat_aSignUp);
        txtChinhSachBaoMat_aSignUp.setPaintFlags(txtChinhSachBaoMat_aSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtDangNhap_aSignUp =findViewById(R.id.txtDangNhap_aSignUp);
        txtDangNhap_aSignUp.setPaintFlags(txtDangNhap_aSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        inpHoTen_aSignUp =findViewById(R.id.inpHoTen_aSignUp);
        inpEmail_aSignUp =findViewById(R.id.inpEmail_aSignUp);
        inpMatKhau_aSignUp =findViewById(R.id.inpMatKhau_aSignUp);
        inpNhapLaiMatKhau_aSignUp =findViewById(R.id.inpNhapLaiMatKhau_aSignUp);
        inpPhone_aSignUp =findViewById(R.id.inpPhone_aSignUp);

        btnTiepTuc_aSignUp =findViewById(R.id.btnTiepTuc_aSignUp);
        chkAccept = findViewById(R.id.chkAccept);

        progressDialog = new ProgressDialog(this);
    }

    private void addEvent() {
        inpEmail_aSignUp.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()){
                    if (checkValidateEmail(s.toString())) {
                        inpEmail_aSignUp.setEndIconDrawable(R.drawable.ic_check_fill_green);
                        inpEmail_aSignUp.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                        inpEmail_aSignUp.setErrorEnabled(false);
                    }
                    else{
                        InvalidInput("Email không hợp lệ",inpEmail_aSignUp);
                    }
                } else {
                    inpEmail_aSignUp.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        inpPhone_aSignUp.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    if (checkValidatePhone(s.toString())){
                        inpEmail_aSignUp.setEndIconDrawable(R.drawable.ic_check_fill_green);
                        inpEmail_aSignUp.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                        inpPhone_aSignUp.setErrorEnabled(false);
                    }
                    else {
                        InvalidInput("Số điện thoại không hợp lệ",inpPhone_aSignUp);
                    }
                } else {
                    inpPhone_aSignUp.setErrorEnabled(false);
                    phone =inpPhone_aSignUp.toString().trim();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnTiepTuc_aSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;

                if (inpHoTen_aSignUp.getEditText().getText().toString().isEmpty()){
                    InvalidInput("Vui lòng điền tên", inpHoTen_aSignUp);
                    valid = false;
                }

                if (inpEmail_aSignUp.getEditText().getText().toString().isEmpty()){
                    InvalidInput("Vui lòng điền email", inpEmail_aSignUp);
                    valid = false;
                }

                if (inpMatKhau_aSignUp.getEditText().getText().toString().isEmpty()){
                    InvalidInput("Vui lòng điền mật khẩu", inpMatKhau_aSignUp);
                    valid = false;
                }
                if (inpNhapLaiMatKhau_aSignUp.getEditText().getText().toString().isEmpty()){
                    InvalidInput("vui lòng nhập lại mật khẩu", inpNhapLaiMatKhau_aSignUp);
                    valid = false;
                } else {
                    String password = inpMatKhau_aSignUp.getEditText().getText().toString();
                    String passwordAgain = inpNhapLaiMatKhau_aSignUp.getEditText().getText().toString();
                        if (!password.equals(passwordAgain)){
                            InvalidInput("Mật khẩu không đúng", inpNhapLaiMatKhau_aSignUp);
                            valid = false;
                        }
                        else {
                            inpNhapLaiMatKhau_aSignUp.setErrorEnabled(false);
                            valid = true;
                        }
                }

                if (!chkAccept.isChecked()){
                    Toast.makeText(SignUpActivity.this,"Để đăng ký, ban cần chấp nhận các điều khoản", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if (!valid) {
                    return;
                }

                checkHaveRegisted(inpEmail_aSignUp.getEditText().getText().toString().trim(), new CheckEmailExisted() {
                    @Override

                    public void onCheck(boolean isRegistered) {
                        progressDialog.show();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                        if (isRegistered) {
                            InvalidInput("Email này đã được sử dụng để đăng ký", inpEmail_aSignUp);
                            progressDialog.dismiss();
                            return;
                        } else {
                            phone= inpPhone_aSignUp.getEditText().getText().toString();
                            if (phone.startsWith("0")) {
                                phone = phone.substring(1,phone.length());
                            }
                            phone = "+84"+phone;
                            sendOTP(phone);
                        }
                    }
                });


            }
        });
        txtDangNhap_aSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    private void InvalidInput(String text, TextInputLayout input) {
        input.setError(text);
        input.setErrorEnabled(true);
    }

    private boolean checkValidatePhone(String phone) {
//        String regex = "^(0|84|\\+84)   (\\s|\\.)?   ((3[2-9]) | (5[689]) | (7[06-9]) | (8[1-689]) | (9[0-46-9]))   (\\d)   (\\s|\\.)?   (\\d{3})  (\\s|\\.)?   (\\d{3})$";
        String regex = "^(0|84|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return phone.matches(regex);
    }

    private boolean checkValidateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private void checkHaveRegisted(String email, CheckEmailExisted checkEmailExisted){
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean result = !task.getResult().getSignInMethods().isEmpty();
                checkEmailExisted.onCheck(result);
            }
        });
    }

    private void sendOTP(String phone) {
        progressDialog.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                String email = inpEmail_aSignUp.getEditText().getText().toString().trim();
                                String password = inpMatKhau_aSignUp.getEditText().getText().toString().trim();

                                String name = inpHoTen_aSignUp.getEditText().getText().toString();
                                createUserWithEmail(email, password, phone, name, phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(SignUpActivity.this,"Xác minh không thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                openVerifyOTPActivity(s);
                            }

                        })
                        .build();
        progressDialog.dismiss();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void openVerifyOTPActivity(String verifyID) {
        Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.NAME, inpHoTen_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.EMAIL, inpEmail_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.PASSWORD, inpMatKhau_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.VERIFY_ID, verifyID);
        startActivity(intent);
    }

    private void linktoEmailPassword(AuthCredential credential){
        mAuth.getCurrentUser()
                .linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Liên kết số điện thoại không thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, "Đã có lỗi xảy ra",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createUserWithEmail(String email, String password, String phone, String name, AuthCredential phoneAuthCredential) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                    user.updateProfile(profileChangeRequest);
                    User mUser = new User();
                    mUser.setUserID(user.getUid());
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child(Key.USER).child(mUser.getUserID()).setValue(mUser);
                    linktoEmailPassword(phoneAuthCredential);
                }
            }
        });
    }
}
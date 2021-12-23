package com.mcommerce.nhom8.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {


    private EditText edt_otp1_aVerifyPhone,
            edt_otp2_aVerifyPhone,
            edt_otp3_aVerifyPhone,
            edt_otp4_aVerifyPhone,
            edt_otp5_aVerifyPhone,
            edt_otp6_aVerifyPhone;
    private TextView txtResendOTP_aVerifyPhone;
    private FirebaseAuth mAuth;
    private Button btnAuthOTP_aVerifyPhone;
    private PhoneAuthProvider.ForceResendingToken resendingToken;

    private String name, email, phone, password , verifyID, strOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        mAuth = FirebaseAuth.getInstance();
        linkView();
        initData();
        setUpEditText();
        addEvent();

    }

    private void linkView() {
        edt_otp1_aVerifyPhone = findViewById(R.id.edt_otp1_aVerifyPhone);
        edt_otp2_aVerifyPhone = findViewById(R.id.edt_otp2_aVerifyPhone);
        edt_otp3_aVerifyPhone = findViewById(R.id.edt_otp3_aVerifyPhone);
        edt_otp4_aVerifyPhone = findViewById(R.id.edt_otp4_aVerifyPhone);
        edt_otp5_aVerifyPhone = findViewById(R.id.edt_otp5_aVerifyPhone);
        edt_otp6_aVerifyPhone = findViewById(R.id.edt_otp6_aVerifyPhone);

        txtResendOTP_aVerifyPhone = findViewById(R.id.txtResendOTP_aVerifyPhone);

        btnAuthOTP_aVerifyPhone = findViewById(R.id.btnAuthOTP_aVerifyPhone);
    }

    private void setUpEditText() {
        edt_otp1_aVerifyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp2_aVerifyPhone.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp2_aVerifyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp3_aVerifyPhone.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp3_aVerifyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp4_aVerifyPhone.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp4_aVerifyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp5_aVerifyPhone.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp5_aVerifyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp6_aVerifyPhone.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void initData() {
        name = getIntent().getStringExtra(Constant.NAME);
        email = getIntent().getStringExtra(Constant.EMAIL);
        phone = getIntent().getStringExtra(Constant.PHONE);
        password = getIntent().getStringExtra(Constant.PASSWORD);
        verifyID = getIntent().getStringExtra(Constant.VERIFY_ID);
        resendingToken = getIntent().getParcelableExtra(Constant.TOKEN);
    }

    private void addEvent() {
        setUpEditText();
        btnAuthOTP_aVerifyPhone.setOnClickListener(v -> {
            strOTP = edt_otp1_aVerifyPhone.getText().toString()+edt_otp2_aVerifyPhone.getText().toString()+edt_otp3_aVerifyPhone.getText().toString()+edt_otp4_aVerifyPhone.getText().toString()+edt_otp5_aVerifyPhone.getText().toString()+edt_otp6_aVerifyPhone.getText().toString();
            createUserWithEmail();
        });
        txtResendOTP_aVerifyPhone.setOnClickListener(v -> requestOTPCodeAgain());
    }

    private void requestOTPCodeAgain(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setForceResendingToken(resendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                linktoEmailPassword(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyPhoneActivity.this,"Xác minh không thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verifyID = s;
                                resendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void linktoEmailPassword(AuthCredential credential){
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = task.getResult().getUser();
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                    user.updateProfile(profileChangeRequest);
                User mUser = new User();
                    mUser.setUserID(user.getUid());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("User").child(mUser.getUserID()).setValue(mUser);
                startActivity(new Intent(VerifyPhoneActivity.this, MainActivity.class));
                finish();
            } else {
                FirebaseUser user = mAuth.getCurrentUser();
                    user.delete();
                String errorMessage = ((FirebaseAuthException) task.getException()).getMessage();
                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                System.out.println("lỗi "+errorCode);
                System.out.println("lỗi "+ errorMessage);
                switch (errorCode) {
                    case "ERROR_INVALID_VERIFICATION_CODE":
                        Toast.makeText(VerifyPhoneActivity.this,"Mã OTP không hợp lệ",Toast.LENGTH_SHORT).show();
                        break;
                    case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                        Toast.makeText(VerifyPhoneActivity.this,"Số điện thoại đã được đăng ký",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(VerifyPhoneActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }


    private void createUserWithEmail() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifyID,strOTP);
                linktoEmailPassword(phoneAuthCredential);
            }
            else {
                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                switch (errorCode) {
                    case "ERROR_USER_TOKEN_EXPIRED":
                        Toast.makeText(VerifyPhoneActivity.this, "Quá thời gian để xác thực, vui lòng thực hiện đăng ký lại", Toast.LENGTH_LONG).show();
                        break;
                    case "ERROR_WEAK_PASSWORD":
                        Toast.makeText(VerifyPhoneActivity.this, "Mật khẩu yếu, hãy thử mật khẩu có 6 ký tự trở lên", Toast.LENGTH_LONG).show();
                        break;
                    case "ERROR_EMAIL_ALREADY_IN_USE":
                        Toast.makeText(VerifyPhoneActivity.this, "Email đã được sử dụng", Toast.LENGTH_LONG).show();
                        break;
                    case "ERROR_INVALID_EMAIL":
                        Toast.makeText(VerifyPhoneActivity.this, "Email không hợp lệ, vui lòng thử với email khác", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
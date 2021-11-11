package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
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
    }


    private void addEvent() {
        setUpEditText();
        btnAuthOTP_aVerifyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOTP = edt_otp1_aVerifyPhone.getText().toString()+edt_otp2_aVerifyPhone.getText().toString()+edt_otp3_aVerifyPhone.getText().toString()+edt_otp4_aVerifyPhone.getText().toString()+edt_otp5_aVerifyPhone.getText().toString()+edt_otp6_aVerifyPhone.getText().toString();
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifyID,strOTP);
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
        });
        txtResendOTP_aVerifyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
                                signInWithPhoneAuthCredential(phoneAuthCredential);
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

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = task.getResult().getUser();
                            linktoEmailPassword(credential);
                            startActivity(new Intent(VerifyPhoneActivity.this, MainActivity.class));
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void linktoEmailPassword(AuthCredential credential ){
        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                        } else {

                        }
                    }
                });
    }
}
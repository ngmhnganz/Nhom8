package com.mcommerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;

public class VerifyPhoneFragment extends Fragment {

    private View view;
    private EditText edt_otp1_fmOtp,
            edt_otp2_fmOtp,
            edt_otp3_fmOtp,
            edt_otp4_fmOtp,
            edt_otp5_fmOtp,
            edt_otp6_fmOtp;
    private FirebaseAuth mAuth;
    private Button btnAuthOTP_fmOtp;

    private String name, email, phone, password , verifyID, strOTP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_verify_phone, container,false);
        mAuth = FirebaseAuth.getInstance();
        linkView();
        addEvent();
        getOTP();
        return view;
    }

    private void getOTP() {

    }

    private void addEvent() {
        setUpEditText();
        btnAuthOTP_fmOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOTP = edt_otp1_fmOtp.getText().toString()+edt_otp2_fmOtp.getText().toString()+edt_otp3_fmOtp.getText().toString()+edt_otp4_fmOtp.getText().toString()+edt_otp5_fmOtp.getText().toString()+edt_otp6_fmOtp.getText().toString();
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifyID,strOTP);
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
        });
    }

    private void setUpEditText() {
        edt_otp1_fmOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp2_fmOtp.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp2_fmOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp3_fmOtp.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp3_fmOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp4_fmOtp.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp4_fmOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp5_fmOtp.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp5_fmOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp6_fmOtp.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void linkView() {
        edt_otp1_fmOtp = view.findViewById(R.id.edt_otp1_fmOtp);
        edt_otp2_fmOtp = view.findViewById(R.id.edt_otp2_fmOtp);
        edt_otp3_fmOtp = view.findViewById(R.id.edt_otp3_fmOtp);
        edt_otp4_fmOtp = view.findViewById(R.id.edt_otp4_fmOtp);
        edt_otp5_fmOtp = view.findViewById(R.id.edt_otp5_fmOtp);
        edt_otp6_fmOtp = view.findViewById(R.id.edt_otp6_fmOtp);
        btnAuthOTP_fmOtp = view.findViewById(R.id.btnAuthOTP_fmOtp);

    }

    public void getSignUpData(String name, String email, String phone, String password, String verifyID) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.verifyID = verifyID;
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(getActivity(), MainActivity.class));
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

}



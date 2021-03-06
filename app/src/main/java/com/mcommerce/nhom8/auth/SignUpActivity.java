package com.mcommerce.nhom8.auth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
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
import com.mcommerce.interfaces.CheckEmailExisted;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

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
                        InvalidInput("Email kh??ng h???p l???",inpEmail_aSignUp);
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
                        inpPhone_aSignUp.setEndIconDrawable(R.drawable.ic_check_fill_green);
                        inpPhone_aSignUp.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
                        inpPhone_aSignUp.setErrorEnabled(false);
                    }
                    else {
                        InvalidInput("S??? ??i???n tho???i kh??ng h???p l???",inpPhone_aSignUp);
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

        btnTiepTuc_aSignUp.setOnClickListener(v -> {
            boolean valid;

            if (inpHoTen_aSignUp.getEditText().getText().toString().isEmpty()){
                InvalidInput("Vui l??ng ??i???n t??n", inpHoTen_aSignUp);
                valid = false;
            }

            if (inpEmail_aSignUp.getEditText().getText().toString().isEmpty()){
                InvalidInput("Vui l??ng ??i???n email", inpEmail_aSignUp);
                valid = false;
            }

            if (inpMatKhau_aSignUp.getEditText().getText().toString().isEmpty()){
                InvalidInput("Vui l??ng ??i???n m???t kh???u", inpMatKhau_aSignUp);
                valid = false;
            }
            if (inpNhapLaiMatKhau_aSignUp.getEditText().getText().toString().isEmpty()){
                InvalidInput("vui l??ng nh???p l???i m???t kh???u", inpNhapLaiMatKhau_aSignUp);
                valid = false;
            } else {
                String password = inpMatKhau_aSignUp.getEditText().getText().toString();
                if (checkValidatePassword(password)) {
                    String passwordAgain = inpNhapLaiMatKhau_aSignUp.getEditText().getText().toString();
                    if (!password.equals(passwordAgain)){
                        InvalidInput("M???t kh???u kh??ng ????ng", inpNhapLaiMatKhau_aSignUp);
                        valid = false;
                    }
                    else {
                        inpNhapLaiMatKhau_aSignUp.setErrorEnabled(false);
                        valid = true;
                    }
                }
                else {
                    InvalidInput("M???t kh???u c???n t???i thi???u 6 k?? t??? v?? t???i ??a 20 k?? t???", inpMatKhau_aSignUp);
                    valid = false;
                }

            }

            if (!chkAccept.isChecked()){
                Toast.makeText(SignUpActivity.this,"????? ????ng k??, ban c???n ch???p nh???n c??c ??i???u kho???n", Toast.LENGTH_SHORT).show();
                valid = false;
            }

            if (!valid) {
                return;
            } else {

                checkHaveRegisted(inpEmail_aSignUp.getEditText().getText().toString().trim(), isRegistered -> {
                    progressDialog.show();
                    if (isRegistered) {
                        InvalidInput("Email n??y ???? ???????c s??? d???ng ????? ????ng k??", inpEmail_aSignUp);
                        progressDialog.dismiss();
                        return;
                    } else {
                        phone= inpPhone_aSignUp.getEditText().getText().toString();
                        if (phone.startsWith("0")) {
                            phone = phone.substring(1);
                        }
                        phone = "+84"+phone;
                        sendOTP(phone);
                    }
                });
            }
        });
        txtDangNhap_aSignUp.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this, LoginActivity.class)));
    }

    private void InvalidInput(String text, TextInputLayout input) {
        input.setError(text);
    }
    //        String regex = "^(0|84|\\+84)   (\\s|\\.)?   ((3[2-9]) | (5[689]) | (7[06-9]) | (8[1-689]) | (9[0-46-9]))   (\\d)   (\\s|\\.)?   (\\d{3})  (\\s|\\.)?   (\\d{3})$";'

    private boolean checkValidatePhone(String phone) {
        String regex = "^(0)((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\d{3})(\\d{3})$";
        return phone.matches(regex);
    }

    private boolean checkValidatePassword(String phone) {
        String regex = "^[a-z0-9_-]{6,20}$";
        return phone.matches(regex);
    }

    private boolean checkValidateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private void checkHaveRegisted(String email, CheckEmailExisted checkEmailExisted){
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
            boolean result = !task.getResult().getSignInMethods().isEmpty();
            checkEmailExisted.onCheck(result);
        });
    }

    private void sendOTP(String phone) {
        PhoneAuthOptions options = PhoneAuthOptions
                .newBuilder(mAuth)
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
                        Toast.makeText(SignUpActivity.this,"X??c minh kh??ng th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        progressDialog.dismiss();
                        openVerifyOTPActivity(s, forceResendingToken);
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void openVerifyOTPActivity(String verifyID, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.NAME, inpHoTen_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.EMAIL, inpEmail_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.PASSWORD, inpMatKhau_aSignUp.getEditText().getText().toString().trim());
        intent.putExtra(Constant.VERIFY_ID, verifyID);
        intent.putExtra(Constant.TOKEN, forceResendingToken);
        startActivity(intent);
        finish();
    }

    private void createUserWithEmail(String email, String password, String phone, String name, AuthCredential phoneAuthCredential) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                linktoEmailPassword(phoneAuthCredential, name);
            } else {
                if(!task.isSuccessful()) {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    String errorMessage = task.getException().getLocalizedMessage();

                    switch (errorCode) {
                        case "ERROR_USER_TOKEN_EXPIRED":
                            Toast.makeText(SignUpActivity.this, "Qu?? th???i gian ????? x??c th???c, vui l??ng th???c hi???n ????ng k?? l???i", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_WEAK_PASSWORD":
                            Toast.makeText(SignUpActivity.this, "M???t kh???u y???u, h??y th??? m???t kh???u c?? 6 k?? t??? tr??? l??n", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            Toast.makeText(SignUpActivity.this, "Email ???? ???????c s??? d???ng", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_INVALID_EMAIL":
                            Toast.makeText(SignUpActivity.this, "Email kh??ng h???p l???, vui l??ng th??? v???i email kh??c", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                            break;
                    }

                }
            }
        });
    }

    private void linktoEmailPassword(AuthCredential credential, String name) {
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
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                finish();
            } else {
                FirebaseUser user = mAuth.getCurrentUser();
                user.delete();
                String errorMessage = ((FirebaseAuthException) task.getException()).getMessage();
                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                System.out.println("l???i " + errorCode);
                System.out.println("l???i " + errorMessage);
                switch (errorCode) {
                    case "ERROR_INVALID_VERIFICATION_CODE":
                        Toast.makeText(SignUpActivity.this, "M?? OTP kh??ng h???p l???", Toast.LENGTH_SHORT).show();
                        break;
                    case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                        Toast.makeText(SignUpActivity.this, "S??? ??i???n tho???i ???? ???????c ????ng k??", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
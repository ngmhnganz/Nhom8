package com.mcommerce.nhom8.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mcommerce.nhom8.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UserInfoActivity extends AppCompatActivity {


    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imv_aUserInfo;
    private TextInputLayout inpUserEmail_aUserInfo,
                            inpUserName_aUserInfo,
                            inpUserBirthday_aUserInfo,
                            inpUserPhone_aUserInfo;

    private ImageButton btnBack_aUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        linkview();
        setUI();
        loadUserInfo();
        addEvent();
    }


    private void linkview() {
        autoCompleteTextView = findViewById(R.id.txtGender);
        imv_aUserInfo = findViewById(R.id.imv_aUserInfo);

        inpUserEmail_aUserInfo = findViewById(R.id.inpUserEmail_aUserInfo);
        inpUserName_aUserInfo = findViewById(R.id.inpUserName_aUserInfo);
        inpUserBirthday_aUserInfo = findViewById(R.id.inpUserBirthday_aUserInfo);
        inpUserPhone_aUserInfo = findViewById(R.id.inpUserPhone_aUserInfo);

        btnBack_aUserInfo = findViewById(R.id.btnBack_aUserInfo);
    }

    private void setUI() {
        //region Adapter cho AutoCompletetext Gender
        String[] genderList = {"Nữ", "Nam", "Non-binary", "Pokemon", "Khác"};
        ArrayAdapter adapter = new ArrayAdapter<String>(UserInfoActivity.this,R.layout.layout_gender_item,genderList);
        autoCompleteTextView.setAdapter(adapter);
        //endregion
    }

    private void loadUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri uri = user.getPhotoUrl();
        String phone = user.getPhoneNumber();
        inpUserEmail_aUserInfo.getEditText().setText(email);
        inpUserName_aUserInfo.getEditText().setText(name);
        inpUserPhone_aUserInfo.getEditText().setText(phone);
        Glide.with(this).load(uri).error(R.drawable.default_ava).into(imv_aUserInfo);
    }

    private void addEvent() {

        btnBack_aUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imv_aUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inpUserBirthday_aUserInfo.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày sinh");
                MaterialDatePicker<Long> materialDatePicker = builder.build();
                materialDatePicker.show(getSupportFragmentManager(), "DatePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        inpUserBirthday_aUserInfo.getEditText().setText(simpleDateFormat.format(new Timestamp(selection)));
                    }
                });
            }
        });

    }

}
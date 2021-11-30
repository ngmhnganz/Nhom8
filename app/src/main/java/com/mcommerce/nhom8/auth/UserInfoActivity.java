package com.mcommerce.nhom8.auth;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;
import com.theartofdev.edmodo.cropper.CropImage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
public class UserInfoActivity extends AppCompatActivity {


    private AutoCompleteTextView txtGender;
    private ImageView imv_aUserInfo;
    private TextInputLayout inpUserEmail_aUserInfo,
                            inpUserName_aUserInfo,
                            inpUserBirthday_aUserInfo,
                            inpUserPhone_aUserInfo,
                            inpUserAdress_aUserInfo;

    private ImageButton btnBack_aUserInfo;
    private Button btnSave_aUserInfo;
    private BottomSheetDialog sheetDialog = null;
    private ActivityResultLauncher<Intent> moGallery;
    private Uri userImage = null;

    private boolean valid = true;
    final private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User/"+user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        linkview();
        loadUserInfo();
        setUI();
        addEvent();
    }


    private void linkview() {
        txtGender = findViewById(R.id.txtGender);
        imv_aUserInfo = findViewById(R.id.imv_aUserInfo);

        inpUserEmail_aUserInfo = findViewById(R.id.inpUserEmail_aUserInfo);
        inpUserName_aUserInfo = findViewById(R.id.inpUserName_aUserInfo);
        inpUserBirthday_aUserInfo = findViewById(R.id.inpUserBirthday_aUserInfo);
        inpUserPhone_aUserInfo = findViewById(R.id.inpUserPhone_aUserInfo);
        inpUserAdress_aUserInfo = findViewById(R.id.inpUserAdress_aUserInfo);

        btnBack_aUserInfo = findViewById(R.id.btnBack_aUserInfo);
        btnSave_aUserInfo = findViewById(R.id.btnSave_aUserInfo);
    }

    private void setUI() {
        //region Adapter cho AutoCompletetext Gender
        String[] genderList = {"Nữ", "Nam", "Non-binary", "Pokemon", "Khác"};
        ArrayAdapter adapter = new ArrayAdapter<>(UserInfoActivity.this,R.layout.layout_gender_item,genderList);
        txtGender.setAdapter(adapter);
        //endregion

        //region Ảnh người dùng
        moGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if ( result.getResultCode()==RESULT_OK && result.getData()!=null){
                //result là một cái màn hình resul
                // getdata lần nhất dc màn hình
                // get data lần hai dc data đã pick
                Uri uri = result.getData().getData();
                // sau khi lấy được dữ liệu uri thì truyền qua màn hình crop
                openCrop(uri);
            }
        });
        //endregion
    }

    private void loadUserInfo() {
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
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inpUserBirthday_aUserInfo.getEditText().setText(snapshot.child("userBirthday").getValue().toString());
                inpUserAdress_aUserInfo.getEditText().setText(snapshot.child("userAddress").getValue().toString());
                txtGender.setText(snapshot.child("userGender").getValue().toString(), false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).error(R.drawable.default_ava).into(imv_aUserInfo);
    }

    private void addEvent() {

        btnBack_aUserInfo.setOnClickListener(v -> finish());

        imv_aUserInfo.setOnClickListener(v -> {
            createBottomSheet();
            sheetDialog.show();
        });

        inpUserBirthday_aUserInfo.getEditText().setOnClickListener(v -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày sinh");
            MaterialDatePicker<Long> materialDatePicker = builder.build();
            materialDatePicker.show(getSupportFragmentManager(), "DatePicker");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                inpUserBirthday_aUserInfo.getEditText().setText(simpleDateFormat.format(new Timestamp(selection)));
            });
        });

        btnSave_aUserInfo.setOnClickListener(v -> {
            // nếu có thông tin chưa hợp lệ thì return
            if (!valid) {
                return;
            }
            // nếu các thông tin hợp lệ thì lưu dữ liệu lên server
            String uBirthday = inpUserBirthday_aUserInfo.getEditText().getText().toString();
            String uGender = txtGender.getText().toString();
            String uAdress = inpUserAdress_aUserInfo.getEditText().getText().toString();
            String uName = inpUserName_aUserInfo.getEditText().getText().toString();
            if (!uBirthday.equals("")){
                userRef.child("userBirthday").setValue(uBirthday);
            }
            if (!uGender.equals("")){
                userRef.child("userGender").setValue(uGender);
            }
            if (!uAdress.equals("")){
                userRef.child("userAddress").setValue(uAdress);
            }
            UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
            if (!uName.equals("")){
                builder.setDisplayName(uName);
            }
            if (userImage !=null){
                builder.setPhotoUri(userImage);
            }
            UserProfileChangeRequest profileUpdates = builder.build();
            user.updateProfile(profileUpdates);
            user.updateEmail(inpUserEmail_aUserInfo.getEditText().getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(UserInfoActivity.this, "Thành công set email",Toast.LENGTH_SHORT).show();
                }
            });
        });

        inpUserEmail_aUserInfo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    InvalidInput("Không được để trống", inpUserEmail_aUserInfo);
                    valid = false;
                } else
                if (!checkValidateEmail(s.toString())){
                    InvalidInput("Vui lòng nhập email hợp lệ", inpUserEmail_aUserInfo);
                    valid = false;
                } else {
                    valid = true;
                    inpUserEmail_aUserInfo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inpUserPhone_aUserInfo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    InvalidInput("Không được để trống", inpUserPhone_aUserInfo);
                    valid = false;
                } else
                if (!checkValidatePhone(s.toString())){
                    InvalidInput("Vui lòng nhập số điện thoại hợp lệ", inpUserPhone_aUserInfo);
                    valid = false;
                } else {
                    valid = true;
                    inpUserPhone_aUserInfo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //region Valid
    private void InvalidInput(String text, TextInputLayout input) {
        input.setError(text);
        input.setErrorEnabled(true);
    }
    private boolean checkValidateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    private boolean checkValidatePhone(String phone) {
        String regex = "^(0|84|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return phone.matches(regex);
    }
    //endregion

    //region BottomSheet
    private void createBottomSheet() {
        if (sheetDialog==null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_choose_ava, null);
            TextView txtCamera, txtGallery;

            txtCamera = view.findViewById(R.id.txtCamera);
            txtGallery = view.findViewById(R.id.txtGallery);

            txtCamera.setOnClickListener(v -> {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                moGallery.launch(intent);
                sheetDialog.dismiss();
            });

            txtGallery.setOnClickListener(v -> {
                requestPermission();
                sheetDialog.dismiss();
            });
            sheetDialog = new BottomSheetDialog(this);
            sheetDialog.setContentView(view);
        }
    }
    //endregion

    //region Quyền truy cập cho thư viện ảnh
    private void requestPermission(){
        // với android M về trước chỉ cần grant permission thông qua manifest
        // từ android m trở đi thì cần ng dùng cho phép quyền thì mới được, nhưng do min sdk là 24 nên phải check per
        //nếu như đã được cho quyền truy cập
        if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permissions, Constant.REQUEST_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==Constant.REQUEST_PERMISSION) {
            if (grantResults.length>0){
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openGallery();
                } else {
                    Toast.makeText(this,"Vui lòng cho phép truy cập vào thư viện", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //endregion

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        moGallery.launch(Intent.createChooser(intent,"Chọn ảnh"));
    }

    //region crop ảnh rồi set lên imageview
    private void openCrop(Uri imageUri){
        CropImage.activity(imageUri)
                .start(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                userImage = result.getUri();
                Glide.with(this).load(userImage).apply(RequestOptions.circleCropTransform()).into(imv_aUserInfo);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    //endregion


}
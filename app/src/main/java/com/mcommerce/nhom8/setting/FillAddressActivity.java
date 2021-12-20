package com.mcommerce.nhom8.setting;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.mcommerce.adapter.DiaPhuongAdapter;
import com.mcommerce.model.DiaPhuong;
import com.mcommerce.nhom8.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FillAddressActivity extends AppCompatActivity {
    public static final String DB_NAME = "dvhcvn.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase db = null;

    public static final String ADDRESS ="Selected Address";

    AutoCompleteTextView txtProvince, txtDistrict, txtWard;
    Button btnXong;
    Cursor cursor;
    TextInputLayout inpWard, inpProvince, inpDistrict, inpDetail;
    Intent returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_address);
        returnIntent = getIntent();
        copyDb();
        linkview();
        loadUI();
        addEvent();

    }

    private void copyDb() {
        File dbFIle = getDatabasePath(DB_NAME);
        if (!dbFIle.exists()){
            if (!copyDbfromAsset()) {
                Toast.makeText(FillAddressActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean copyDbfromAsset() {
        String dbPath = getApplicationInfo().dataDir + "/databases/" + DB_NAME;

        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File file = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);

            if (!file.exists()){
                file.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = inputStream.read(buffer))  > 0 ) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void linkview() {
        txtProvince = findViewById(R.id.txtProvince);
        txtDistrict = findViewById(R.id.txtDistrict);
        txtWard = findViewById(R.id.txtWard);
        btnXong = findViewById(R.id.btnXong);

        inpWard = findViewById(R.id.inpWard);
        inpProvince = findViewById(R.id.inpProvince);
        inpDistrict = findViewById(R.id.inpDistrict);
        inpDetail = findViewById(R.id.inpDetail);
    }

    private void loadUI() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        cursor=db.rawQuery("SELECT*FROM province",null);
        txtProvince.setAdapter(initAutoComplete(cursor,this));
        txtProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaPhuong district = (DiaPhuong) parent.getItemAtPosition(position);
                cursor=db.rawQuery("SELECT*FROM district WHERE province_id ='"+district.getId()+"'",null);
                txtDistrict.setText("");
                txtWard.setText("");
                txtDistrict.setAdapter(initAutoComplete(cursor, getBaseContext()));
                txtDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DiaPhuong ward = (DiaPhuong) parent.getItemAtPosition(position);
                        cursor=db.rawQuery("SELECT*FROM ward WHERE district_id ='"+ward.getId()+"'",null);
                        txtWard.setText("");
                        txtWard.setAdapter(initAutoComplete(cursor, getBaseContext()));
                    }
                });
            }
        });
    }

    private DiaPhuongAdapter initAutoComplete(Cursor cursor, Context mContext){
        List<DiaPhuong> diaPhuongs = new ArrayList<>();
        DiaPhuong mDiaPhuong;
        while (cursor.moveToNext()){
            mDiaPhuong = new DiaPhuong(cursor.getString(0),cursor.getString(1));
            diaPhuongs.add(mDiaPhuong);
        }
        DiaPhuongAdapter diaPhuongAdapter = new DiaPhuongAdapter(mContext, android.R.layout.simple_list_item_1, diaPhuongs);
        return diaPhuongAdapter;
    }

    private void addEvent() {
        btnXong.setOnClickListener(v -> {
            if (ValidateInputs()){
                sendResult();
                finish();
            }
        });
    }

    private boolean ValidateInputs(){

        if (txtProvince.getText().toString().equals("")){
            inpProvince.setError("Vui lòng không để trống");
            return false;
        } else if (txtDistrict.getText().toString().equals("")) {
            inpProvince.setErrorEnabled(false);
            inpDistrict.setError("Vui lòng không để trống");
            return false;
        } else if (txtWard.getText().toString().equals("")){
            inpDistrict.setErrorEnabled(false);
            inpWard.setError("Vui lòng không để trống");
            return false;
        } else if (inpDetail.getEditText().getText().toString().equals("")){
            inpWard.setErrorEnabled(false);
            inpDetail.setError("Vui lòng không để trống");
            return false;
        }
        return true;
    }

    private void sendResult() {
        String province, district, ward, details;
        province = txtProvince.getText().toString();
        district = txtDistrict.getText().toString();
        ward = txtWard.getText().toString();
        details = inpDetail.getEditText().getText().toString();
        returnIntent.putExtra(ADDRESS,details+", "+ward+", "+district+", "+province);
        setResult(Activity.RESULT_OK,returnIntent);
    }

    @Override
    public void onBackPressed() {
        if (ValidateInputs()){
            sendResult();
        }
        super.onBackPressed();
    }
}
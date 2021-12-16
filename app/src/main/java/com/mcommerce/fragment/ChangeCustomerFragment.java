package com.mcommerce.fragment;
import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.setting.FillAddressActivity;

public class ChangeCustomerFragment extends Fragment {

    public static final String TAG = "ChangeCustomerFragment";

    View view;
    TextView txtChooseAddress, txtAddress, txtCustomerName, txtCustomerPhone;
    Button btnXong;
    ImageButton btnBack;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent mIntent = result.getData();
                        if (mIntent.getStringExtra(FillAddressActivity.ADDRESS) != null){
                            String string = mIntent.getStringExtra(FillAddressActivity.ADDRESS);
                            txtAddress.setText(string);
                            txtAddress.setError(null);
                        }
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_customer,container,false);
        linkview();
        Bundle bundle = getArguments();
        if (bundle!= null){
            if (bundle.getString(ConfirmOrderFragment.ADDRESS)!= null){
                txtAddress.setText(bundle.getString(ConfirmOrderFragment.ADDRESS));
            }
            txtCustomerPhone.setText(bundle.getString(ConfirmOrderFragment.PHONE));
            txtCustomerName.setText(bundle.getString(ConfirmOrderFragment.NAME));
        }
        addEvent();
        return view;
    }

    private void linkview() {
        txtChooseAddress = view.findViewById(R.id.txtChooseAddress);
        txtAddress = view.findViewById(R.id.txtAddress);
        btnXong = view.findViewById(R.id.btnXong);

        txtCustomerName = view.findViewById(R.id.txtCustomerName);
        txtCustomerPhone = view.findViewById(R.id.txtCustomerPhone);
        btnBack=view.findViewById(R.id.btnBack);
    }

    private void addEvent() {
        txtChooseAddress.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FillAddressActivity.class);
            activityResultLauncher.launch(intent);
        });
        btnXong.setOnClickListener(v -> {
            if (ValidateInput()){
                String phone, address, name;
                address = txtAddress.getText().toString();
                phone = txtCustomerPhone.getText().toString();
                name = txtCustomerName.getText().toString();
                ConfirmOrderFragment confirmOrderFragment = new ConfirmOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ConfirmOrderFragment.ADDRESS,address);
                bundle.putString(ConfirmOrderFragment.PHONE, phone);
                bundle.putString(ConfirmOrderFragment.NAME, name);
                confirmOrderFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layoutContainer, confirmOrderFragment).commit();
            }
        });

    }

    private boolean checkValidatePhone(String phone) {
        String regex = "^(0|84|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return phone.matches(regex);
    }

    private boolean ValidateInput(){
        boolean valid = true;
        if (txtCustomerName.getText().toString().equals("")) {
            valid = false;
            txtCustomerName.setError("Vui lòng không để trống");
        }
        if ( txtCustomerPhone.getText().toString().equals("")){
            valid = false;
            txtCustomerPhone.setError("Vui lòng không để trống");
        } else if (!checkValidatePhone(txtCustomerPhone.getText().toString())) {
            valid = false;
            txtCustomerPhone.setError("Số điện thoại không hợp lệ");
        } else if (txtAddress.getText().toString().equals("")){
            valid = false;
            txtAddress.setHint("Vui lòng chọn địa chỉ");
            txtAddress.setError("Vui lòng Chọn địa chỉ");
        }
        return valid;
    }
}

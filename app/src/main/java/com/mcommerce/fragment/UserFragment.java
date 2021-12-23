package com.mcommerce.fragment;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.nhom8.auth.LoginActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.auth.PointHistoryActivity;
import com.mcommerce.nhom8.auth.UserInfoActivity;
import com.mcommerce.nhom8.setting.PolicyActivity;
import com.mcommerce.nhom8.setting.SettingsActivity;

public class UserFragment extends Fragment {

    private View view;
    private LinearLayout llSupport_fmuser,
                llSetting_fmuser,
                llChinhSach_fmuser,
                llPoint_fmuser,
                llUserInfo_fmuser;
    private TextView    txtUserName_fmuser,
                txtUserPoint_fmuser;

    private ImageView imv_fmuser;
    private Button btnLogout_fmuser;
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user,container,false);
        linkview();
        loadUserInfo();
        addEvent();
        return view;
    }

    private void linkview() {
        llSupport_fmuser = view.findViewById(R.id.llSupport_fmuser);
        llSetting_fmuser = view.findViewById(R.id.llSetting_fmuser);
        llChinhSach_fmuser = view.findViewById(R.id.llChinhSach_fmuser);
        llPoint_fmuser = view.findViewById(R.id.llPoint_fmuser);
        llUserInfo_fmuser = view.findViewById(R.id.llUserInfo_fmuser);

        txtUserName_fmuser = view.findViewById(R.id.txtUserName_fmuser);
        txtUserPoint_fmuser = view.findViewById(R.id.txtUserPoint_fmuser);

        imv_fmuser = view.findViewById(R.id.imv_fmuser);

        btnLogout_fmuser = view.findViewById(R.id.btnLogOut_fmuser);
    }

    private void loadUserInfo() {

        if (user == null) {
            btnLogout_fmuser.setText("Đăng nhập");
            return;
        }

        try {
            String name = user.getDisplayName();

            Uri uri = user.getPhotoUrl();
            txtUserName_fmuser.setText(name);
            Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).error(R.drawable.default_ava).into(imv_fmuser);
            reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("userPoint").getValue()==null){
                        txtUserPoint_fmuser.setText("0 điểm");
                    } else {
                        txtUserPoint_fmuser.setText(snapshot.child("userPoint").getValue()+" điểm");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(),"Có lỗi xảy ra, vui lòng thử lại sau",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getActivity(),"Có lỗi xảy ra, vui lòng tải lại ứng dụng hoặc đăng nhập lại",Toast.LENGTH_SHORT).show();
        }
    }

    private void addEvent() {

        llUserInfo_fmuser.setOnClickListener(goToContentActivity);
        llPoint_fmuser.setOnClickListener(goToContentActivity);
        llChinhSach_fmuser.setOnClickListener(goToContentActivity);
        llSetting_fmuser.setOnClickListener(goToContentActivity);
        llSupport_fmuser.setOnClickListener(goToContentActivity);

        if (user == null) {
            btnLogout_fmuser.setOnClickListener(signin);
        }
        else {
            btnLogout_fmuser.setOnClickListener(logout);
        }
    }

    View.OnClickListener logout = v -> {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    };

    View.OnClickListener signin = v -> {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    };

    View.OnClickListener goToContentActivity = v -> {
        if (v.getId() == R.id.llUserInfo_fmuser){
            startActivity(new Intent(getActivity(), UserInfoActivity.class));
        }
        if (v.getId() == R.id.llChinhSach_fmuser) {
            startActivity(new Intent(getActivity(), PolicyActivity.class));
        }
        if (v.getId() == R.id.llPoint_fmuser) {
            startActivity(new Intent(getActivity(), PointHistoryActivity.class));
        }
        if (v.getId() == R.id.llSetting_fmuser) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        }
        if (v.getId() == R.id.llSupport_fmuser) {
            Dialog commingsoon = new Dialog(getActivity());
            commingsoon.setContentView(R.layout.diaglog_comming_soon);
            commingsoon.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btnOk=commingsoon.findViewById(R.id.btnOK);
            btnOk.setOnClickListener(l -> commingsoon.dismiss());
            commingsoon.show();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadUserInfo();
    }

}

package com.mcommerce.nhom8.wishlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mcommerce.fragment.Wishlist_ProductFragment;
import com.mcommerce.fragment.Wishlist_RecipeFragment;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.auth.LoginActivity;
import com.mcommerce.nhom8.order.CartActivity;

public class WishList extends Fragment {

    View view;
    Button btnCongThuc_Wish, btnSanPham_Wish;
    ImageButton btnCart_WL;
    TextView txtDangNhap;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_wish_list,container,false);
        loadFragment(new Wishlist_ProductFragment());
        linkViews();
        addEvents();
        return view;
    }

    private void linkViews() {
        btnCongThuc_Wish =view.findViewById(R.id.btnCongThuc_Wish);
        btnSanPham_Wish =view.findViewById(R.id.btnSanPham_Wish);
        btnCart_WL=view.findViewById(R.id.btnCart_WL);
        btnSanPham_Wish.setEnabled(false);
        btnCongThuc_Wish.setEnabled(true);
        btnSanPham_Wish.setBackgroundResource(R.drawable.button_underline);
        txtDangNhap = view.findViewById(R.id.txtDangNhap);
    }

    private void addEvents() {
        if (user != null){
            btnCongThuc_Wish.setOnClickListener(myClick);
            btnSanPham_Wish.setOnClickListener(myClick);
            btnCart_WL.setOnClickListener(v -> startActivity(new Intent(getContext(), CartActivity.class)));
        }
        else {
            txtDangNhap.setVisibility(View.VISIBLE);
            txtDangNhap.setOnClickListener(v -> {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            });
        }

    }
    View.OnClickListener myClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.btnSanPham_Wish) {
                loadFragment(new Wishlist_ProductFragment());
                btnSanPham_Wish.setEnabled(false);
                btnCongThuc_Wish.setEnabled(true);
                btnSanPham_Wish.setBackgroundResource(R.drawable.button_underline);
                btnCongThuc_Wish.setBackgroundResource(R.color.white);
            }else if(view.getId()==R.id.btnCongThuc_Wish){
                loadFragment(new Wishlist_RecipeFragment());
                btnCongThuc_Wish.setEnabled(false);
                btnSanPham_Wish.setEnabled(true);
                btnCongThuc_Wish.setBackgroundResource(R.drawable.button_underline);
                btnSanPham_Wish.setBackgroundResource(R.color.white);
            }

        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutContainer_WishList, fragment);
        transaction.commit();
    }
}
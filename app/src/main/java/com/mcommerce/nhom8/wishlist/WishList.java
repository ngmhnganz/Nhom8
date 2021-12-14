package com.mcommerce.nhom8.wishlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mcommerce.fragment.ComingOrderFragment;
import com.mcommerce.fragment.HistoryOrderFragment;
import com.mcommerce.fragment.Wishlist_Product;
import com.mcommerce.fragment.Wishlist_Recipe;
import com.mcommerce.nhom8.R;

public class WishList extends Fragment {

    View view;
    Button btnCongThuc_Wish, btnSanPham_Wish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_wish_list,container,false);
        loadFragment(new Wishlist_Product());
        linkViews();
        addEvents();
        return view;
    }

    private void linkViews() {
        btnCongThuc_Wish =view.findViewById(R.id.btnCongThuc_Wish);
        btnSanPham_Wish =view.findViewById(R.id.btnSanPham_Wish);

        btnSanPham_Wish.setEnabled(false);
        btnCongThuc_Wish.setEnabled(true);
        btnSanPham_Wish.setBackgroundResource(R.drawable.button_underline);    }

    private void addEvents() {
        btnCongThuc_Wish.setOnClickListener(myClick);
        btnSanPham_Wish.setOnClickListener(myClick);
    }
    View.OnClickListener myClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.btnSanPham_Wish) {
                btnSanPham_Wish.setEnabled(false);
                btnCongThuc_Wish.setEnabled(true);
                btnSanPham_Wish.setBackgroundResource(R.drawable.button_underline);
                btnCongThuc_Wish.setBackgroundResource(R.color.white);
            }else if(view.getId()==R.id.btnCongThuc_Wish){
                loadFragment(new Wishlist_Recipe());
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
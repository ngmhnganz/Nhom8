package com.mcommerce.nhom8.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mcommerce.fragment.Wishlist_Product;
import com.mcommerce.fragment.Wishlist_Recipe;
import com.mcommerce.nhom8.R;

public class WishList extends AppCompatActivity {

    Button btnCongThuc_Wish, btnSanPham_Wish;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        linkViews();
        addEvents();
        fragmentManager=getSupportFragmentManager();
    }

    private void linkViews() {
        btnCongThuc_Wish =findViewById(R.id.btnCongThuc_Wish);
        btnSanPham_Wish =findViewById(R.id.btnSanPham_Wish);
    }

    private void addEvents() {
        btnCongThuc_Wish.setOnClickListener(myClick);
        btnSanPham_Wish.setOnClickListener(myClick);
    }
    View.OnClickListener myClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            Fragment fragment=null;
            if(view.getId()==R.id.btnCongThuc_Wish){
                fragment=new Wishlist_Recipe();
            }else if(view.getId()==R.id.btnSanPham_Wish){
                fragment=new Wishlist_Product();
                Bundle bundle=new Bundle();
                bundle.putString("say","Hello");
                fragment.setArguments(bundle);
            }
            if(fragment !=null){
                fragmentTransaction.replace(R.id.layoutContainer_WishList,fragment);
                fragmentTransaction.commit();
            }
        }
    };
}
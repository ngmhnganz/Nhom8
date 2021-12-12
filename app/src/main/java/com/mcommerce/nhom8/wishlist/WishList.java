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

    Button btnCongThuc_LikeList, btnSanPham_LikeList;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        linkViews();
//        addEvents();
        fragmentManager=getSupportFragmentManager();
    }

    private void linkViews() {
        btnCongThuc_LikeList=findViewById(R.id.btnCongThuc_LikeList);
        btnSanPham_LikeList=findViewById(R.id.btnSanPham_LikeList);
    }

//    private void addEvents() {
//        btnCongThuc_LikeList.setOnClickListener(myClick);
//        btnSanPham_LikeList.setOnClickListener(myClick);
//    }
//    View.OnClickListener myClick=new View.OnClickListener(){
//        @Override
//        public void onClick(View view) {
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            Fragment fragment=null;
//            if(view.getId()==R.id.btnCongThuc_LikeList){
//                fragment=new Wishlist_Recipe();
//            }else if(view.getId()==R.id.btnSanPham_LikeList){
//                fragment=new Wishlist_Product();
//            }
//            if(fragment !=null){
//                fragmentTransaction.replace(R.id.layoutContainer_LikeList,fragment);
//                fragmentTransaction.commit();
//            }
//        }
//    };
}
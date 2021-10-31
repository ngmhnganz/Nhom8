package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LikeList extends AppCompatActivity {
    Button btnCongThuc_LikeList, btnSanPham_LikeList;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottom_navigation_likelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_list);
        
        linkViews();
        addEvents();
        fragmentManager=getSupportFragmentManager();
//        manager=getSupportFragmentManager();
//        FragmentTransaction transaction=manager.beginTransaction();
//        transaction.replace(R.id.layoutContainer,new ProductFragment());
//        transaction.commit();
    }

    private void linkViews() {
        btnCongThuc_LikeList=findViewById(R.id.btnCongThuc_LikeList);
        btnSanPham_LikeList=findViewById(R.id.btnSanPham_LikeList);
        bottom_navigation_likelist=findViewById(R.id.bottom_navigation_likelist);
    }

    private void addEvents() {
        btnCongThuc_LikeList.setOnClickListener(myClick);
        btnSanPham_LikeList.setOnClickListener(myClick);
    }
    View.OnClickListener myClick=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            Fragment fragment=null;
            if(view.getId()==R.id.btnCongThuc_LikeList){
                fragment=new FragmentRecipeLikeList();
            }else if(view.getId()==R.id.btnSanPham_LikeList){
                fragment=new FragmentProductLikeList();
            }
            if(fragment !=null){
                fragmentTransaction.replace(R.id.layoutContainer_LikeList,fragment);
                fragmentTransaction.commit();
            }
        }
    };
}
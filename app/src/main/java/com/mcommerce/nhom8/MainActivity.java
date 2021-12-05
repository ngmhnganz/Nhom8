package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.mcommerce.fragment.HomeFragment;
import com.mcommerce.fragment.OrderFragment;
import com.mcommerce.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    public static String SELECTED_FRAGMENT = "Selected Fragment";
    public static final int HOME_FRAGMENT = 1;
    public static final int LIKE_FRAGMENT = 2;
    public static final int ORDER_FRAGMENT = 3;
    public static final int USER_FRAGMENT = 4;

    private BottomNavigationView bottomNavigation;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkview();
        initData();
        addEvent();
    }

    private void addEvent() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            fragment = null;
            switch (item.getItemId()){
                case (R.id.menu_order):
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;
                case (R.id.menu_home):
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case  (R.id.menu_person):
                    fragment = new UserFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        });

    }

    private void initData() {
        int intentFragment = 0;
        if (getIntent().getExtras() != null){
            intentFragment = getIntent().getExtras().getInt(SELECTED_FRAGMENT);
        }

        switch (intentFragment){
            case HOME_FRAGMENT:
                loadFragment(new HomeFragment());
                break;
            case LIKE_FRAGMENT:
//
                break;
            case ORDER_FRAGMENT:
                loadFragment(new OrderFragment());
                break;
            case USER_FRAGMENT:
                loadFragment(new UserFragment());
                break;
            case 0:
                loadFragment(new HomeFragment());
        }
    }

    private void linkview() {

        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_mainractivity, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigation.getSelectedItemId() == R.id.menu_home)
        {
            finish();
        }
        else
        {
            bottomNavigation.setSelectedItemId(R.id.menu_home);
        }
    }

    /* class sliderTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (vpgBannerMain.getCurrentItem() < bannerMainModalList.size()-1) {
                        vpgBannerMain.setCurrentItem(vpgBannerMain.getCurrentItem()+1);
                    } else {
                        vpgBannerMain.setCurrentItem(0);
                    }

                }
            });
        }
    }
    }
}

    */

}
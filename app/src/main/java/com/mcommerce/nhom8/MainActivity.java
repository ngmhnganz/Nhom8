package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mcommerce.adapter.BannerMainAdapter;
import com.mcommerce.adapter.GoiYComboAdapter;
import com.mcommerce.adapter.GoiYMonanAdapter;
import com.mcommerce.fragment.HistoryOrderFragment;
import com.mcommerce.fragment.HomeFragment;
import com.mcommerce.model.BannerMainModel;
import com.mcommerce.model.GoiYComboModel;
import com.mcommerce.model.GoiYMonanModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Menu menu;
    private MenuItem menuItem;

    private ViewPager vpgBannerMain;
    private List<BannerMainModel> bannerMainModelList;
    private List<GoiYMonanModel> goiYMonanModelList;
    private List<GoiYComboModel> goiYComboModelList;
    private ImageButton btnSanPham, btnCongThuc, btnGoiY;
    private RecyclerView rcvGoiYMonan, rcvGoiYCombo;
    private RealtimeBlurView blurView;
    private BannerMainAdapter bannerMainAdapter;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragment = null;


    private LinearLayout llSliderDot;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkview();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        initData();
        addEvent();

    }

    private void addEvent() {
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case (R.id.menu_order):
                        startActivity(new Intent(MainActivity.this,OrderActivity.class));
                        break;
                    case (R.id.menu_home):
                        break;
                }
                return false;
            }
        });

    }


    private void initData() {


    }

    private void linkview() {

        bottomNavigation = findViewById(R.id.bottom_navigation);
        menu = bottomNavigation.getMenu();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.container_mainractivity,fragment);
        fragmentTransaction.commit();


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
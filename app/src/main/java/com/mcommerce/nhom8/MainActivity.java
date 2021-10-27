package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mmin18.widget.RealtimeBlurView;
import com.mcommerce.adapter.BannerMainAdapter;
import com.mcommerce.adapter.GoiYComboAdapter;
import com.mcommerce.adapter.GoiYMonanAdapter;
import com.mcommerce.model.BannerMainModel;
import com.mcommerce.model.GoiYComboModel;
import com.mcommerce.model.GoiYMonanModel;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPager vpgBannerMain;
    List<BannerMainModel> bannerMainModelList;
    List<GoiYMonanModel> goiYMonanModelList;
    List<GoiYComboModel> goiYComboModelList;
    ImageButton btnSanPham, btnCongThuc, btnGoiY;
    RecyclerView rcvGoiYMonan, rcvGoiYCombo;
    RealtimeBlurView blurView;

    LinearLayout llSliderDot;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkview();
        initData();
        addEvent();

       /* Timer timer = new Timer();
        timer.scheduleAtFixedRate( new sliderTimer(), 5000, 4000);*/

    }

    private void addEvent() {
        //region hiệu ứng touch cho icon Sản phẩm, Công thức, Gợi Ý
        btnSanPham.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    btnSanPham.setImageResource(R.drawable.ic_sanpham_pressed_main);

                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP ) {
                    btnSanPham.setImageResource(R.drawable.ic_sanpham_main);
                }
                return false;
            }
        });

        btnCongThuc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    btnCongThuc.setImageResource(R.drawable.ic_congthuc_pressed_main);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP ) {
                    btnCongThuc.setImageResource(R.drawable.ic_congthuc_main);
                }
                return false;
            }
        });

        btnGoiY.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    btnGoiY.setImageResource(R.drawable.ic_goiy_pressed_main);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP ) {
                    btnGoiY.setImageResource(R.drawable.ic_goiy_main);

                }
                return false;
            }
        });
        //endregion

    }


    private void initData() {
        //region Banner
        bannerMainModelList = new ArrayList<>();
        bannerMainModelList.add(new BannerMainModel(R.drawable.mot,"1"));
        bannerMainModelList.add(new BannerMainModel(R.drawable.hai,"2"));
        bannerMainModelList.add(new BannerMainModel(R.drawable.ba,"3"));

        BannerMainAdapter bannerMainAdapter = new BannerMainAdapter(this, bannerMainModelList);
        vpgBannerMain.setAdapter(bannerMainAdapter);
        //endregion

        //region dot cho viewPage
        dotscount = bannerMainAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i<dotscount;i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            llSliderDot.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        vpgBannerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //endregion

        //region Gợi ý Món ăn

        goiYMonanModelList = new ArrayList<>();
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","20 phút"));
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","30 phút"));
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","nhiều phút"));

        GoiYMonanAdapter goiYMonanAdapter = new GoiYMonanAdapter(goiYMonanModelList,this);
        rcvGoiYMonan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvGoiYMonan.setAdapter(goiYMonanAdapter);

        //endregion

        //region Gơi ý Combo

        goiYComboModelList = new ArrayList<>();
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));

        GoiYComboAdapter goiYComboAdapter = new GoiYComboAdapter(goiYComboModelList,this);
        rcvGoiYCombo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rcvGoiYCombo.setAdapter(goiYComboAdapter);

        //endregion
    }

    private void linkview() {
        vpgBannerMain = findViewById(R.id.vpgBanner_main);
        llSliderDot = findViewById(R.id.llSliderDots_main);

        btnCongThuc = findViewById(R.id.btnCongThuc_main);
        btnSanPham = findViewById(R.id.btnSanPham_main);
        btnGoiY = findViewById(R.id.btnGoiY_main);

        rcvGoiYMonan = findViewById(R.id.rcvGoiYMonAn_main);
        rcvGoiYCombo = findViewById(R.id.rcvGoiYCombo_main);

        blurView = findViewById(R.id.blurview_LyGoiYMonan);
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
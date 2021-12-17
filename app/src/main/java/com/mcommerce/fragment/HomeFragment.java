package com.mcommerce.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.mmin18.widget.RealtimeBlurView;

import com.mcommerce.adapter.BannerMainAdapter;
import com.mcommerce.adapter.GoiYComboAdapter;
import com.mcommerce.adapter.GoiYMonanAdapter;
import com.mcommerce.model.BannerMainModel;
import com.mcommerce.model.GoiYComboModel;
import com.mcommerce.model.GoiYMonanModel;
import com.mcommerce.nhom8.order.CartActivity;
import com.mcommerce.nhom8.product.AllProductsActivity;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View view;
    private ViewPager vpgBannerMain;
    private List<BannerMainModel> bannerMainModelList;
    private List<GoiYMonanModel> goiYMonanModelList;
    private List<GoiYComboModel> goiYComboModelList;
    private ImageButton btnSanPham, btnCongThuc, btnGoiY;
    private RecyclerView rcvGoiYMonan, rcvGoiYCombo;
    private RealtimeBlurView blurView;
    private BannerMainAdapter bannerMainAdapter;
    private LinearLayout llSliderDot;
    private int dotscount;
    private ImageView[] dots;
    private ImageButton btnCard_main;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home,container,false);

        linkview();
        initData();
        addEvent();
        return view;
    }
    private void linkview() {
        vpgBannerMain = view.findViewById(R.id.vpgBanner_main);
        llSliderDot = view.findViewById(R.id.llSliderDots_main);

        btnCongThuc = view.findViewById(R.id.btnCongThuc_main);
        btnSanPham = view.findViewById(R.id.btnSanPham_main);
        btnGoiY = view.findViewById(R.id.btnGoiY_main);

        rcvGoiYMonan = view.findViewById(R.id.rcvGoiYMonAn_main);
        rcvGoiYCombo = view.findViewById(R.id.rcvGoiYCombo_main);

        blurView = view.findViewById(R.id.blurview_LyGoiYMonan);
        btnCard_main = view.findViewById(R.id.btnCard_main);

    }

    private void initData() {
        //region Banner
        bannerMainModelList = new ArrayList<>();
        bannerMainModelList.add(new BannerMainModel(R.drawable.mot,"1"));
        bannerMainModelList.add(new BannerMainModel(R.drawable.hai,"2"));
        bannerMainModelList.add(new BannerMainModel(R.drawable.ba,"3"));

        bannerMainAdapter = new BannerMainAdapter(getContext(), bannerMainModelList);
        vpgBannerMain.setAdapter(bannerMainAdapter);
        //endregion

        //region Gợi ý Món ăn

        goiYMonanModelList = new ArrayList<>();
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","20 phút"));
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","30 phút"));
        goiYMonanModelList.add(new GoiYMonanModel(R.drawable.suggest_monan, "Bánh bông lan trứng muối","Bánh bông lan chà mông chứng múi ăn với cà với cà với càv ới cà với cà với càv ới cà với cà với càv ới cà với cà ","nhiều phút"));

        GoiYMonanAdapter goiYMonanAdapter = new GoiYMonanAdapter(goiYMonanModelList, (Activity) getContext());
        rcvGoiYMonan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvGoiYMonan.setAdapter(goiYMonanAdapter);

        //endregion

        //region Gơi ý Combo

        goiYComboModelList = new ArrayList<>();
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));
        goiYComboModelList.add(new GoiYComboModel(90000,R.drawable.suggest_monan, "Bánh tráng","3 người"));

        GoiYComboAdapter goiYComboAdapter = new GoiYComboAdapter(goiYComboModelList, (Activity) getContext());
        rcvGoiYCombo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        rcvGoiYCombo.setAdapter(goiYComboAdapter);

        //endregion
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

        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getContext() ,
                        AllProductsActivity.class);
                startActivity(intentMain);
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

        btnCard_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CartActivity.class));
            }
        });

        //region dot cho viewPage
        dotscount = bannerMainAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i<dotscount;i++){
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            llSliderDot.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_dot));

        vpgBannerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //endregion

    }
}

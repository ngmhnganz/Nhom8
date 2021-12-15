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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.mmin18.widget.RealtimeBlurView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.BannerMainAdapter;
import com.mcommerce.adapter.GoiYMonanAdapter;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.adapter.RecipeAdapter;
import com.mcommerce.model.BannerMainModel;
import com.mcommerce.model.GoiYMonanModel;
import com.mcommerce.model.Product;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.SuggestRecipeActivity;
import com.mcommerce.nhom8.order.CartActivity;
import com.mcommerce.nhom8.product.AllProductsActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.product.ListProductActivity;
import com.mcommerce.nhom8.recipe.ListRecipeActivity;
import com.mcommerce.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View view;
    ViewPager vpgBannerMain;
    List<BannerMainModel> bannerMainModelList;
    List<Recipe> goiYRecipeList = new ArrayList<>();
    List<Product> goiYComboList = new ArrayList<>();
    TextView txtSeeMoreMonAn, txtSeeMoreCongThuc;

    ImageButton btnSanPham, btnCongThuc, btnGoiY;
    RecyclerView rcvGoiYMonan, rcvGoiYCombo;

    RealtimeBlurView blurView;
    BannerMainAdapter bannerMainAdapter;

    LinearLayout llSliderDot;
    int dotscount;
    ImageView[] dots;

    private ImageButton btnCard_main;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

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
        txtSeeMoreMonAn = view.findViewById(R.id.txtSeeMoreMonAn);
        txtSeeMoreCongThuc = view.findViewById(R.id.txtSeeMoreCongThuc);

        vpgBannerMain = view.findViewById(R.id.vpgBanner_main);
        llSliderDot = view.findViewById(R.id.llSliderDots_main);

        btnCongThuc = view.findViewById(R.id.btnCongThuc_main);
        btnSanPham = view.findViewById(R.id.btnSanPham_main);
        btnGoiY = view.findViewById(R.id.btnGoiY_main);

        rcvGoiYMonan = view.findViewById(R.id.rcvGoiYMonAn_main);
        rcvGoiYMonan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        rcvGoiYCombo = view.findViewById(R.id.rcvGoiYCombo_main);
        rcvGoiYCombo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

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

        Query queryCongThuc = ref.child("CongThuc").limitToLast(1);
        queryCongThuc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Recipe p = dataSnapshot.getValue(Recipe.class);
                    goiYRecipeList.add(p);
                }
                RecipeAdapter adapter = new RecipeAdapter((MainActivity) getContext(), RecipeAdapter.SUGGEST,goiYRecipeList, null);
                rcvGoiYMonan.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //endregion

        //region Gơi ý Combo
        Query queryCombo = ref.child("NguyenLieu").orderByChild("productType").equalTo(Product.COMBO).limitToLast(4);
        queryCombo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product p = dataSnapshot.getValue(Product.class);
                    goiYComboList.add(p);
                }
                ProductAdapter adapter = new ProductAdapter();
                adapter.setData((MainActivity) getContext(),goiYComboList,ProductAdapter.CATEGORY);
                rcvGoiYCombo.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //endregion
    }

    private void addEvent() {

        txtSeeMoreMonAn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext() ,
                    ListProductActivity.class);
            intent.putExtra(Constant.PRODUCT_LIST_TYPE, Product.COMBO);
            startActivity(intent);
        });
        txtSeeMoreCongThuc.setOnClickListener(v -> {
            Intent intent = new Intent(getContext() ,
                    ListRecipeActivity.class);
            startActivity(intent);
        });

        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getContext() ,
                        AllProductsActivity.class);
                startActivity(intentMain);
            }
        });

        btnCongThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getContext() ,
                        ListRecipeActivity.class);
                startActivity(intentMain);
            }
        });

        btnGoiY.setOnClickListener(v -> {
            Intent intentMain = new Intent(getContext() ,
                    SuggestRecipeActivity.class);
            startActivity(intentMain);
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

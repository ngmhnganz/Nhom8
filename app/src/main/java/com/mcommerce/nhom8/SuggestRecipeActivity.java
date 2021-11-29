package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mcommerce.adapter.RecipeMaterialAdapter;
import com.mcommerce.adapter.RecipeMaterialAdapterRCV;
import com.mcommerce.model.Product;
import com.mcommerce.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class SuggestRecipeActivity extends AppCompatActivity {

    RecyclerView rcvBot_RecipeMaterial,rcvSuaKem_RecipeMaterial,rcvBo_RecipeMaterial;
    ImageView imvDropDownBot_SuggestRecipe, imvDropDownSuaKem_SuggestRecipe, imvDropDownBo_SuggestRecipe;
    RecipeMaterialAdapterRCV adapterBot, adapterSuaKem, adapterBo;
    ArrayList<Product> materialsBot, materialsSuaKem, materialsBo;
    LinearLayout llBot_RecipeMaterial, llSua_RecipeMaterial, llBo_RecipeMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_recipe);
        linkViews();
        configRCV();
        addEvents();
        initData();
        initAdapter();
    }

    private void linkViews() {
        rcvBot_RecipeMaterial = findViewById(R.id.rcvBot_RecipeMaterial);
        rcvSuaKem_RecipeMaterial=findViewById(R.id.rcvSuaKem_RecipeMaterial);
        rcvBo_RecipeMaterial=findViewById(R.id.rcvBo_RecipeMaterial);


        llBot_RecipeMaterial = findViewById(R.id.llBot_RecipeMaterial);
        llSua_RecipeMaterial = findViewById(R.id.llSuaKem_RecipeMaterial);
        llBo_RecipeMaterial = findViewById(R.id.llBo_RecipeMaterial);

        imvDropDownBot_SuggestRecipe = findViewById(R.id.imvDropDownBot_SuggestRecipe);
        imvDropDownSuaKem_SuggestRecipe = findViewById(R.id.imvDropDownSuaKem_SuggestRecipe);
        imvDropDownBo_SuggestRecipe = findViewById(R.id.imvDropDownBo_SuggestRecipe);
    }

    private void configRCV() {
        configRecyclerView(rcvBot_RecipeMaterial);
        configRecyclerView(rcvSuaKem_RecipeMaterial);
        configRecyclerView(rcvBo_RecipeMaterial);
    }

    public void configRecyclerView(RecyclerView rcv) {

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        //layoutManager.setJustifyContent(JustifyContent.CENTER);
        rcv.setLayoutManager(layoutManager);

        SpacingItemDecorator itemDecorator=new SpacingItemDecorator(25);
        rcv.addItemDecoration(itemDecorator);
    }

    private void addEvents() {
        imvDropDownBot_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llBot_RecipeMaterial.getVisibility() == view.VISIBLE) {
                    llBot_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                } else {
                    llBot_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });

        imvDropDownSuaKem_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSua_RecipeMaterial.getVisibility() == view.VISIBLE) {
                    llSua_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                } else {
                    llSua_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });

        imvDropDownBo_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llBo_RecipeMaterial.getVisibility() == view.VISIBLE) {
                    llBo_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                } else {
                    llBo_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });
    }

    private void initData() {
        materialsBot = new ArrayList<Product>();
        materialsBot.add(new Product("Bột mì"));
        materialsBot.add(new Product("Bột năng dẻo"));
        materialsBot.add(new Product("Bột Cacao thơm"));
        materialsBot.add(new Product("Bột nếp mlem"));
        materialsBot.add(new Product("Bột matcha ngon quá trời quá đất"));

        materialsSuaKem = new ArrayList<Product>();
        materialsSuaKem.add(new Product("Sữa tươi"));
        materialsSuaKem.add(new Product("Sữa đặc"));
        materialsSuaKem.add(new Product("Sữa béo"));
        materialsSuaKem.add(new Product("Sữa hạt"));
        materialsSuaKem.add(new Product("Sữa chua"));
        materialsSuaKem.add(new Product("Whipping"));
        materialsSuaKem.add(new Product("Topping"));

        materialsBo = new ArrayList<Product>();
        materialsBo.add(new Product("Bơ lạt"));
        materialsBo.add(new Product("Bơ mặn"));
        materialsBo.add(new Product("Bơ thực vật"));
    }

    private void initAdapter() {
        adapterBot=new RecipeMaterialAdapterRCV(SuggestRecipeActivity.this,materialsBot);
        rcvBot_RecipeMaterial.setAdapter(adapterBot);

        adapterSuaKem=new RecipeMaterialAdapterRCV(SuggestRecipeActivity.this,materialsSuaKem);
        rcvSuaKem_RecipeMaterial.setAdapter(adapterSuaKem);

        adapterBo=new RecipeMaterialAdapterRCV(SuggestRecipeActivity.this,materialsBo);
        rcvBo_RecipeMaterial.setAdapter(adapterBo);

    }

}
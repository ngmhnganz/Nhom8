package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mcommerce.adapter.RecipeMaterialAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class SuggestRecipeActivity extends AppCompatActivity {
    GridView grvBot_RecipeMaterial,grvSua_RecipeMaterial,grvBo_RecipeMaterial;
    ImageView imvDropDownBot_SuggestRecipe,imvDropDownSuaKem_SuggestRecipe,imvDropDownBo_SuggestRecipe;
    RecipeMaterialAdapter adapterBot,adapterSuaKem,adapterBo;
    ArrayList<Product> materialsBot,materialsSuaKem,materialsBo;
    LinearLayout llBot_RecipeMaterial,llSua_RecipeMaterial,llBo_RecipeMaterial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_recipe);
        linkViews();
        addEvents();
        initData();
        initAdapter();
    }

    private void linkViews() {
        grvBot_RecipeMaterial=findViewById(R.id.grvBot_RecipeMaterial);
        grvSua_RecipeMaterial=findViewById(R.id.grvSuaKem_RecipeMaterial);
        grvBo_RecipeMaterial=findViewById(R.id.grvBo_RecipeMaterial);

        llBot_RecipeMaterial=findViewById(R.id.llBot_RecipeMaterial);
        llSua_RecipeMaterial=findViewById(R.id.llSuaKem_RecipeMaterial);
        llBo_RecipeMaterial=findViewById(R.id.llBo_RecipeMaterial);

        imvDropDownBot_SuggestRecipe=findViewById(R.id.imvDropDownBot_SuggestRecipe);
        imvDropDownSuaKem_SuggestRecipe=findViewById(R.id.imvDropDownSuaKem_SuggestRecipe);
        imvDropDownBo_SuggestRecipe=findViewById(R.id.imvDropDownBo_SuggestRecipe);
    }

    private void addEvents() {
        imvDropDownBot_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llBot_RecipeMaterial.getVisibility()==view.VISIBLE){
                    llBot_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                }else {
                    llBot_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });

        imvDropDownSuaKem_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llSua_RecipeMaterial.getVisibility()==view.VISIBLE){
                    llSua_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                }else {
                    llSua_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });

        imvDropDownBo_SuggestRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llBo_RecipeMaterial.getVisibility()==view.VISIBLE){
                    llBo_RecipeMaterial.setVisibility(View.GONE);
                    imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
                }else {
                    llBo_RecipeMaterial.setVisibility(View.VISIBLE);
                    imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
                }
            }
        });
    }

    private void initData() {
        materialsBot=new ArrayList<Product>();
        materialsBot.add(new Product("Bột mì",R.drawable.background_listproduct));
        materialsBot.add(new Product("Bột bắp",R.drawable.background_listproduct));
        materialsBot.add(new Product("Bột năng",R.drawable.background_listproduct));
        materialsBot.add(new Product("Bột cacao",R.drawable.background_listproduct));
        materialsBot.add(new Product("Bột nếp",R.drawable.background_listproduct));
        materialsBot.add(new Product("Bột matcha",R.drawable.background_listproduct));

        materialsSuaKem=new ArrayList<Product>();
        materialsSuaKem.add(new Product("Sữa tươi",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Sữa đặc",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Sữa béo",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Sữa hạt",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Sữa chua",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Whipping",R.drawable.background_listproduct));
        materialsSuaKem.add(new Product("Topping",R.drawable.background_listproduct));

        materialsBo=new ArrayList<Product>();
        materialsBo.add(new Product("Bơ lạt",R.drawable.background_listproduct));
        materialsBo.add(new Product("Bơ mặn",R.drawable.background_listproduct));
        materialsBo.add(new Product("Bơ thực vật",R.drawable.background_listproduct));
    }

    private void initAdapter() {
        adapterBot=new RecipeMaterialAdapter(SuggestRecipeActivity.this,R.layout.item_recipe_material_layout,materialsBot);
        grvBot_RecipeMaterial.setAdapter(adapterBot);

        adapterSuaKem=new RecipeMaterialAdapter(SuggestRecipeActivity.this,R.layout.item_recipe_material_layout,materialsSuaKem);
        grvSua_RecipeMaterial.setAdapter(adapterSuaKem);

        adapterBo=new RecipeMaterialAdapter(SuggestRecipeActivity.this,R.layout.item_recipe_material_layout,materialsBo);
        grvBo_RecipeMaterial.setAdapter(adapterBo);
    }
}
package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.mcommerce.adapter.ListProductAdapter;
import com.mcommerce.adapter.RecipeMaterialAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    //tái sử dụng listproductadapter
    GridView grvRecipeMaterial;
    RecipeMaterialAdapter adapterRecipeMaterial;
    ArrayList<Product> materials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        linkViews();
        initData();
        initAdapter();
    }

    private void linkViews() {
        grvRecipeMaterial=findViewById(R.id.grvRecipeMaterial);
    }

    private void initData() {
        materials=new ArrayList<Product>();
        materials.add(new Product("Bột mì",R.drawable.background_listproduct));
        materials.add(new Product("Trứng gà",R.drawable.background_listproduct));
        materials.add(new Product("Sữa",R.drawable.background_listproduct));
        materials.add(new Product("Men nở",R.drawable.background_listproduct));
        materials.add(new Product("Đường",R.drawable.background_listproduct));
    }

    private void initAdapter() {
        adapterRecipeMaterial=new RecipeMaterialAdapter(RecipeActivity.this,R.layout.item_recipe_material_layout,materials);
        grvRecipeMaterial.setAdapter(adapterRecipeMaterial);
    }
}
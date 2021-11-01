package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcommerce.adapter.ListProductAdapter;
import com.mcommerce.adapter.RecipeMaterialAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    GridView grvRecipeMaterial;
    RecipeMaterialAdapter adapterRecipeMaterial;
    ArrayList<Product> materials;
    ImageView imvDropDownMaterial;
    TextView txtPreparedMaterials_Recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        linkViews();
        addEvents();
        initData();
        initAdapter();
    }

    private void linkViews() {
        grvRecipeMaterial=findViewById(R.id.grvRecipeMaterial);
        grvRecipeMaterial.setVisibility(View.GONE);
        imvDropDownMaterial=findViewById(R.id.imvDropDownMaterial);
        txtPreparedMaterials_Recipe=findViewById(R.id.txtPreparedMaterials_Recipe);
    }

    private void addEvents() {
        imvDropDownMaterial.setOnClickListener(clickSetVisibility);
        txtPreparedMaterials_Recipe.setOnClickListener(clickSetVisibility);
    }

    View.OnClickListener clickSetVisibility=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(grvRecipeMaterial.getVisibility()==view.VISIBLE){
                grvRecipeMaterial.setVisibility(View.GONE);
                imvDropDownMaterial.setImageResource(R.drawable.ic_arrow_down_24);
            }else {
                grvRecipeMaterial.setVisibility(View.VISIBLE);
                imvDropDownMaterial.setImageResource(R.drawable.ic__arrow_up_24);
            }
        }
    };

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
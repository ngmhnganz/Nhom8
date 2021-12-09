package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.mcommerce.adapter.RecipeMaterialAdapterRCV;
import com.mcommerce.model.Product;
import com.mcommerce.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class EachRecipeActivity extends AppCompatActivity {

    RecyclerView rcvRecipeMaterial;
    RecipeMaterialAdapterRCV adapterRecipeMaterial;
    ArrayList<Product> materials;
    ImageView imvDropDownMaterial;
    TextView txtPreparedMaterials_Recipe,txtRecipe_Info_recipe;
    LinearLayout llMaterialBuying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);
        linkViews();
        configRCV();
        addEvents();
        initData();
        initAdapter();
        //setGridViewHeightBasedOnChildren( grvRecipeMaterial,3);
    }

    private void linkViews() {
        rcvRecipeMaterial=findViewById(R.id.rcvRecipeMaterial_EachRecipe);

        llMaterialBuying=findViewById(R.id.llMaterialBuying);
        llMaterialBuying.setVisibility(View.GONE);
        imvDropDownMaterial=findViewById(R.id.imvDropDownMaterial);
        txtPreparedMaterials_Recipe=findViewById(R.id.txtPreparedMaterials_Recipe);
        txtRecipe_Info_recipe=findViewById(R.id.txtRecipe_Info_recipe);
    }

    private void configRCV() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        rcvRecipeMaterial.setLayoutManager(layoutManager);

        SpacingItemDecorator itemDecorator=new SpacingItemDecorator(25);
        rcvRecipeMaterial.addItemDecoration(itemDecorator);

    }


    private void addEvents() {
        imvDropDownMaterial.setOnClickListener(clickSetVisibility);
        txtPreparedMaterials_Recipe.setOnClickListener(clickSetVisibility);

//        //khi click vào item trên rcv view => đồng thời đổi màu + lưu list sp người dùng chọn
//        rcvRecipeMaterial.setOnItemClickListener
    }

     View.OnClickListener clickSetVisibility=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(llMaterialBuying.getVisibility()==view.VISIBLE){
                llMaterialBuying.setVisibility(View.GONE);
                imvDropDownMaterial.setImageResource(R.drawable.ic_arrow_down_24);
            }else {
                llMaterialBuying.setVisibility(View.VISIBLE);
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
        adapterRecipeMaterial=new RecipeMaterialAdapterRCV(EachRecipeActivity.this,materials);
        rcvRecipeMaterial.setAdapter(adapterRecipeMaterial);
    }
}
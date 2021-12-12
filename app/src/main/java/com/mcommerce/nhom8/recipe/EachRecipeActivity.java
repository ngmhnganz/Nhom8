package com.mcommerce.nhom8.recipe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcommerce.adapter.RecipeMaterialAdapterRCV;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;
import com.mcommerce.custom.SpacingItemDecorator;

import java.util.HashMap;

public class EachRecipeActivity extends AppCompatActivity {

    RecyclerView rcvRecipeMaterial;
    RecipeMaterialAdapterRCV adapterRecipeMaterial;
    HashMap<String, HashMap<String,?>> materials;
    ImageView imvDropDownMaterial;
    TextView txtPreparedMaterials_Recipe,txtRecipe_Info_recipe, txtRecipeName_recipe;
    LinearLayout llMaterialBuying;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Recipe recipe = new Recipe();
    Chip chip;
    ChipGroup chipGroup;
    ChipDrawable chipDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);
        linkViews();
        getData();
        initAdapter();
        addEvents();
        //setGridViewHeightBasedOnChildren( grvRecipeMaterial,3);
    }


    private void linkViews() {
        chipGroup=findViewById(R.id.chip_group);
//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
//        layoutManager.setFlexWrap(FlexWrap.WRAP);
//        layoutManager.setJustifyContent(JustifyContent.CENTER);
//        rcvRecipeMaterial.setLayoutManager(layoutManager);

//        SpacingItemDecorator itemDecorator=new SpacingItemDecorator(25);
//        rcvRecipeMaterial.addItemDecoration(itemDecorator);

        llMaterialBuying=findViewById(R.id.llMaterialBuying);
        llMaterialBuying.setVisibility(View.GONE);
        imvDropDownMaterial=findViewById(R.id.imvDropDownMaterial);
        txtPreparedMaterials_Recipe=findViewById(R.id.txtPreparedMaterials_Recipe);
        txtRecipe_Info_recipe=findViewById(R.id.txtRecipe_Info_recipe);
        txtRecipeName_recipe = findViewById(R.id.txtRecipeName_recipe);
    }
    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.RECIPE_BUNDLE);
        recipe = bundle.getParcelable(Constant.SECLECTED_RECIPE);
        txtRecipeName_recipe.setText(recipe.getRecipeName());
        HashMap<String,HashMap<String,?>> recipeIngredient = (HashMap<String, HashMap<String, ?>>) bundle.getSerializable(Constant.ITEMS_INGREDIENT);
        for (HashMap<String,?>  ingredient: recipeIngredient.values()) {
            chip = new Chip(this);
            chip.setText(ingredient.get("name").toString());
            chipDrawable= ChipDrawable.createFromAttributes(this,
                    null,
                    0,
                    R.style.custom_chip);
            if (ingredient.get("name").equals("Bột mì")){
                chip.setEnabled(false);
            }
            chip.setCheckedIconTint(ContextCompat.getColorStateList(this,R.color.white));
            chip.setTextColor(ContextCompat.getColorStateList(this, R.color.chip_text_selector));
            chip.setChipDrawable(chipDrawable);
            chip.setEnsureMinTouchTargetSize(false);
            chipGroup.addView(chip);
        }
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

    private void initAdapter() {

    }
}
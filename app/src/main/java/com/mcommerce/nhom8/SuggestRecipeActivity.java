package com.mcommerce.nhom8;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.Ingredient;
import com.mcommerce.nhom8.recipe.ListRecipeActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class SuggestRecipeActivity extends AppCompatActivity {

    ChipGroup   chipBots,
                chipBos,
                chipKhacs,
                chipSuaKems;
    ImageView imvDropDownBot_SuggestRecipe,
            imvDropDownSuaKem_SuggestRecipe,
            imvDropDownBo_SuggestRecipe,
            imvDropDownKhac_SuggestRecipe;
    Button btnSearch;

    HashMap<String, HashMap<String,?>> bots, suakems,bos,khacs;

    List<Integer> filter;

    LinearLayout llBot_RecipeMaterial,
            llSua_RecipeMaterial,
            llBo_RecipeMaterial,
            llKhac_RecipeMaterial;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_recipe);
        linkViews();
        initData();
        addEvents();
    }

    private void linkViews() {
        chipBots = findViewById(R.id.chipBots);
        chipBos = findViewById(R.id.chipBos);
        chipKhacs = findViewById(R.id.chipKhacs);
        chipSuaKems = findViewById(R.id.chipSuaKems);


        llBot_RecipeMaterial = findViewById(R.id.llBot_RecipeMaterial);
        llSua_RecipeMaterial = findViewById(R.id.llSuaKem_RecipeMaterial);
        llBo_RecipeMaterial = findViewById(R.id.llBo_RecipeMaterial);
        llKhac_RecipeMaterial = findViewById(R.id.llKhac_RecipeMaterial);

        imvDropDownBot_SuggestRecipe = findViewById(R.id.imvDropDownBot_SuggestRecipe);
        imvDropDownSuaKem_SuggestRecipe = findViewById(R.id.imvDropDownSuaKem_SuggestRecipe);
        imvDropDownBo_SuggestRecipe = findViewById(R.id.imvDropDownBo_SuggestRecipe);
        imvDropDownKhac_SuggestRecipe = findViewById(R.id.imvDropDownKhac_SuggestRecipe);

        btnSearch = findViewById(R.id.btnSearch);
    }

    private void addEvents() {
        imvDropDownBot_SuggestRecipe.setOnClickListener(view -> {
            if (llBot_RecipeMaterial.getVisibility() == View.VISIBLE) {
                llBot_RecipeMaterial.setVisibility(View.GONE);
                imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
            } else {
                llBot_RecipeMaterial.setVisibility(View.VISIBLE);
                imvDropDownBot_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
            }
        });

        imvDropDownSuaKem_SuggestRecipe.setOnClickListener(view -> {
            if (llSua_RecipeMaterial.getVisibility() == View.VISIBLE) {
                llSua_RecipeMaterial.setVisibility(View.GONE);
                imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
            } else {
                llSua_RecipeMaterial.setVisibility(View.VISIBLE);
                imvDropDownSuaKem_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
            }
        });

        imvDropDownBo_SuggestRecipe.setOnClickListener(view -> {
            if (llBo_RecipeMaterial.getVisibility() == View.VISIBLE) {
                llBo_RecipeMaterial.setVisibility(View.GONE);
                imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
            } else {
                llBo_RecipeMaterial.setVisibility(View.VISIBLE);
                imvDropDownBo_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
            }
        });

        imvDropDownKhac_SuggestRecipe.setOnClickListener(view -> {
            if (llKhac_RecipeMaterial.getVisibility() == View.VISIBLE) {
                llKhac_RecipeMaterial.setVisibility(View.GONE);
                imvDropDownKhac_SuggestRecipe.setImageResource(R.drawable.ic_arrow_down_24);
            } else {
                llKhac_RecipeMaterial.setVisibility(View.VISIBLE);
                imvDropDownKhac_SuggestRecipe.setImageResource(R.drawable.ic__arrow_up_24);
            }
        });

        btnSearch.setOnClickListener(v -> {
            filter = new ArrayList<>();
            filter.addAll(chipBos.getCheckedChipIds());
            filter.addAll(chipBots.getCheckedChipIds());
            filter.addAll(chipKhacs.getCheckedChipIds());
            filter.addAll(chipSuaKems.getCheckedChipIds());
            Intent intent = new Intent(SuggestRecipeActivity.this, ListRecipeActivity.class);
            intent.putIntegerArrayListExtra(Constant.FILTER_OPTION, (ArrayList<Integer>) filter);
            startActivity(intent);
        });
    }

    private void initData() {
        bots = new HashMap<>();
        bos = new HashMap<>();
        suakems = new HashMap<>();
        khacs = new HashMap<>();
        ref.child(Key.INGREDIENT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Ingredient ingredient;
                String label;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ingredient = dataSnapshot.getValue(Ingredient.class);
                    label = ingredient.getLabel();
                    if (label.equals(Ingredient.LABEL_BOT))
                    {
                        bots.put(dataSnapshot.getKey(), (HashMap<String, ?>) dataSnapshot.getValue());
                    }
                    if (label.equals(Ingredient.LABEL_SUAKEM))
                    {
                        suakems.put(dataSnapshot.getKey(), (HashMap<String, ?>) dataSnapshot.getValue());
                    }
                    if (label.equals(Ingredient.LABEL_BO))
                    {
                        bos.put(dataSnapshot.getKey(), (HashMap<String, ?>) dataSnapshot.getValue());
                    }
                    if (label.equals(Ingredient.LABEL_KHAC))
                    {
                        khacs.put(dataSnapshot.getKey(), (HashMap<String, ?>) dataSnapshot.getValue());
                    }
                }
                initAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initAdapter() {
        initAdapterfor(chipBots,bots);
        initAdapterfor(chipSuaKems,suakems);
        initAdapterfor(chipBos,bos);
        initAdapterfor(chipKhacs,khacs);
    }

    private void initAdapterfor(ChipGroup chipGroup, HashMap<String, HashMap<String,?>> materials) {
        Chip chip;
        ChipDrawable chipDrawable;
        for (HashMap<String,?>  ingredient: materials.values()) {
            chip = new Chip(this);
            chip.setId(  ( (Long)ingredient.get("id") ).intValue() );
            chip.setText(ingredient.get("name").toString());
//            if (ingredient.get("name").equals("Bột mì")){
//                chip.setEnabled(false);
//            }
            chipDrawable = ChipDrawable.createFromAttributes(this,
                    null,
                    0,
                    R.style.custom_chip);
            chip.setCheckedIconTint(ContextCompat.getColorStateList(this,R.color.white));
            chip.setTextColor(ContextCompat.getColorStateList(this, R.color.chip_text_selector));
            chip.setChipDrawable(chipDrawable);
            chip.setEnsureMinTouchTargetSize(false);
            chipGroup.addView(chip);
        }
    }

}
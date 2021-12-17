package com.mcommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.Ingredient;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Key;

import java.util.ArrayList;
import java.util.List;

public class SuggestRecipeWithFilter extends Fragment {
    ChipGroup chipBots,
            chipBos,
            chipKhacs,
            chipSuaKems;
    ImageView imvDropDownBot_SuggestRecipe,
            imvDropDownSuaKem_SuggestRecipe,
            imvDropDownBo_SuggestRecipe,
            imvDropDownKhac_SuggestRecipe;

    LinearLayout llBot_RecipeMaterial,
            llSua_RecipeMaterial,
            llBo_RecipeMaterial,
            llKhac_RecipeMaterial;

    List<Ingredient> bots, suakems, bos, khacs, results, total;
    List<Integer> filter = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_suggest_recipe_with_filter, container, false);
        linkview();
        initData();
        addEvent();
        return view;
    }

    private void linkview() {
        chipBots = view.findViewById(R.id.chipBots);
        chipBos = view.findViewById(R.id.chipBos);
        chipKhacs = view.findViewById(R.id.chipKhacs);
        chipSuaKems = view.findViewById(R.id.chipSuaKems);

        llBot_RecipeMaterial = view.findViewById(R.id.llBot_RecipeMaterial);
        llSua_RecipeMaterial = view.findViewById(R.id.llSuaKem_RecipeMaterial);
        llBo_RecipeMaterial = view.findViewById(R.id.llBo_RecipeMaterial);
        llKhac_RecipeMaterial = view.findViewById(R.id.llKhac_RecipeMaterial);

        imvDropDownBot_SuggestRecipe = view.findViewById(R.id.imvDropDownBot_SuggestRecipe);
        imvDropDownSuaKem_SuggestRecipe = view.findViewById(R.id.imvDropDownSuaKem_SuggestRecipe);
        imvDropDownBo_SuggestRecipe = view.findViewById(R.id.imvDropDownBo_SuggestRecipe);
        imvDropDownKhac_SuggestRecipe = view.findViewById(R.id.imvDropDownKhac_SuggestRecipe);

    }

    private void initData() {
        bots = new ArrayList<>();
        bos = new ArrayList<>();
        suakems = new ArrayList<>();
        khacs = new ArrayList<>();
        total = new ArrayList<>();
        ref.child(Key.INGREDIENT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Ingredient ingredient;
                String label;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ingredient = dataSnapshot.getValue(Ingredient.class);
                    label = ingredient.getLabel();
                    if (label.equals(Ingredient.LABEL_BOT)) bots.add(ingredient);
                    if (label.equals(Ingredient.LABEL_SUAKEM)) suakems.add(ingredient);
                    if (label.equals(Ingredient.LABEL_BO)) bos.add(ingredient);
                    if (label.equals(Ingredient.LABEL_KHAC)) khacs.add(ingredient);
                    total.add(ingredient);
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

    private void initAdapterfor(ChipGroup chipGroup,List<Ingredient> materials) {
        Chip chip;
        ChipDrawable chipDrawable;
        for (Ingredient  ingredient: materials) {
            chip = new Chip(getContext());
            chip.setId((int) ingredient.getId());
            chip.setText(ingredient.getName());
            chipDrawable = ChipDrawable.createFromAttributes(getContext(),
                    null,
                    0,
                    R.style.unavailable_chip);
            chip.setCheckedIconTint(ContextCompat.getColorStateList(getContext(),R.color.white));
            chip.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.unavailable_chip_text_selector));
            chip.setChipDrawable(chipDrawable);
            chip.setEnsureMinTouchTargetSize(false);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        filter.add((int) ingredient.getId());
                    } else {
                        filter.remove((Integer)(int) ingredient.getId());
                    }
                    System.out.println("đây nè suggest"+ filter.toString());
                }
            });
            chipGroup.addView(chip);
        }
    }

    private void addEvent() {
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
    }
}

package com.mcommerce.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.mcommerce.nhom8.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeMaterialAdapterRCV extends RecyclerView.Adapter<RecipeMaterialAdapterRCV.ViewHolder>{

    private Context context;
    private HashMap<String, HashMap<String,?>> ingredients;
    private Chip chip;
    private final ArrayList<String> ingredientKey;
    private String id;
    private ChipDrawable chipDrawable;

    public RecipeMaterialAdapterRCV(Context context, HashMap<String, HashMap<String,?>> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
        ingredientKey = new ArrayList<>(ingredients.keySet());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //nạp giao diện thông qua layout inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_recipe_material_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        id = ingredientKey.get(position);
        //biding dữ liệu
//      selector cho checkbox đổi thành checkbox
//
//        //flexGrow works like the weight attribute in horizontal LinearLayouts
//        ViewGroup.LayoutParams layoutParams=holder.txtRecipeMaterialName.getLayoutParams();
//        if(layoutParams instanceof FlexboxLayoutManager.LayoutParams){
//            FlexboxLayoutManager.LayoutParams flexboxLp= (FlexboxLayoutManager.LayoutParams) holder.txtRecipeMaterialName.getLayoutParams();
//            flexboxLp.setFlexGrow(1.0f);

        chip = new Chip(context);
        chip.setText(ingredients.get(id).get("name").toString());
        chipDrawable= ChipDrawable.createFromAttributes(context,
                null,
                0,
                R.style.custom_chip);
        chip.setCheckedIconTint(ContextCompat.getColorStateList(context,R.color.white));
        chip.setTextColor(ContextCompat.getColorStateList(context, R.color.chip_text_selector));
        chip.setChipDrawable(chipDrawable);
        chip.setEnsureMinTouchTargetSize(false);
        holder.chipGroup.addView(chip);

//     }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ChipGroup chipGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chipGroup = itemView.findViewById(R.id.chip_group);
        }
    }

}

package com.mcommerce.adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mcommerce.interfaces.RecyclerViewItemClickListener;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.recipe.EachRecipeActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public static final int RECIPE_ITEM = 0;
    public static final int SUGGEST = 1;
    public static final int WISH=2;

    Context context;
    int type;
    List<Recipe> recipeList;
    List<Integer> filter;
    public RecipeAdapter(Context context, int type, List<Recipe> recipeList, List<Integer> filter) {
        this.context = context;
        this.type = type;
        this.recipeList = recipeList;
        this.filter = filter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (type){
            case RECIPE_ITEM:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_listrecipe_layout,parent,false);
                break;
            }
            case SUGGEST:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggest_recipe,parent,false);
                break;
            }
            case WISH:{
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishrecipe,parent,false);
            }
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        if (recipe == null) {
            return;
        }
        switch (type){
            case RECIPE_ITEM:{
                holder.txtRecipeLike.setText(recipe.getRecipeLike()+" người thích");
                holder.txtRecipeName.setText(recipe.getRecipeName());
                Glide.with(context).load(recipe.getRecipeImage()).into(holder.imvRecipeThumb);
                break;
            }
            case SUGGEST:{
                holder.txtTenMonan_LyGoiYMonan.setText(recipe.getRecipeName());
                holder.txtDesMonan_LyGoiYMonan.setText(recipe.getRecipeDescription());
                holder.txtTimeMonan_LyGoiYMonan.setText(recipe.getRecipeTime()+" phút");
                Glide.with(context).load(recipe.getRecipeImage()).into(holder.imv_LyGoiMonan);
                break;
            }
            case WISH:{

            }
        }
        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, EachRecipeActivity.class);
                Log.d("Onclick","ăn sự kiện");
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.SECLECTED_RECIPE, recipe);
                bundle.putSerializable(Constant.ITEMS_INGREDIENT, (Serializable) recipe.getRecipeIngredient());
                bundle.putIntegerArrayList(Constant.FILTER_OPTION, (ArrayList<Integer>) filter);
                intent.putExtra(Constant.RECIPE_BUNDLE, bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView    txtRecipeName,
                    txtRecipeLike,
                txtTenMonan_LyGoiYMonan,
                txtDesMonan_LyGoiYMonan,
                txtTimeMonan_LyGoiYMonan;
        ImageView   imvRecipeThumb, imv_LyGoiMonan;
        private RecyclerViewItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type) {
                case RECIPE_ITEM:{
                    txtRecipeName = itemView.findViewById(R.id.txtRecipeName_ListRecipe);
                    txtRecipeLike = itemView.findViewById(R.id.txtRecipeLike_ListRecipe);
                    imvRecipeThumb = itemView.findViewById(R.id.imvRecipeThumb_ListRecipe);
                    break;
                }
                case SUGGEST:{
                    imv_LyGoiMonan = itemView.findViewById(R.id.imv_LyGoiMonan);
                    txtTimeMonan_LyGoiYMonan = itemView.findViewById(R.id.txtTimeMonan_LyGoiYMonan);
                    txtTenMonan_LyGoiYMonan = itemView.findViewById(R.id.txtTenMonan_LyGoiYMonan);
                    txtDesMonan_LyGoiYMonan = itemView.findViewById(R.id.txtDesMonan_LyGoiYMonan);
                    break;
                }
            }
            itemView.setOnClickListener(this);


        }

        public void setItemClickListener(RecyclerViewItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}

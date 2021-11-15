package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.mcommerce.adapter.ListRecipeAdapter;
import com.mcommerce.model.Recipe;

import java.util.ArrayList;

public class ListRecipeActivity extends AppCompatActivity {

    GridView grvListRecipe_Recipe;
    ArrayList<Recipe> listRecipe;
    ListRecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        linkViews();

        initData();
        initAdapter();
    }

    private void linkViews() {
        grvListRecipe_Recipe=findViewById(R.id.grvListRecipe_Recipe);
    }

    private void initData() {
        listRecipe=new ArrayList<Recipe>();
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
        listRecipe.add(new Recipe("Cách làm bánh Donut",500,R.drawable.suggest_monan,R.drawable.ic_heart));
    }

    private void initAdapter() {
        adapter=new ListRecipeAdapter(ListRecipeActivity.this,R.layout.item_recipe_listrecipe_layout,listRecipe);
        grvListRecipe_Recipe.setAdapter(adapter);
    }
}
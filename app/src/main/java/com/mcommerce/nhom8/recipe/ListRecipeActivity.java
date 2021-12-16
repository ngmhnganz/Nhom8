package com.mcommerce.nhom8.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.RecipeAdapter;
import com.mcommerce.model.Product;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.order.CartActivity;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeActivity extends AppCompatActivity {

    RecyclerView rcvListRecipe_Recipe;
    RecipeAdapter adapter;
    ImageButton btnBack_aListProduct, btnCart;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Recipe recipe = new Recipe();
    List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe2);
        linkViews();
        initData();
        addEvents();
    }

    private void addEvents() {
        btnBack_aListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListRecipeActivity.this, CartActivity.class));
            }
        });

    }

    private void linkViews() {
        rcvListRecipe_Recipe =findViewById(R.id.rcvListRecipe_Recipe);
        rcvListRecipe_Recipe.setLayoutManager(new GridLayoutManager(this, 2));
        btnBack_aListProduct=findViewById(R.id.btnBack_aListProduct);
        btnCart=findViewById(R.id.btnCart);
    }

    private void initData() {
        ref.child("CongThuc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    recipe = dataSnapshot.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                initAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initAdapter() {
        adapter = new RecipeAdapter(ListRecipeActivity.this,RecipeAdapter.RECIPE_ITEM,recipeList);
        rcvListRecipe_Recipe.setAdapter(adapter);
    }
}
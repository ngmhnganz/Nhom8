package com.mcommerce.nhom8.recipe;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.adapter.RecipeAdapter;
import com.mcommerce.model.Product;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.product.ListProductActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;
import com.mcommerce.nhom8.order.CartActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class ListRecipeActivity extends AppCompatActivity {

    RecyclerView rcvListRecipe_Recipe;
    RecipeAdapter adapter;
    ImageButton btnBack_aListProduct, btnCart;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Recipe recipe;

    SearchView searchView_aListProduct;
    List<Recipe> recipeList = new ArrayList<>();
    List<Integer> ingredients;

    List<Integer> filter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        linkViews();
        getData();
        initData();
        addEvents();
    }
    private void linkViews() {
        rcvListRecipe_Recipe =findViewById(R.id.rcvListRecipe_Recipe);
        rcvListRecipe_Recipe.setLayoutManager(new GridLayoutManager(this, 2));
        rcvListRecipe_Recipe.setAdapter(new RecipeAdapter(ListRecipeActivity.this,RecipeAdapter.RECIPE_ITEM,null, filter));

        btnBack_aListProduct=findViewById(R.id.btnBack_aListProduct);
        btnCart=findViewById(R.id.btnCart);
        searchView_aListProduct = findViewById(R.id.searchView_aListProduct);
    }

    private void getData() {
        Intent intent = getIntent();
        filter = intent.getIntegerArrayListExtra(Constant.FILTER_OPTION);
        if (filter!=null) Collections.sort(filter);
    }

    private void initData() {
        ref.child(Key.RECIPE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                recipe = new Recipe();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    recipe = dataSnapshot.getValue(Recipe.class);
                    if (filter==null){
                        recipeList.add(recipe);
                    }
                    else {
                        ingredients = new ArrayList<>();
                        for (HashMap<String,?> value: recipe.getRecipeIngredient().values()) {
                            ingredients.add( ( (Long) value.get("id")).intValue() );
                        }
                        if (check(ingredients)) recipeList.add(recipe);
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
        adapter = new RecipeAdapter(ListRecipeActivity.this,RecipeAdapter.RECIPE_ITEM,recipeList, filter);
        rcvListRecipe_Recipe.setAdapter(adapter);
    }

    private void addEvents() {
        btnBack_aListProduct.setOnClickListener(view -> finish());
        btnCart.setOnClickListener(v -> startActivity(new Intent(ListRecipeActivity.this, CartActivity.class)));

        searchView_aListProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Recipe> list = new ArrayList<>();
                for (Recipe recipe: recipeList){
                    if (recipe.getRecipeName().toLowerCase().contains(newText.toLowerCase())){
                        list.add(recipe);
                    }
                }
                adapter = new RecipeAdapter(ListRecipeActivity.this,RecipeAdapter.RECIPE_ITEM,list, filter);
                rcvListRecipe_Recipe.setAdapter(adapter);
                return true;
            }
        });
    }

    private boolean check(List<Integer> ingredients){
        Collections.sort(ingredients);
        int i =0; int j =0;

        while ((i < ingredients.size()) && (j < filter.size())) {
            while (filter.get(j) > ingredients.get(i)){
                i = i+1;
                if (i>=ingredients.size()){
                    return false;
                }
            }
            if (!filter.get(j).equals(ingredients.get(i)))
                return false;
            else {
                i = i+1;
                j= j+1;
            }
            if (j<filter.size() && i>= ingredients.size())
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
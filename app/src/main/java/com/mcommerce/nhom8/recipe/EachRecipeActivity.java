package com.mcommerce.nhom8.recipe;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.RecipeMaterialAdapterRCV;
import com.mcommerce.model.Recipe;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.order.CartActivity;
import com.mcommerce.nhom8.product.ProductDetailActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.custom.SpacingItemDecorator;

import java.util.HashMap;

public class EachRecipeActivity extends AppCompatActivity {

    Button btnAddToCart_Recipe;
    ImageView imvDropDownMaterial;
    ImageButton btnBack,btnCart;
    TextView txtPreparedMaterials_Recipe,txtRecipe_Info_recipe, txtRecipeName_recipe;
    LinearLayout llMaterialBuying;
    CheckBox chkLike,chkLike_WL,chkLike1;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference Likeref = FirebaseDatabase.getInstance().getReference("User/"+user.getUid()+"/userLikeProduct");
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
        btnAddToCart_Recipe = findViewById(R.id.btnAddToCart_Recipe);
        btnBack=findViewById(R.id.btnBack);
        btnCart=findViewById(R.id.btnCart);
        chkLike=findViewById(R.id.chkLike);
        chkLike_WL=findViewById(R.id.chkLike);
        chkLike1=findViewById(R.id.chkLike1);
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
        Likeref.child("id"+recipe.getRecipeID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // nếu khách hàng đã từng thêm món này vào cart
                if (snapshot.getValue()!=null){
                    // nội dung nút là cập nhật
                    chkLike.setChecked(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EachRecipeActivity.this, "Lỗi",Toast.LENGTH_SHORT);
            }
        });
    }

    private void addEvents() {
        imvDropDownMaterial.setOnClickListener(clickSetVisibility);
        txtPreparedMaterials_Recipe.setOnClickListener(clickSetVisibility);
        btnAddToCart_Recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(EachRecipeActivity.this, CartActivity.class));
            }
        });


//        //khi click vào item trên rcv view => đồng thời đổi màu + lưu list sp người dùng chọn
//        rcvRecipeMaterial.setOnItemClickListener
        chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String recipeIDs = (String) recipe.getRecipeID();
                    if (isChecked) {
                        Likeref.child("id"+recipeIDs).child("name").setValue(recipe.getRecipeName());
                        Likeref.child("id"+recipeIDs).child("id").setValue(recipeIDs);
                        Likeref.child("id"+recipeIDs).child("des").setValue(recipe.getRecipeDescription());
                        Likeref.child("id"+recipeIDs).child("thumb").setValue(recipe.getRecipeImage());
                        Likeref.child("id"+recipeIDs).child("time").setValue(recipe.getRecipeTime());
                    }
                    else {
                        Likeref.child("id"+recipeIDs).removeValue();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
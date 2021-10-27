package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.mcommerce.adapter.CategoryAdapter;
import com.mcommerce.model.Category;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AllProducts extends AppCompatActivity {

    private RecyclerView rcvCategory_allproducts;
    private CategoryAdapter categoryAdapter;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        linkview();
        initAdapter();
        addEvent();

    }

    private void addEvent() {
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(AllProducts.this ,
                        MainActivity.class);
                AllProducts.this.startActivity(intentMain);
            }
        });
    }

    private void initAdapter() {
        categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvCategory_allproducts.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvCategory_allproducts.setAdapter(categoryAdapter);

    }

    private void linkview() {
        rcvCategory_allproducts=findViewById(R.id.rcvCategory_allproducts);
        topAppBar = findViewById(R.id.topAppBar_allProducts);
    }

    // hàm get dữ liệu từ server gửi về
    private List<Category> getListCategory(){
        List<Category> listCategory = new ArrayList<>();
        List<Product> listProduct = new ArrayList<>();

        // làm dữ liệu giả

        listProduct.add(new Product("Bột mì",R.drawable.botmi, 24000));
        listProduct.add(new Product("Bột ca cao",R.drawable.botcacao, 120000));
        listProduct.add(new Product("Bột trà xanh",R.drawable.bottraxanh, 59000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));

        listCategory.add(new Category("Nguyên liệu","Xem tất cả",listProduct));
        listCategory.add(new Category("Dụng cụ","Xem tất cả",listProduct));
        listCategory.add(new Category("Combo","Xem tất cả",listProduct));

        return listCategory;
    }

}
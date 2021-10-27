package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mcommerce.adapter.CategoryAdapter;
import com.mcommerce.model.Category;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AllProducts extends AppCompatActivity {

    private RecyclerView rcvCategory_allproducts;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        rcvCategory_allproducts=findViewById(R.id.rcvCategory_allproducts);
        categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvCategory_allproducts.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvCategory_allproducts.setAdapter(categoryAdapter);

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
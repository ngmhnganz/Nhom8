package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.mcommerce.adapter.ListProductAdapter;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayList<Product> products;
    ListProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        linkViews();
        initData();
        initAdapter();
    }

    private void linkViews() {
        lvProduct=findViewById(R.id.lvProducts);
    }

    //dữ liệu giả
    private void initData() {
        products=new ArrayList<Product>();
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
        products.add(new Product("Bột mì",R.drawable.botmi,20000,187,R.drawable.background_listproduct,R.drawable.ic_square_add));
    }

    private void initAdapter() {
        adapter=new ListProductAdapter(ListProductActivity.this,R.layout.item_listproduct_layout,products);
        lvProduct.setAdapter(adapter);
    }
}
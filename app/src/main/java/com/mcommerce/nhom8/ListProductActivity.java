package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;
import com.mcommerce.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    private RecyclerView rcv_aListProduct;

    private ImageButton btnBack_aListProduct;
    private String type;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private ArrayList<Product> products = new ArrayList<>();
    private SearchView searchView_aListProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        linkViews();
        initData();
        addEvent();

    }

    private void addEvent() {
        btnBack_aListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchView_aListProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Product> list = new ArrayList<>();
                for (Product product: products){
                    if (product.getProductName().toLowerCase().contains(newText.toLowerCase())){
                        list.add(product);
                    }
                }
                ProductAdapter productAdapter = new ProductAdapter();
                productAdapter.setData(ListProductActivity.this,list,ProductAdapter.PRODUCT);
                rcv_aListProduct.setAdapter(productAdapter);
                return true;
            }
        });
    }

    private void initData() {

        Intent intent = getIntent();
        type = intent.getStringExtra(Constant.PRODUCT_LIST_TYPE);

        //region Lấy dữ liệu từ firebase bỏ vào rcv
        Query query = firebaseDatabase.getReference().child("NguyenLieu").orderByChild("productType").equalTo(type);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product p = getDataProductFromFirebase(dataSnapshot);
                    products.add(p);
                }
                ProductAdapter adapter = new ProductAdapter();
                adapter.setData(ListProductActivity.this,products,ProductAdapter.PRODUCT);
                rcv_aListProduct.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion
    }

    private Product getDataProductFromFirebase(DataSnapshot dataSnapshot) {
        Product product = new Product();
        product.setProductImg(dataSnapshot.child("productImg").getValue().toString());
        product.setProductLike( ((Long) dataSnapshot.child("productLike").getValue()).intValue() );
        product.setProductDescription(dataSnapshot.child("productDescription").getValue().toString());
        product.setProductDetail(dataSnapshot.child("productDetail").getValue().toString());
        product.setProductName(dataSnapshot.child("productName").getValue().toString());
        product.setProductPrice(((Long) dataSnapshot.child("productPrice").getValue()).intValue());
        product.setProductQuantity(((Long) dataSnapshot.child("productQuantity").getValue()).intValue());
        product.setProductID(dataSnapshot.child("productID").getValue().toString());
        product.setProductType(dataSnapshot.child("productType").getValue().toString());
        return product;
    }

    private void linkViews() {
        rcv_aListProduct=findViewById(R.id.rcv_aListProduct);
        btnBack_aListProduct = findViewById(R.id.btnBack_aListProduct);
        searchView_aListProduct = findViewById(R.id.searchView_aListProduct);
        rcv_aListProduct.setLayoutManager(new LinearLayoutManager(ListProductActivity.this, LinearLayoutManager.VERTICAL,false));

    }
}
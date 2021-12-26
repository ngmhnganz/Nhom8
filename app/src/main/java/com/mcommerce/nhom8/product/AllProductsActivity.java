package com.mcommerce.nhom8.product;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.order.CartActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;

import java.util.ArrayList;

public class AllProductsActivity extends AppCompatActivity {

    private RecyclerView    rcvCategory_allproducts,
                            rcvDungCu_allproducts,
                            rcvCombo_allproducts;

    private TextView    txtMoreDC_allproducts,
                        txtMoreNL_allproducts,
                        txtMoreCB_allproducts;

    private ImageButton btnBack_allproducts, btnCart_allproducts;
    private ArrayList<Product> listProductNL = new ArrayList<>();
    private ArrayList<Product> listProductDC = new ArrayList<>();
    private ArrayList<Product> listProductCB = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        linkview();
        initAdapter();
        addEvent();
    }

    private void addEvent() {
        txtMoreCB_allproducts.setOnClickListener(goToProductList);
        txtMoreNL_allproducts.setOnClickListener(goToProductList);
        txtMoreDC_allproducts.setOnClickListener(goToProductList);

        btnBack_allproducts.setOnClickListener(v -> finish());
        btnCart_allproducts.setOnClickListener(v -> startActivity(new Intent(AllProductsActivity.this, CartActivity.class)));
    }

    View.OnClickListener goToProductList = v -> {
        Intent intent= new Intent(AllProductsActivity.this, ListProductActivity.class);
        switch (v.getId()) {
            case R.id.txtMoreCB_allproducts:
                intent.putExtra(Constant.PRODUCT_LIST_TYPE,Product.COMBO);
                startActivity(intent);
                break;
            case R.id.txtMoreNL_allproducts:
                intent.putExtra(Constant.PRODUCT_LIST_TYPE,Product.NGUYEN_lIEU);
                startActivity(intent);
                break;
            case R.id.txtMoreDC_allproducts:
                intent.putExtra(Constant.PRODUCT_LIST_TYPE,Product.DUNG_CU);
                startActivity(intent);
                break;
        }
    };

    private void initAdapter() {
        //region Lấy dữ liệu Sản Phẩm từ Fireabase về truyền cho adapter

        Query queryNL = firebaseDatabase.getReference().child(Key.PRODUCT);
        queryNL.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Product p = dataSnapshot.getValue(Product.class);
                    switch (p.getProductType()){
                        case Product.COMBO:
                            listProductCB.add(p);
                            break;
                        case Product.DUNG_CU:
                            listProductDC.add(p);
                            break;
                        case Product.NGUYEN_lIEU:
                            listProductNL.add(p);
                            break;
                    }
                }
                ProductAdapter nguyenLieuAdapter = new ProductAdapter(AllProductsActivity.this,listProductNL, ProductAdapter.CATEGORY);
                rcvCategory_allproducts.setAdapter(nguyenLieuAdapter);
                ProductAdapter comboAdapter = new ProductAdapter(AllProductsActivity.this,listProductCB, ProductAdapter.CATEGORY);
                rcvCombo_allproducts.setAdapter(comboAdapter);
                ProductAdapter dungCuAdapter = new ProductAdapter(AllProductsActivity.this,listProductDC, ProductAdapter.CATEGORY);
                rcvDungCu_allproducts.setAdapter(dungCuAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //endregion

    }

    private void linkview() {
        rcvCategory_allproducts=findViewById(R.id.rcvNguyenLieu_allproducts);
        rcvCategory_allproducts.setLayoutManager(new LinearLayoutManager(AllProductsActivity.this, LinearLayoutManager.HORIZONTAL,false));
        rcvCategory_allproducts.setAdapter(new ProductAdapter(AllProductsActivity.this,null, ProductAdapter.CATEGORY));

        rcvDungCu_allproducts = findViewById(R.id.rcvDungCu_allproducts);
        rcvDungCu_allproducts.setLayoutManager(new LinearLayoutManager(AllProductsActivity.this, LinearLayoutManager.HORIZONTAL,false));
        rcvDungCu_allproducts.setAdapter(new ProductAdapter(AllProductsActivity.this,null, ProductAdapter.CATEGORY));

        rcvCombo_allproducts = findViewById(R.id.rcvCombo_allproducts);
        rcvCombo_allproducts.setLayoutManager(new LinearLayoutManager(AllProductsActivity.this, LinearLayoutManager.HORIZONTAL,false));
        rcvCombo_allproducts.setAdapter(new ProductAdapter(AllProductsActivity.this,null, ProductAdapter.CATEGORY));

        txtMoreDC_allproducts = findViewById(R.id.txtMoreDC_allproducts);
        txtMoreNL_allproducts = findViewById(R.id.txtMoreNL_allproducts);
        txtMoreCB_allproducts = findViewById(R.id.txtMoreCB_allproducts);

        btnBack_allproducts = findViewById(R.id.btnBack_allproducts);
        btnCart_allproducts = findViewById(R.id.btnCart_allproducts);
    }
}
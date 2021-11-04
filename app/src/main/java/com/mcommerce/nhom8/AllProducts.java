package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class AllProducts extends AppCompatActivity {

    private RecyclerView rcvCategory_allproducts, rcvDungCu_allproducts, rcvCombo_allproducts;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myref = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        linkview();
        initAdapter();
        addEvent();
    }

    private void addEvent() {
    }

    private void initAdapter() {
        //region Lấy dữ liệu Sản Phẩm từ Fireabase về truyền cho adapter
        ArrayList<Product> listProductNL = new ArrayList<>();
        ArrayList<Product> listProductDC = new ArrayList<>();
        ArrayList<Product> listProductCB = new ArrayList<>();

        ProductAdapter nguyenLieuAdapter = new ProductAdapter();
        ProductAdapter comboAdapter = new ProductAdapter();
        ProductAdapter dungCuAdapter = new ProductAdapter();

        Query queryNL = firebaseDatabase.getReference().child("NguyenLieu");
        queryNL.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Product p = getDataProductFromFirebase(dataSnapshot);
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
                };

                nguyenLieuAdapter.setData(AllProducts.this,listProductNL);
                rcvCategory_allproducts.setLayoutManager(new LinearLayoutManager(AllProducts.this, LinearLayoutManager.HORIZONTAL,false));
                rcvCategory_allproducts.setAdapter(nguyenLieuAdapter);

                comboAdapter.setData(AllProducts.this,listProductCB);
                rcvCombo_allproducts.setLayoutManager(new LinearLayoutManager(AllProducts.this, LinearLayoutManager.HORIZONTAL,false));
                rcvCombo_allproducts.setAdapter(comboAdapter);

                dungCuAdapter.setData(AllProducts.this,listProductDC);
                rcvDungCu_allproducts.setLayoutManager(new LinearLayoutManager(AllProducts.this, LinearLayoutManager.HORIZONTAL,false));
                rcvDungCu_allproducts.setAdapter(dungCuAdapter);
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

    private void linkview() {
        rcvCategory_allproducts=findViewById(R.id.rcvNguyenLieu_allproducts);
        rcvDungCu_allproducts = findViewById(R.id.rcvDungCu_allproducts);
        rcvCombo_allproducts = findViewById(R.id.rcvCombo_allproducts);
    }
}
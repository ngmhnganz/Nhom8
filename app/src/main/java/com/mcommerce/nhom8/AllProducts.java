package com.mcommerce.nhom8;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;
import com.mcommerce.util.Constant;

import java.util.ArrayList;

public class AllProducts extends AppCompatActivity {

    private RecyclerView    rcvCategory_allproducts,
                            rcvDungCu_allproducts,
                            rcvCombo_allproducts;

    private TextView    txtMoreDC_allproducts,
                        txtMoreNL_allproducts,
                        txtMoreCB_allproducts;

    ImageButton btnBack_allproducts;
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

        btnBack_allproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    View.OnClickListener goToProductList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(AllProducts.this, ListProductActivity.class);
            Bundle bundle = new Bundle();
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
        }
    };

    private void initAdapter() {
        //region Lấy dữ liệu Sản Phẩm từ Fireabase về truyền cho adapter


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

                nguyenLieuAdapter.setData(AllProducts.this,listProductNL, ProductAdapter.CATEGORY);
                rcvCategory_allproducts.setLayoutManager(new LinearLayoutManager(AllProducts.this, LinearLayoutManager.HORIZONTAL,false));
                rcvCategory_allproducts.setAdapter(nguyenLieuAdapter);

                comboAdapter.setData(AllProducts.this,listProductCB, ProductAdapter.CATEGORY);
                rcvCombo_allproducts.setLayoutManager(new LinearLayoutManager(AllProducts.this, LinearLayoutManager.HORIZONTAL,false));
                rcvCombo_allproducts.setAdapter(comboAdapter);

                dungCuAdapter.setData(AllProducts.this,listProductDC, ProductAdapter.CATEGORY);
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

        txtMoreDC_allproducts = findViewById(R.id.txtMoreDC_allproducts);
        txtMoreNL_allproducts = findViewById(R.id.txtMoreNL_allproducts);
        txtMoreCB_allproducts = findViewById(R.id.txtMoreCB_allproducts);

        btnBack_allproducts = findViewById(R.id.btnBack_allproducts);
    }
}
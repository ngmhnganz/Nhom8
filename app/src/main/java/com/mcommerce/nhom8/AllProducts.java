package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.CategoryAdapter;
import com.mcommerce.model.Category;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AllProducts extends AppCompatActivity {

    private RecyclerView rcvCategory_allproducts;
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

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference();

        //region Lấy dữ liệu Sản Phẩm từ Fireabase về truyền cho adapter
        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();

        CategoryAdapter categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvCategory_allproducts.setLayoutManager(linearLayoutManager);

        Query query = myref.child("NguyenLieu");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = new Product();
                    product.setProductImg(dataSnapshot.child("productImg").getValue().toString());
                    product.setProductLike(((Long) dataSnapshot.child("productLike").getValue()).intValue());
                    product.setProductDescription(dataSnapshot.child("productDescription").getValue().toString());
                    product.setProductDetail(dataSnapshot.child("productDetail").getValue().toString());
                    product.setProductName(dataSnapshot.child("productName").getValue().toString());
                    product.setProductPrice(((Long) dataSnapshot.child("productPrice").getValue()).intValue());
                    product.setProductQuantity(((Long) dataSnapshot.child("productQuantity").getValue()).intValue());
                    listProduct.add(product);
                };


                listCategory.add(new Category("Dụng cụ","Xem tất cả",listProduct));
                listCategory.add(new Category("Nguyên liệu","Xem tất cả",listProduct));
                listCategory.add(new Category("Combo","Xem tất cả",listProduct));

                categoryAdapter.setData(listCategory);
                rcvCategory_allproducts.setAdapter(categoryAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

    }

    private void linkview() {
        rcvCategory_allproducts=findViewById(R.id.rcvCategory_allproducts);
        topAppBar = findViewById(R.id.topAppBar_allProducts);
    }

    /* hàm get dữ liệu từ server gửi về
    private List<Category> getListCategory(){


        làm dữ liệu giả

        listProduct.add(new Product("Bột mì",R.drawable.botmi, 24000));
        listProduct.add(new Product("Bột ca cao",R.drawable.botcacao, 120000));
        listProduct.add(new Product("Bột trà xanh",R.drawable.bottraxanh, 59000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));
        listProduct.add(new Product("Trứng gà",R.drawable.trungga, 30000));

        listCategory.add(new Category("Nguyên liệu","Xem tất cả",listProduct));
        listCategory.add(new Category("Dụng cụ","Xem tất cả",listProduct));
        listCategory.add(new Category("Combo","Xem tất cả",listProduct));

        Query query = myref.child("NguyenLieu");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = new Product();
                    product.setProductImg(dataSnapshot.child("productImg").getValue().toString());
                    product.setProductLike(dataSnapshot.child("productLike").getValue().toString());
                    product.setProductDescription(dataSnapshot.child("productDescription").getValue().toString());
                    product.setProductDetail(dataSnapshot.child("productDetail").getValue().toString());
                    product.setProductName(dataSnapshot.child("productName").getValue().toString());
                    product.setProductPrice(dataSnapshot.child("productPrice").getValue().toString());
                    product.setProductQuantity(dataSnapshot.child("productQuantity").getValue().toString());
                    listProduct.add(product);
                };

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listCategory.add(new Category("Dụng cụ","Xem tất cả",listProduct));
        listCategory.add(new Category("Combo","Xem tất cả",listProduct));

        return listCategory;

    }*/

}
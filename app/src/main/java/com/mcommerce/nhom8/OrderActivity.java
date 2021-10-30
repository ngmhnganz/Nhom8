package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.OrderAdapter;
import com.mcommerce.fragment.HistoryOrderFragment;
import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    Button btnComingOrder, btnHistoryOder;
    ImageButton btnBack, btnCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        linkView();
        
        initAdapter();
        
        addEvent();

    }
  

    private void addEvent() {

        btnHistoryOder.setOnClickListener(myClick);
    }

    private void linkView() {
        btnBack = findViewById(R.id.btnBack_orderactivity);
        btnCart = findViewById(R.id.btnCart_orderactivity);
        btnComingOrder = findViewById(R.id.btnComingOrder_orderactivity);
        btnHistoryOder = findViewById(R.id.btnHistoryOrder_orderactivity);

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment= null;

            if ( view.getId()==R.id.btnHistoryOrder_orderactivity){
                fragment = new HistoryOrderFragment();
            }

            if (fragment != null){
                fragmentTransaction.replace(R.id.layoutContainer_orderactivity,fragment);
                fragmentTransaction.commit();
            }
        }
    };

    private void initAdapter() {

       /* List<Product> listProduct = new ArrayList<>();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference();
        

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
                OrderModel order = new OrderModel();
                
                order.setAddOrder("2 Bùi Đình Túy quận Bình Thạnh");
                order.setDateOrder("12/03/2001");
                order.setIdOrder("or3");
                order.setPaymentOrder("Tiền mặt");
                order.setPriceOrder("120000");
                order.setStatusOrder("6");

                HashMap<String,String> itemOrder = new HashMap<>();
                itemOrder.put(listProduct.get(1).getProductName(),"1");
                itemOrder.put(listProduct.get(3).getProductName(),"4");
                itemOrder.put(listProduct.get(5).getProductName(),"2");

                order.setItemOrder(itemOrder);
                order.setImgOrder(listProduct.get(1).getProductImg());

                myref.child("DonHang").child(order.getIdOrder()).setValue(order);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        
        
    }
    
}
package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.fragment.HistoryOrderFragment;
import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;

import java.util.ArrayList;
import java.util.Date;
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
                btnHistoryOder.setEnabled(false);
                btnComingOrder.setEnabled(true);

            }

            if (fragment != null){
                fragmentTransaction.replace(R.id.layoutContainer_orderactivity,fragment);
                fragmentTransaction.commit();
            }
        }
    };

    private void initAdapter() {

        //region Tạo dữ liệu cho Order trên date base, xóa cũng được
        List<Product> listProduct = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference();
        

        Query query = myref.child("NguyenLieu");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
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
                    listProduct.add(product);
                };

                OrderModel order = new OrderModel();
                
                order.setAddOrder("48 Bùi Thị Xuân Quận 3 Hồ Chí Minh");

                order.setIdOrder("od1");
                order.setPaymentOrder("Ngân hàng");
                order.setPriceOrder(890000);
                order.setStatusOrder(OrderModel.DA_HUY);

                Date currentTime = Calendar.getInstance().getTime();
                order.setDateOrder(currentTime.toString());


                HashMap<String,Integer> itemOrder = new HashMap<>();
                itemOrder.put(  listProduct.get(2).getProductID()   , 1  );
                itemOrder.put(listProduct.get(4).getProductID(), 2);
                itemOrder.put(listProduct.get(8).getProductID(), 2);
                itemOrder.put(listProduct.get(9).getProductID(), 3);

                order.setItemOrder(itemOrder);
                order.setImgOrder(listProduct.get(0).getProductImg());

                myref.child("DonHang").child(order.getIdOrder()).setValue(order);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion
        
        
    }
    
}
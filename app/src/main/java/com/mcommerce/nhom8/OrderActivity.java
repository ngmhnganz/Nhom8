package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.mcommerce.fragment.ComingOrderFragment;
import com.mcommerce.fragment.HistoryOrderFragment;

public class OrderActivity extends AppCompatActivity {

    Button btnComingOrder, btnHistoryOder;
    ImageButton btnBack, btnCart;
    private Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        linkView();
        
        initAdapter();
        
        addEvent();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new ComingOrderFragment();
        btnComingOrder.setEnabled(false);
        btnHistoryOder.setEnabled(true);
        btnComingOrder.setBackgroundResource(R.drawable.button_underline);

        fragmentTransaction.replace(R.id.containerOrderLists_orderactivity,fragment);
        fragmentTransaction.commit();

    }
  

    private void addEvent() {

        btnHistoryOder.setOnClickListener(myClick);
        btnComingOrder.setOnClickListener(myClick);
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

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment= null;

            if ( view.getId()==R.id.btnHistoryOrder_orderactivity){
                fragment= null;
                fragment = new HistoryOrderFragment();
                btnHistoryOder.setEnabled(false);
                btnComingOrder.setEnabled(true);
                btnHistoryOder.setBackgroundResource(R.drawable.button_underline);
                btnComingOrder.setBackgroundResource(R.color.white);

            } else if (view.getId()==R.id.btnComingOrder_orderactivity){
                fragment= null;
                fragment = new ComingOrderFragment();
                btnComingOrder.setEnabled(false);
                btnHistoryOder.setEnabled(true);
                btnComingOrder.setBackgroundResource(R.drawable.button_underline);
                btnHistoryOder.setBackgroundResource(R.color.white);
            }

            if (fragment != null){
                fragmentTransaction.replace(R.id.containerOrderLists_orderactivity,fragment);
                fragmentTransaction.commit();
            }
        }
    };

    private void initAdapter() {

    /*   //region Tạo dữ liệu cho Order trên date base, xóa cũng được
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
                
                order.setAddOrder("48 Bùi Thị Xuân Quận 3");

                order.setIdOrder("od1");
                order.setPaymentOrder("Tiền mặt");
                order.setPriceOrder(9050000);
                order.setStatusOrder(OrderModel.THANH_CONG);

                String sDate1="11/10/2021";
                try {
                    Date date =new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                    order.setDateOrder(sDate1);
                    order.setDateLongOder(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                HashMap<String,Integer> itemOrder = new HashMap<>();

                itemOrder.put(listProduct.get(9).getProductID(), 2);
                itemOrder.put(listProduct.get(1).getProductID(), 2);
                itemOrder.put(listProduct.get(14).getProductID(), 2);
                order.setItemOrder(itemOrder);
                order.setImgOrder(listProduct.get(0).getProductImg());

                myref.child("DonHang").child(order.getIdOrder()).setValue(order);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //endregion
        
        
    }

  /*  public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/YYYY").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }*/
    
}
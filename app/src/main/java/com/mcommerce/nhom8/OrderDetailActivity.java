package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.OrderDetailAdapter;
import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;
import com.mcommerce.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    //region Khai báo biên
    private TextView    txtID_aOrderDetail,
                        txtDate_aOrderDetail,
                        txtTotalQuantity_aOrderDetail,
                        txtSubtotal_aOrderDetail,
                        txtShippingFee_aOrderDetail,
                        txtDiscount_aOrderDetail,
                        txtBeforeDiscount_aOrderDetail,
                        txtTotal_aOrderDetail,
                        txtCustomerName_aOrderDetail,
                        txtCustomerPhone_aOrderDetail,
                        txtCustomerAddress_aOrderDetail,
                        txtStatus_aOrderDetail;
    private Button      btnSupport_aOrderDetail,
                        btnReOrder_aOrderDetail;

    private ImageView   imvStatus_aOrderDetail;
    private RecyclerView rcvListProduct_aOrderDetail;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private List<Product> products;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        linkview();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra(Constant.ORDER_BUNDLE);

        OrderModel order = bundle.getParcelable(Constant.SELECTED_ORDER);
        txtID_aOrderDetail.setText(order.getIdOrder());
        txtDate_aOrderDetail.setText(order.getDateOrder());
        txtSubtotal_aOrderDetail.setText(order.getPriceOrder()+"");

        txtShippingFee_aOrderDetail.setText(order.getShippingFeeOrder()+"");
        if (order.getDiscountOrder()!=0){
            LinearLayout layoutDiscount = findViewById(R.id.layoutDiscount);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.weight= 9f;
            TextView txtDiscountTitle = new TextView(this);
            txtDiscountTitle.setTextSize(14);
            txtDiscountTitle.setText("Khuyến mãi");
            txtDiscountTitle.setLayoutParams(params);
            txtDiscountTitle.setTextColor(getResources().getColor(R.color.colorText));
            layoutDiscount.addView(txtDiscountTitle);


            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params2.weight=3f;
            TextView txtDiscount = new TextView(this);
            txtDiscount.setTextSize(14);
            txtDiscount.setText(order.getDiscountOrder()+"");
            txtDiscount.setLayoutParams(params);
            txtDiscount.setTextColor(getResources().getColor(R.color.colorText));
            layoutDiscount.addView(txtDiscount);



        }
        txtStatus_aOrderDetail.setText(order.getStatusStringOrder());

        //region Lấy dữ liệu Order từ Firebase
        HashMap<String,Integer> itemsOrder = (HashMap<String, Integer>) bundle.getSerializable(Constant.ITEMS_ORDER);


        //endregion

        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this,itemsOrder);
        rcvListProduct_aOrderDetail.setAdapter(orderDetailAdapter);

    }


    private void linkview() {
        txtID_aOrderDetail = findViewById(R.id.txtID_aOrderDetail);
        txtDate_aOrderDetail = findViewById(R.id.txtDate_aOrderDetail);
        txtTotalQuantity_aOrderDetail = findViewById(R.id.txtTotalQuantity_aOrderDetail);
        txtSubtotal_aOrderDetail = findViewById(R.id.txtSubtotal_aOrderDetail);
        txtShippingFee_aOrderDetail = findViewById(R.id.txtShippingFee_aOrderDetail);
        /*txtDiscount_aOrderDetail = findViewById(R.id.txtDiscount_aOrderDetail);*/
        txtBeforeDiscount_aOrderDetail = findViewById(R.id.txtBeforeDiscount_aOrderDetail);
        txtTotal_aOrderDetail = findViewById(R.id.txtTotal_aOrderDetail);
        txtCustomerName_aOrderDetail = findViewById(R.id.txtCustomerName_aOrderDetail);
        txtCustomerPhone_aOrderDetail = findViewById(R.id.txtCustomerPhone_aOrderDetail);
        txtCustomerAddress_aOrderDetail = findViewById(R.id.txtCustomerAddress_aOrderDetail);
        txtStatus_aOrderDetail = findViewById(R.id.txtStatus_aOrderDetail);

        imvStatus_aOrderDetail = findViewById(R.id.imvStatus_aOrderDetail);

        btnSupport_aOrderDetail = findViewById(R.id.btnSupport_aOrderDetail);
        btnReOrder_aOrderDetail = findViewById(R.id.btnReOrder_aOrderDetail);

        rcvListProduct_aOrderDetail = findViewById(R.id.rcvListProduct_aOrderDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvListProduct_aOrderDetail.setLayoutManager(linearLayoutManager);
    }
}
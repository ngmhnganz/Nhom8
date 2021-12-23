package com.mcommerce.nhom8.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcommerce.adapter.OrderDetailAdapter;
import com.mcommerce.model.Order;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import java.util.HashMap;

public class OrderDetailActivity extends AppCompatActivity {

    //region Khai báo biên
    private TextView    txtID_aOrderDetail,
                        txtDate_aOrderDetail,
                        txtDiscount_aOrderDetail,
                        txtTotalQuantity_aOrderDetail,
            txtPrice_aOrderDetail,
                        txtShippingFee_aOrderDetail,
                        txtBeforeDiscount_aOrderDetail,
                        txtTotal_aOrderDetail,
                        txtCustomerName_aOrderDetail,
                        txtCustomerPhone_aOrderDetail,
                        txtCustomerAddress_aOrderDetail,
                        txtStatus_aOrderDetail;
    private Button      btnSupport_aOrderDetail,
                        btnReOrder_aOrderDetail;
    private ImageButton btnBack_orderactivity;
    LinearLayout llTitleDiscount_aOrderDetail;

    private RecyclerView rcvListProduct_aOrderDetail;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        linkview();
        getData();
        addEvents();
    }

    private void addEvents() {
        btnBack_orderactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSupport_aOrderDetail.setOnClickListener(v -> {
            Dialog commingsoon = new Dialog(this);
            commingsoon.setContentView(R.layout.diaglog_comming_soon);
            commingsoon.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btnOk=commingsoon.findViewById(R.id.btnOK);

            btnOk.setOnClickListener(l -> {
                commingsoon.dismiss();
            });

            commingsoon.show();
        });

        btnReOrder_aOrderDetail.setOnClickListener(v -> {

        });
    }

    private void getData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra(Constant.ORDER_BUNDLE);

        Order order = bundle.getParcelable(Constant.SELECTED_ORDER);
        txtID_aOrderDetail.setText("Mã đơn hàng "+order.getIdOrder());
        txtDate_aOrderDetail.setText(order.getDateOrder());
        txtCustomerName_aOrderDetail.setText(order.getCustomerName());
        txtCustomerPhone_aOrderDetail.setText(order.getCustomerPhone());
        txtCustomerAddress_aOrderDetail.setText(order.getAddOrder());
        txtPrice_aOrderDetail.setText(order.getPriceOrder()+" đ");
        txtShippingFee_aOrderDetail.setText(order.getShippingFeeOrder()+" đ");
        txtDiscount_aOrderDetail.setText(order.getDiscountOrder()+" đ");
        txtTotal_aOrderDetail.setText(order.getTotalOrder()+" đ");
        txtBeforeDiscount_aOrderDetail.setText(order.getTotalOrder()+order.getDiscountOrder()+" đ");
        txtStatus_aOrderDetail.setText(order.getStatusStringOrder());


        if (order.getDiscountOrder()!=0L){
            llTitleDiscount_aOrderDetail.setVisibility(View.VISIBLE);
        }
        txtStatus_aOrderDetail.setText(order.getStatusStringOrder());

        HashMap<String,HashMap<String, ?>> itemsOrder = (HashMap<String,HashMap<String, ?>>) bundle.getSerializable(Constant.ITEMS_ORDER);
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this,itemsOrder);
        rcvListProduct_aOrderDetail.setAdapter(orderDetailAdapter);

        long amount =0;
        long s;
        for (String i :  itemsOrder.keySet()) {
            s = (long) itemsOrder.get(i).get("quantity");
            amount += s;
        }
        txtTotalQuantity_aOrderDetail.setText(amount+" món");

    }


    private void linkview() {
        txtID_aOrderDetail = findViewById(R.id.txtID_aOrderDetail);
        txtDate_aOrderDetail = findViewById(R.id.txtDate_aOrderDetail);
        txtTotalQuantity_aOrderDetail = findViewById(R.id.txtTotalQuantity_aOrderDetail);
        txtPrice_aOrderDetail = findViewById(R.id.txtPrice_aOrderDetail);
        txtShippingFee_aOrderDetail = findViewById(R.id.txtShippingFee_aOrderDetail);
        txtBeforeDiscount_aOrderDetail = findViewById(R.id.txtBeforeDiscount_aOrderDetail);
        txtTotal_aOrderDetail = findViewById(R.id.txtTotal_aOrderDetail);
        txtCustomerName_aOrderDetail = findViewById(R.id.txtCustomerName_aOrderDetail);
        txtCustomerPhone_aOrderDetail = findViewById(R.id.txtCustomerPhone_aOrderDetail);
        txtCustomerAddress_aOrderDetail = findViewById(R.id.txtCustomerAddress_aOrderDetail);
        txtStatus_aOrderDetail = findViewById(R.id.txtStatus_aOrderDetail);

        txtDiscount_aOrderDetail = findViewById(R.id.txtDiscount_aOrderDetail);
        llTitleDiscount_aOrderDetail = findViewById(R.id.llTitleDiscount_aOrderDetail);

        btnSupport_aOrderDetail = findViewById(R.id.btnSupport_aOrderDetail);
        btnReOrder_aOrderDetail = findViewById(R.id.btnReOrder_aOrderDetail);

        rcvListProduct_aOrderDetail = findViewById(R.id.rcvListProduct_aOrderDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvListProduct_aOrderDetail.setLayoutManager(linearLayoutManager);
        btnBack_orderactivity=findViewById(R.id.btnBack_orderactivity);
    }
}
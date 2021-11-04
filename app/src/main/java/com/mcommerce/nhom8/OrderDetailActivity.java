package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
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
                        txtBeforeDiscount_aOrderDetail,
                        txtTotal_aOrderDetail,
                        txtCustomerName_aOrderDetail,
                        txtCustomerPhone_aOrderDetail,
                        txtCustomerAddress_aOrderDetail,
                        txtStatus_aOrderDetail;
    private Button      btnSupport_aOrderDetail,
                        btnReOrder_aOrderDetail;

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
        txtID_aOrderDetail.setText("Mã đơn hàng "+order.getIdOrder());
        txtDate_aOrderDetail.setText(order.getDateOrder());
        txtSubtotal_aOrderDetail.setText(order.getPriceOrder()+" đ");

        txtShippingFee_aOrderDetail.setText(order.getShippingFeeOrder()+" đ");
        if (order.getDiscountOrder()!=0){
            LinearLayout layoutDiscount = findViewById(R.id.layoutDiscount);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.weight=7;
            TextView txtDiscountTitle = new TextView(this);
            txtDiscountTitle.setTextSize(14);
            txtDiscountTitle.setText("Khuyến mãi");
            txtDiscountTitle.setLayoutParams(params);
            txtDiscountTitle.setTextColor(getResources().getColor(R.color.colorText));
            txtDiscountTitle.setTypeface(ResourcesCompat.getFont(this,R.font.sfpro_medium));
            layoutDiscount.addView(txtDiscountTitle);


            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);

            params2.weight=3;
            TextView txtDiscount = new TextView(this);
            txtDiscount.setTextSize(14);
            txtDiscount.setText("-"+order.getDiscountOrder()+" đ");
            txtDiscount.setLayoutParams(params2);
            txtDiscount.setTextColor(getResources().getColor(R.color.colorText));
            txtDiscount.setTypeface(ResourcesCompat.getFont(this,R.font.sfpro_medium));
            txtDiscount.setGravity(Gravity.END);
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

        btnSupport_aOrderDetail = findViewById(R.id.btnSupport_aOrderDetail);
        btnReOrder_aOrderDetail = findViewById(R.id.btnReOrder_aOrderDetail);

        rcvListProduct_aOrderDetail = findViewById(R.id.rcvListProduct_aOrderDetail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvListProduct_aOrderDetail.setLayoutManager(linearLayoutManager);
    }
}
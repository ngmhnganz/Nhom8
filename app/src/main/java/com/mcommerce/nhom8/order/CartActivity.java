package com.mcommerce.nhom8.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.CartAdapter;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.auth.LoginActivity;
import com.mcommerce.util.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity {

    private TextView txtDeleteAll_aCart;
    private RecyclerView rcv_aCart;
    private Button btnPayment_aCart;
    private ImageButton btnBack;
    private CartAdapter adapter;
    private ImageView imvCartEmpty_aCart;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private DatabaseReference myRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        linkview();
        loadData();
        addEvent();
    }

    private void linkview() {
        rcv_aCart = findViewById(R.id.rcv_aCart);
        rcv_aCart.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
        btnPayment_aCart = findViewById(R.id.btnPayment_aCart);
        txtDeleteAll_aCart = findViewById(R.id.txtDeleteAll_aCart);
        imvCartEmpty_aCart= findViewById(R.id.imvCartEmpty_aCart);
        btnBack=findViewById(R.id.btnBack);
    }

    private void loadData() {
        if (user != null) {
            ProgressDialog progressDialog = new ProgressDialog(CartActivity.this);
            progressDialog.show();
            myRef = firebaseDatabase.getReference().child(Key.USER).child(user.getUid()).child(User.Cart);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String,HashMap<String,?>> cartList = (Map<String, HashMap<String,?>>) snapshot.getValue();
                    if (cartList != null) {
                        btnPayment_aCart.setEnabled(true);
                        Map<String, HashMap<String,?>> sortedMap = new TreeMap<String, HashMap<String,?>>(cartList);
                        adapter = new CartAdapter(CartActivity.this,sortedMap,R.layout.layout_cart_item);
                        imvCartEmpty_aCart.setVisibility(View.GONE);

                    } else {
                        adapter = null;
                        imvCartEmpty_aCart.setVisibility(View.VISIBLE);
                        btnPayment_aCart.setEnabled(false);
                    }
                    progressDialog.dismiss();
                    rcv_aCart.setAdapter(adapter);

               }
               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
        }
    }

    View.OnClickListener requestUser = v -> {
        Dialog requestUser = new Dialog(CartActivity.this);
        requestUser.setContentView(R.layout.dialog_request_user);
        requestUser.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnDangNhap=requestUser.findViewById(R.id.btnDangNhap);
        Button btnDong=requestUser.findViewById(R.id.btnDong);

        btnDong.setOnClickListener(l -> requestUser.dismiss());
        btnDangNhap.setOnClickListener(l -> {
            startActivity(new Intent(CartActivity.this, LoginActivity.class));
            finish();
        });
        requestUser.show();
    };

    private void addEvent() {
        if (user != null){
            btnPayment_aCart.setOnClickListener(v -> {
                startActivity(new Intent(CartActivity.this, PaymentActivity.class));
            });
            txtDeleteAll_aCart.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setMessage("Các sản phẩm sẽ bị xóa khỏi giỏ hàng của bạn");
                builder.setNegativeButton("Hủy", (dialog, which) -> {});
                builder.setPositiveButton("Xóa", (dialog, which) -> {
                    myRef = firebaseDatabase.getReference().child(Key.USER).child(user.getUid()).child(User.Cart);
                    myRef.removeValue();
                    adapter = null;
                    imvCartEmpty_aCart.setVisibility(View.VISIBLE);
                    rcv_aCart.setAdapter(adapter);
                });
                builder.create().show();
            });
        }
        else {
            btnPayment_aCart.setOnClickListener(requestUser);
            txtDeleteAll_aCart.setOnClickListener(requestUser);
        }
        btnBack.setOnClickListener(v -> finish());

    }


}
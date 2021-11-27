package com.mcommerce.nhom8.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.CartAdapter;
import com.mcommerce.nhom8.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity {

    private TextView txtDeleteAll_aCart;
    private RecyclerView rcv_aCart;
    private Button btnPayment_aCart;
    private CartAdapter adapter;

    private  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private DatabaseReference myRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        linkview();
        setAdapter();
    }

    private void linkview() {
//        txtDeleteAll_aCart = findViewById(R.id.txtDeleteAll_aCart);
        rcv_aCart = findViewById(R.id.rcv_aCart);
        btnPayment_aCart = findViewById(R.id.btnPayment_aCart);
        txtDeleteAll_aCart = findViewById(R.id.txtDeleteAll_aCart);
    }

    private void setAdapter() {

        if (user == null) {

        } else {
           myRef = firebaseDatabase.getReference().child("User");
           myRef.child(user.getUid()).child("userCart").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String,Integer> cartList = (Map<String, Integer>) snapshot.getValue();
                   Map<String, Integer> sortedMap = new TreeMap<String, Integer>(cartList);
                   if (sortedMap != null) {
                       adapter = new CartAdapter(CartActivity.this,sortedMap);
                       rcv_aCart.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
                       rcv_aCart.setAdapter(adapter);

                   } else {
                       adapter = null;
                   }


               }
               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
        }
        txtDeleteAll_aCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setMessage("Các sản phẩm sẽ bị xóa khỏi giỏ hàng của bạn");
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myRef.child(user.getUid()).child("userCart").removeValue();
                    }
                });
                builder.create().show();

            }
        });

    }
}
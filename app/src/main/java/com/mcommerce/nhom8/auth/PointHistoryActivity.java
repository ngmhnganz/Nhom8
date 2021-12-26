package com.mcommerce.nhom8.auth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.OrderAdapter;
import com.mcommerce.model.Order;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Key;

import java.util.ArrayList;
import java.util.List;

public class PointHistoryActivity extends AppCompatActivity {

    RecyclerView rcv_PointsHistory;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_history);
        linkview();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Cycle START");
        getData();
    }

    private void linkview() {
        rcv_PointsHistory = findViewById(R.id.rcv_PointsHistory);
        rcv_PointsHistory.setLayoutManager(new LinearLayoutManager(PointHistoryActivity.this, LinearLayoutManager.VERTICAL,false));
    }


    private void getData() {
        if (user != null ){
            ref = FirebaseDatabase.getInstance().getReference(Key.USER+"/"+user.getUid()+"/"+ User.Order);
            ref.addListenerForSingleValueEvent(valueEventListener);
        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            List<Order> orderList = new ArrayList<>();
            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                Order order = dataSnapshot.getValue(Order.class);
                orderList.add(order);
            }
            OrderAdapter adapter = new OrderAdapter(PointHistoryActivity.this, R.layout.item_points_history, orderList,OrderAdapter.POINT);
            rcv_PointsHistory.setAdapter(adapter);
            System.out.println("Cycle listening");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

}
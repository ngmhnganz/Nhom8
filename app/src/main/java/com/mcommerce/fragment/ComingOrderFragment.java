package com.mcommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.OrderAdapter;
import com.mcommerce.model.Order;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComingOrderFragment extends Fragment {

    private View view;
    RecyclerView rcv_fragmentComingOrder;

    private OrderAdapter adapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_coming_order,container,false);

        linkdata();
        initData();

        return view;
    }

    private void initData() {
        Query query = myRef.child("User/"+user.getUid()+"/userOrder").orderByChild("statusOrder").startAt(Order.DAT_HANG_THANH_CONG).endAt(Order.VAN_CHUYEN);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Order> orderLists = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    orderLists.add(setData(dataSnapshot));
                };
                adapter = new OrderAdapter(getContext(),R.layout.layout_coming_order_item,orderLists, OrderAdapter.COMING_ITEM);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.divider));
                rcv_fragmentComingOrder.setAdapter(adapter);
                rcv_fragmentComingOrder.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void linkdata() {
        rcv_fragmentComingOrder = view.findViewById(R.id.rcv_fragmentComingOrder);
        rcv_fragmentComingOrder.setLayoutManager(linearLayoutManager);
    }

    private Order setData (DataSnapshot dataSnapshot){
        Order order = new Order();
        order = dataSnapshot.getValue(Order.class);
//        order.setStatusOrder((int) dataSnapshot.child("statusOrder").getValue());
        switch (order.getStatusOrder()){
            case 0 :
                order.setStatusStringOrder("Giao hàng thành công");
                break;
            case 1 :
                order.setStatusStringOrder("Đã hủy đơn hàng");
                break;
        }
        return order;
    }
}

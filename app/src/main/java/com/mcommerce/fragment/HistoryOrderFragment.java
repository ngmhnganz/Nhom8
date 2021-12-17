package com.mcommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
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
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HistoryOrderFragment extends Fragment {

    TextView txtDate_fragmentHistoryOrder;
    RecyclerView rcv_fragmentHistoryOrder;
    MaterialDatePicker materialDatePicker;
    ImageView imvSearchEmpty;
    ArrayList<Order> orderLists;

    private View view;
    private OrderAdapter adapter;

    long startDate, endDate;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history_order,container,false);

        linkview();
        initAdapter();
        addEvent();

        return view;

    }

    private void initAdapter() {
        if (user == null) {

        }
        //region Lấy dữ liệu Order từ Firebase
        Query query = myRef.child("User/"+user.getUid()+"/userOrder").orderByChild("statusOrder").startAt(Order.THANH_CONG).endAt(Order.DA_HUY);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderLists = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                   orderLists.add(setData(dataSnapshot));
                };
                adapter = new OrderAdapter(getContext(),R.layout.layout_history_order_item,orderLists, OrderAdapter.HISTORY_ITEM);
                rcv_fragmentHistoryOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion
    }

    private Order setData (DataSnapshot dataSnapshot){

        Order order = new Order();
        order = dataSnapshot.getValue(Order.class);

        order.setStatusOrder(((Long) dataSnapshot.child("statusOrder").getValue()).intValue());
        switch (order.getStatusOrder()){
            case 0 :
                order.setStatusStringOrder("Giao hàng thành công");
                break;
            case 1 :
                order.setStatusStringOrder("Đã hủy đơn hàng");
                break;
            case 2 :
                order.setStatusStringOrder("Đặt hàng thành công");
                break;
            case 3 :
                order.setStatusStringOrder("Đơn hàng đã được xác nhận");
                break;
            case 4 :
                order.setStatusStringOrder("Đơn hàng đang được chuẩn bị");
                break;
            case 5 :
                order.setStatusStringOrder("Đơn hàng đã được đóng gói");
                break;
            case 6 :
                order.setStatusStringOrder("Đơn hàng đang được vận chuyển");
                break;
        }
        return order;
    }


    private void addEvent() {

        //region Xử lý chọn ngày
        materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),MaterialDatePicker.todayInUtcMilliseconds())).build();

        txtDate_fragmentHistoryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(((MainActivity)getActivity()).getSupportFragmentManager(), "DateRangePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long,Long> selection) {

                        startDate = selection.first;
                        endDate=selection.second;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        txtDate_fragmentHistoryOrder.setText(simpleDateFormat.format(new Timestamp(startDate))+ " - " + simpleDateFormat.format(new Timestamp(endDate)));

                        if (orderLists!=null){
                            ArrayList<Order> newList = new ArrayList<>();
                            for (Order order: orderLists) {
                                if (order.getDateLongOrder()>=startDate && order.getDateLongOrder()<=endDate){
                                    newList.add(order);
                                }
                            }
                            if (newList.size()!=0) {
                                adapter = new OrderAdapter(getContext(),R.layout.layout_history_order_item,newList, OrderAdapter.HISTORY_ITEM);
                                rcv_fragmentHistoryOrder.setAdapter(adapter);
                                imvSearchEmpty.setVisibility(View.GONE);
                                rcv_fragmentHistoryOrder.setVisibility(View.VISIBLE);
                                adapter.notifyDataSetChanged();
                            } else {
                                rcv_fragmentHistoryOrder.setVisibility(View.GONE);
                                imvSearchEmpty.setVisibility(View.VISIBLE);
                            }
                        }

//                        Query query = myRef.child("DonHang").orderByChild("dateLongOder").startAt(startDate).endAt(endDate);
//                        query.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                ArrayList<Order> orderLists = new ArrayList<>();
//                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                                    orderLists.add(setData(dataSnapshot));
//                                };
//                                adapter = new OrderAdapter(getContext(),R.layout.layout_history_order_item,orderLists, OrderAdapter.HISTORY_ITEM);
//                                rcv_fragmentHistoryOrder.setAdapter(adapter);
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Toast.makeText(getContext(),"Có lỗi",Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
                    }
                });
            }
        });

        //endregion
    }

    private void linkview() {
        txtDate_fragmentHistoryOrder = view.findViewById(R.id.txtDate_fragmentHistoryOrder);
        imvSearchEmpty = view.findViewById(R.id.imvSearchEmpty);
        rcv_fragmentHistoryOrder = view.findViewById(R.id.lv_fragmentHistoryOrder);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.divider));
        rcv_fragmentHistoryOrder.setLayoutManager(linearLayoutManager);
        rcv_fragmentHistoryOrder.addItemDecoration(dividerItemDecoration);
    }
}

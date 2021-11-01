package com.mcommerce.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.OrderAdapter;
import com.mcommerce.model.OrderModel;
import com.mcommerce.nhom8.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class HistoryOrderFragment extends Fragment {

    TextView txtDate_fragmentHistoryOrder;
    RecyclerView rcv_fragmentHistoryOrder;
    MaterialDatePicker materialDatePicker;

    private View view;
    private OrderAdapter adapter;

    long startDate, endDate;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history_order,container,false);

        linkview();
        initData();
        addEvent();

        return view;

    }

    private void initData() {
        //region Lấy dữ liệu Order từ Firebase
        Query query = myRef.child("DonHang").orderByChild("statusOrder").startAt(OrderModel.THANH_CONG).endAt(OrderModel.DA_HUY);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<OrderModel> orderLists = new ArrayList<>();
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

    private OrderModel setData (DataSnapshot dataSnapshot){

        OrderModel order = new OrderModel();

        order.setStatusOrder(((Long) dataSnapshot.child("statusOrder").getValue()).intValue());
        order.setPriceOrder(((Long) dataSnapshot.child("priceOrder").getValue()).intValue());
        order.setDateOrder(dataSnapshot.child("dateOrder").getValue().toString());
        order.setIdOrder(dataSnapshot.child("idOrder").getValue().toString());
        order.setPaymentOrder(dataSnapshot.child("paymentOrder").getValue().toString());
        order.setAddOrder(dataSnapshot.child("addOrder").getValue().toString());
        order.setImgOrder(dataSnapshot.child("imgOrder").getValue().toString());
        order.setDateLongOder((Long) dataSnapshot.child("dateLongOder").getValue());

        HashMap<String,Integer> alt = (HashMap<String, Integer>) dataSnapshot.child("itemOrder").getValue();
        order.setItemOrder(alt);

        return order;
    }



    private void addEvent() {

        //region Xử lý chọn ngày
        materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),MaterialDatePicker.todayInUtcMilliseconds())).build();

        txtDate_fragmentHistoryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DateRangePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long,Long> selection) {

                        startDate = selection.first;
                        endDate=selection.second;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        txtDate_fragmentHistoryOrder.setText(simpleDateFormat.format(new Timestamp(startDate))+ " - " + simpleDateFormat.format(new Timestamp(endDate)));

                        Query query = myRef.child("DonHang").orderByChild("dateLongOder").startAt(startDate).endAt(endDate);
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<OrderModel> orderLists = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    orderLists.add(setData(dataSnapshot));
                                };
                                adapter = new OrderAdapter(getContext(),R.layout.layout_history_order_item,orderLists, OrderAdapter.HISTORY_ITEM);
                                rcv_fragmentHistoryOrder.setAdapter(adapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getContext(),"Có lỗi",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
            }
        });

        //endregion

    }

    private void linkview() {
        txtDate_fragmentHistoryOrder = view.findViewById(R.id.txtDate_fragmentHistoryOrder);

        rcv_fragmentHistoryOrder = view.findViewById(R.id.rcv_fragmentHistoryOrder);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.divider));
        rcv_fragmentHistoryOrder.setLayoutManager(linearLayoutManager);
        rcv_fragmentHistoryOrder.addItemDecoration(dividerItemDecoration);
    }
}


/*
private void initAdapter() {
        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference();

        CategoryAdapter categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvCategory_allproducts.setLayoutManager(linearLayoutManager);

        Query query = myref.child("NguyenLieu");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = new Product();
                    product.setProductImg(dataSnapshot.child("productImg").getValue().toString());
                    product.setProductLike(dataSnapshot.child("productLike").getValue().toString());
                    product.setProductDescription(dataSnapshot.child("productDescription").getValue().toString());
                    product.setProductDetail(dataSnapshot.child("productDetail").getValue().toString());
                    product.setProductName(dataSnapshot.child("productName").getValue().toString());
                    product.setProductPrice(dataSnapshot.child("productPrice").getValue().toString());
                    product.setProductQuantity(dataSnapshot.child("productQuantity").getValue().toString());
                    listProduct.add(product);
                };

                listCategory.add(new Category("Dụng cụ","Xem tất cả",listProduct));
                listCategory.add(new Category("Nguyên liệu","Xem tất cả",listProduct));
                listCategory.add(new Category("Combo","Xem tất cả",listProduct));

                categoryAdapter.setData(listCategory);
                rcvCategory_allproducts.setAdapter(categoryAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
*/

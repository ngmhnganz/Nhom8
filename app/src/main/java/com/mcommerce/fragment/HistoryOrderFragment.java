package com.mcommerce.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
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
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class HistoryOrderFragment extends Fragment {

    TextView txtDate_fragmentHistoryOrder;
    View view;
    MaterialDatePicker materialDatePicker;
    RecyclerView rcv_fragmentHistoryOrder;

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

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();

        //region Lấy dữ liệu Order từ Firebase
        ArrayList<OrderModel> orderLists = new ArrayList<>();

        Query query = myRef.child("DonHang");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OrderModel order = new OrderModel();

                    order.setStatusOrder(dataSnapshot.child("statusOrder").getValue().toString());
                    order.setPriceOrder(dataSnapshot.child("priceOrder").getValue().toString());
                    order.setDateOrder(dataSnapshot.child("dateOrder").getValue().toString());
                    order.setIdOrder(dataSnapshot.child("idOrder").getValue().toString());
                    order.setPaymentOrder(dataSnapshot.child("paymentOrder").getValue().toString());
                    order.setAddOrder(dataSnapshot.child("addOrder").getValue().toString());
                    order.setImgOrder(dataSnapshot.child("imgOrder").getValue().toString());

                    HashMap<String,String> alt = (HashMap<String, String>) dataSnapshot.child("itemOrder").getValue();
                    order.setItemOrder(alt);
                    orderLists.add(order);
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                rcv_fragmentHistoryOrder.setLayoutManager(linearLayoutManager);
                OrderAdapter adapter = new OrderAdapter(getContext(),R.layout.layout_history_order_item,orderLists);
                rcv_fragmentHistoryOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion
    }


    private void addEvent() {

        //region Xử lý chọn ngày
        materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),MaterialDatePicker.todayInUtcMilliseconds())).build();

        txtDate_fragmentHistoryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DateRangePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        txtDate_fragmentHistoryOrder.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });
        //endregion

    }

    private void linkview() {
        txtDate_fragmentHistoryOrder = view.findViewById(R.id.txtDate_fragmentHistoryOrder);
        rcv_fragmentHistoryOrder = view.findViewById(R.id.rcv_fragmentHistoryOrder);
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

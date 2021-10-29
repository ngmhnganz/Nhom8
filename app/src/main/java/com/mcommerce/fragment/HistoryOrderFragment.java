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

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.List;
import java.util.Queue;

public class HistoryOrderFragment extends Fragment {

    TextView txtDate_fragmentHistoryOrder;
    View view;
    MaterialDatePicker materialDatePicker;
    List<Product> productList;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase =firebaseDatabase.getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history_order,container,false);

        linkview();
        addEvent();



      


        return view;



    }



    private void addEvent() {
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
    }

    private void linkview() {
        txtDate_fragmentHistoryOrder = view.findViewById(R.id.txtDate_fragmentHistoryOrder);
    }

}
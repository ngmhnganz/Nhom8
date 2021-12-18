package com.mcommerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.auth.LoginActivity;

public class OrderFragment extends Fragment {

    private View view;
    private TextView txtDangNhap;
    private Button btnComingOrder, btnHistoryOder;
    private ImageButton btnBack, btnCart;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order,container,false);
        linkview();

        if (user != null){
            loadFragment(new ComingOrderFragment());
            txtDangNhap.setVisibility(View.GONE);
        }
        else {
            txtDangNhap.setVisibility(View.VISIBLE);
        }

        addEvent();
        return view;
    }

    private void addEvent() {
        if (user!= null){
            btnHistoryOder.setOnClickListener(myClick);
            btnComingOrder.setOnClickListener(myClick);
        }
        txtDangNhap.setOnClickListener(v ->  {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        });
    }

    private void linkview() {
        btnBack = view.findViewById(R.id.btnBack_orderactivity);
        btnCart = view.findViewById(R.id.btnCart_orderactivity);
        btnComingOrder = view.findViewById(R.id.btnComingOrder_orderactivity);
        btnHistoryOder = view.findViewById(R.id.btnHistoryOrder_orderactivity);

        btnComingOrder.setEnabled(false);
        btnHistoryOder.setEnabled(true);
        btnComingOrder.setBackgroundResource(R.drawable.button_underline);

        txtDangNhap = view.findViewById(R.id.txtDangNhap);
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ( view.getId()==R.id.btnHistoryOrder_orderactivity){
                loadFragment(new HistoryOrderFragment());
                btnHistoryOder.setEnabled(false);
                btnComingOrder.setEnabled(true);
                btnHistoryOder.setBackgroundResource(R.drawable.button_underline);
                btnComingOrder.setBackgroundResource(R.color.white);

            } else if (view.getId()==R.id.btnComingOrder_orderactivity){

                loadFragment(new ComingOrderFragment());
                btnComingOrder.setEnabled(false);
                btnHistoryOder.setEnabled(true);
                btnComingOrder.setBackgroundResource(R.drawable.button_underline);
                btnHistoryOder.setBackgroundResource(R.color.white);
            }
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.containerOrderLists_orderactivity, fragment);
        transaction.commit();
    }
}

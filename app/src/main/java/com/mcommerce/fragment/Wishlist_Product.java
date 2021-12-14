package com.mcommerce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.WishProductAdapter;
import com.mcommerce.nhom8.R;

import java.util.HashMap;
import java.util.Map;

public class Wishlist_Product extends Fragment {
    RecyclerView rcv_wish_product;
    View view;
    DatabaseReference LikeRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wishlist_product,container,false);
        linkViews();
        loadData();
        return view;
    }

    private void linkViews() {
        rcv_wish_product = view.findViewById(R.id.rcv_wish_product);
        rcv_wish_product.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

    }

    //Nạp data...
    private void loadData() {
        if (user == null) {
            return;
        } else {
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.show();
            LikeRef.child("User").child(user.getUid()).child("userLikeProduct").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String, HashMap<String,?>> wishList = (Map<String, HashMap<String, ?>>) snapshot.getValue();
                   WishProductAdapter adapter = new WishProductAdapter(getContext(),wishList,R.layout.item_wishproduct);
                   progressDialog.dismiss();
                   rcv_wish_product.setAdapter(adapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}

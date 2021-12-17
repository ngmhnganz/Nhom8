package com.mcommerce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.adapter.WishRecipeAdapter;
import com.mcommerce.nhom8.R;

import java.util.HashMap;
import java.util.Map;

public class Wishlist_Recipe extends Fragment {
    RecyclerView rcv_WishR;
    View view;
    ImageView imvEmptyList;
    DatabaseReference LikeRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_wishlist_recipe,container,false);
        linkViews();
        loadData();
        return view;
    }

    private void linkViews() {
        rcv_WishR = view.findViewById(R.id.rcv_WishR);
        rcv_WishR.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        imvEmptyList = view.findViewById(R.id.imvEmptyList);

    }

    private void loadData() {
        if (user == null) {
            return;
        } else {
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.show();
            LikeRef.child("User").child(user.getUid()).child("userLikeRecipe").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue()!=null){
                        imvEmptyList.setVisibility(View.GONE);
                        Map<String, HashMap<String,?>> wishListR = (Map<String, HashMap<String, ?>>) snapshot.getValue();
                        WishRecipeAdapter adapter = new WishRecipeAdapter(getContext(),wishListR,R.layout.item_wishrecipe);
                        rcv_WishR.setAdapter(adapter);
                    } else{
                        imvEmptyList.setVisibility(View.VISIBLE);
                    }
                    progressDialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
package com.mcommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.interfaces.RecyclerViewItemClickListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.product.ProductDetailActivity;
import com.mcommerce.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishProductAdapter extends  RecyclerView.Adapter<WishProductAdapter.WishViewHolder>{

    private final Context context;
    private final Map<String, HashMap<String,?>> wishList;
    private final int item_layout;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    List<String> wishIDs;

    public WishProductAdapter(Context context, Map<String, HashMap<String, ?>> wishList, int item_layout) {
        this.context = context;
        this.wishList = wishList;
        this.item_layout = item_layout;
        wishIDs = new ArrayList<>(wishList.keySet());
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent,false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        String key = wishIDs.get(position);
        long productID = (long) wishList.get(key).get("id");
        ref.child("NguyenLieu").child(String.valueOf(productID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                Glide.with(context).load(product.getProductImg()).into(holder.imvProduct_Wish);
                holder.txtName_WishP.setText(product.getProductName());
                holder.txtPrice_WishP.setText(product.getProductPrice()+" đ");
                holder.txtLike_WishP.setText(product.getProductLike()+" người đã thích");
                holder.chkLike_WL.setChecked(true);
                holder.chkLike_WL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (holder.chkLike_WL.isChecked()==false){
                            ref.child("User").child(user.getUid()).child("userLikeProduct").child(wishIDs.get(holder.getBindingAdapterPosition())).removeValue();
                            wishList.remove(wishIDs.get(holder.getBindingAdapterPosition()));
                            wishIDs.remove(wishIDs.get(holder.getBindingAdapterPosition()));
                            notifyItemRemoved(holder.getBindingAdapterPosition());
                        }

                    }
                });

                holder.setItemClickListener(new RecyclerViewItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Constant.SELECTED_PRODUCTED,product);
                        intent.putExtra(Constant.PRODUCT_BUNDLE, bundle);
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class WishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvProduct_Wish;
        TextView txtName_WishP, txtLike_WishP,txtPrice_WishP;
        CheckBox chkLike_WL;
        private RecyclerViewItemClickListener itemClickListener;

        public WishViewHolder(@NonNull View itemView) {
            super(itemView);
            chkLike_WL=itemView.findViewById(R.id.chkLike_WL);
            imvProduct_Wish=itemView.findViewById(R.id.imvProduct_Wish);
            txtName_WishP=itemView.findViewById(R.id.txtName_WishP);
            txtLike_WishP=itemView.findViewById(R.id.txtLike_WishP);
            txtPrice_WishP=itemView.findViewById(R.id.txtPrice_WishP);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(RecyclerViewItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}

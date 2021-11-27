package com.mcommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.HashMap;

public class OrderDetailAdapter  extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private Activity context;
    private HashMap<String,Integer> itemsOrder;
    private DatabaseReference myRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private String[] key; // Mãng lưu các ID của sản phẩm


    public OrderDetailAdapter(Activity context, HashMap<String,Integer> itemsOrder) {
        this.context = context;
        this.itemsOrder = itemsOrder;
        key = this.itemsOrder.keySet().toArray(new String[itemsOrder.size()]);
    }


    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_orderdetail_layout,parent,false);
        return new OrderDetailViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        myRef = firebaseDatabase.getReference().child("NguyenLieu");
        String itemID = key[position];
        int itemQ =  Integer.parseInt(String.valueOf(itemsOrder.get(key[position])));
        myRef.child(itemID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = new Product();
                product.setProductName(snapshot.child("productName").getValue().toString());
                product.setProductPrice(((Long) snapshot.child("productPrice").getValue()).intValue());
                holder.txtProductName_orderdetail.setText(product.getProductName());
                holder.txtProductPrice_orderdetail.setText(String.valueOf(product.getProductPrice()*itemQ)+" đ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        holder.txtProductQuantity_orderdetail.setText(itemQ+"x");
    }


    @Override
    public int getItemCount() {
        return itemsOrder.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        TextView    txtProductQuantity_orderdetail,
                    txtProductName_orderdetail,
                    txtProductPrice_orderdetail;
        public OrderDetailViewHolder(View view) {
            super(view);
            txtProductQuantity_orderdetail = view.findViewById(R.id.txtProductQuantity_orderdetail);
            txtProductName_orderdetail = view.findViewById(R.id.txtProductName_orderdetail);
            txtProductPrice_orderdetail = view.findViewById(R.id.txtProductPrice_orderdetail);
        }
    }
}

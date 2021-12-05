package com.mcommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailAdapter  extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private Activity context;
    private HashMap<String,HashMap<String, ?>> itemsOrder;
    private DatabaseReference myRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final ArrayList<String> productIDs;

    private String itemID;


    public OrderDetailAdapter(Activity context, HashMap<String,HashMap<String, ?>> itemsOrder) {
        this.context = context;
        this.itemsOrder = itemsOrder;
        productIDs = new ArrayList<>(itemsOrder.keySet());
    }


    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_orderdetail_layout,parent,false);
        return new OrderDetailViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        itemID = productIDs.get(position);
        long price, quantity;
        price = (long) itemsOrder.get(itemID).get("price");
        quantity = (long) itemsOrder.get(itemID).get("quantity");
        holder.txtProductName_orderdetail.setText(itemsOrder.get(itemID).get("name").toString());
        holder.txtProductPrice_orderdetail.setText(price*quantity+ " đ");
        holder.txtProductQuantity_orderdetail.setText(quantity+"x");

//        myRef = firebaseDatabase.getReference().child("NguyenLieu");
//        String itemID = key[position];
//        int itemQ =  Integer.parseInt(String.valueOf(itemsOrder.get(key[position])));
//        myRef.child(itemID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Product product = new Product();
//                product.setProductName(snapshot.child("productName").getValue().toString());
//                product.setProductPrice(((Long) snapshot.child("productPrice").getValue()).intValue());
//                holder.txtProductName_orderdetail.setText(product.getProductName());
//                holder.txtProductPrice_orderdetail.setText(String.valueOf(product.getProductPrice()*itemQ)+" đ");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        holder.txtProductQuantity_orderdetail.setText(itemQ+"x");
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
            txtProductQuantity_orderdetail = view.findViewById(R.id.txtProductQuantity);
            txtProductName_orderdetail = view.findViewById(R.id.txtProductName);
            txtProductPrice_orderdetail = view.findViewById(R.id.txtProductPrice);
        }
    }
}

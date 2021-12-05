package com.mcommerce.adapter;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final Map<String, HashMap<String,?>> cartList;
    private final int item_layout;
    private DatabaseReference myRef;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final ArrayList<String> productIDs;
    private String itemID;
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public CartAdapter(Context context, Map<String, HashMap<String,?>> cartList, int item_layout) {
        this.context = context;
        this.cartList = cartList;
        this.item_layout = item_layout;
        productIDs = new ArrayList<>(cartList.keySet());
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_layout,parent,false);
        return new CartViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        myRef = firebaseDatabase.getReference();
        itemID = productIDs.get(position);
        long itemQ = (long) cartList.get(itemID).get("quantity");
        switch (item_layout) {
            case R.layout.layout_cart_item: {
                myRef.child("NguyenLieu").child(itemID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.txtSanPham_lyCart.setText(snapshot.child("productName").getValue().toString());
                        holder.txtAmount_lyCart.setText(( (Long)snapshot.child("productPrice").getValue() ).intValue()*itemQ+"VND");
                        holder.txtQuantity_lyCart.setText(itemQ+"");
                        Glide.with(context).load(snapshot.child("productImg").getValue().toString()).into(holder.imv_lyCart);

                        holder.txtQuantity_lyCart.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (holder.txtQuantity_lyCart.getText().toString().equals("0")) {
                                    holder.btn_decrease_lyCart.setEnabled(false);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Sản phẩm sẽ bị xóa khỏi giỏ hàng của bạn");
                                    builder.setNegativeButton("Hủy", (dialog, v) -> {
                                        holder.txtAmount_lyCart.setText(snapshot.child("productPrice").getValue().toString()+"VND");
                                        holder.txtQuantity_lyCart.setText("1");
                                    });
                                    builder.setPositiveButton("Xóa", (dialog, v) -> {
                                        myRef.child("User").child(user.getUid()).child("userCart").child(productIDs.get(holder.getBindingAdapterPosition())).removeValue();
                                        cartList.remove(productIDs.get(holder.getBindingAdapterPosition()));
                                        productIDs.remove(productIDs.get(holder.getBindingAdapterPosition()));
                                        notifyItemRemoved(holder.getBindingAdapterPosition());
                                    });
                                    builder.create().show();
                                } else {

                                    holder.btn_decrease_lyCart.setEnabled(true);
                                    long quantity = Long.parseLong(holder.txtQuantity_lyCart.getText().toString());
                                    holder.txtAmount_lyCart.setText( (Long)snapshot.child("productPrice").getValue()*quantity+"VND");
                                    myRef.child("User").child(user.getUid()).child("userCart").child(productIDs.get(holder.getBindingAdapterPosition())).child("quantity").setValue(quantity);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                        holder.btn_decrease_lyCart.setOnClickListener(v-> {
                            int currentQuantity = Integer.parseInt(holder.txtQuantity_lyCart.getText().toString());
                            holder.txtQuantity_lyCart.setText(String.valueOf(currentQuantity-1));
                        });

                        holder.btn_increase_lyCart.setOnClickListener(v-> {
                            int currentQuantity = Integer.parseInt(holder.txtQuantity_lyCart.getText().toString());
                            holder.txtQuantity_lyCart.setText(String.valueOf(currentQuantity+1));
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            }
            case R.layout.item_product_orderdetail_layout:{
                holder.txtProductName.setText(cartList.get(itemID).get("name").toString());
                holder.txtProductPrice.setText(cartList.get(itemID).get("price").toString());
                holder.txtProductQuantity.setText(cartList.get(itemID).get("quantity").toString());
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView    txtSanPham_lyCart,
                    txtAmount_lyCart,
                    txtQuantity_lyCart,
                txtProductQuantity,
                txtProductName,
                txtProductPrice;
        Button      btn_decrease_lyCart,
                    btn_increase_lyCart;
        ImageView   imv_lyCart;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (item_layout){
                case R.layout.layout_cart_item: {
                    txtSanPham_lyCart = itemView.findViewById(R.id.txtSanPham_lyCart);
                    txtAmount_lyCart = itemView.findViewById(R.id.txtAmount_lyCart);

                    txtQuantity_lyCart = itemView.findViewById(R.id.txtQuantity_lyCart);
                    btn_decrease_lyCart = itemView.findViewById(R.id.btn_decrease_lyCart);

                    btn_increase_lyCart = itemView.findViewById(R.id.btn_increase_lyCart);
                    imv_lyCart = itemView.findViewById(R.id.imv_lyCart);
                    break;
                }
                case R.layout.item_product_orderdetail_layout:{
                    txtProductQuantity = itemView.findViewById(R.id.txtProductQuantity);
                    txtProductName = itemView.findViewById(R.id.txtProductName);
                    txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
                    break;
                }
            }


        }
    }
}

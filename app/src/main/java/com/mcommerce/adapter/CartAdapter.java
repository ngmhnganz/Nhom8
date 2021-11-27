package com.mcommerce.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private Map<String, Integer> cartList;
    private DatabaseReference myRef;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private ArrayList<String> productIDs;
    private String itemID;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public CartAdapter(Context context, Map<String, Integer> cartList) {
        this.context = context;
        this.cartList = cartList;
        productIDs = new ArrayList<String>(cartList.keySet());
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item,parent,false)
;        return new CartViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        myRef = firebaseDatabase.getReference();
        itemID = productIDs.get(position);
        int itemQ = Integer.parseInt(String.valueOf(cartList.get(itemID)));
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
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.txtQuantity_lyCart.setText("1");
                        }
                    });
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myRef.child("User").child(user.getUid()).child("userCart").child(productIDs.get(holder.getBindingAdapterPosition())).removeValue();
                            cartList.remove(productIDs.get(holder.getBindingAdapterPosition()));
                            productIDs.remove(productIDs.get(holder.getBindingAdapterPosition()));
                            notifyItemRemoved(holder.getBindingAdapterPosition());
                        }
                    });
                    builder.create().show();
                        } else {
                            holder.btn_decrease_lyCart.setEnabled(true);
                            myRef.child("User").child(user.getUid()).child("userCart").child(productIDs.get(holder.getBindingAdapterPosition())).setValue(holder.txtQuantity_lyCart.getText().toString());
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



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView    txtSanPham_lyCart,
                    txtAmount_lyCart,
                    txtQuantity_lyCart;
        Button      btn_decrease_lyCart,
                    btn_increase_lyCart;
        ImageView   imv_lyCart;

        public CartViewHolder(@NonNull View itemView) {

            super(itemView);

            txtSanPham_lyCart = itemView.findViewById(R.id.txtSanPham_lyCart);
            txtAmount_lyCart = itemView.findViewById(R.id.txtAmount_lyCart);

            txtQuantity_lyCart = itemView.findViewById(R.id.txtQuantity_lyCart);
            btn_decrease_lyCart = itemView.findViewById(R.id.btn_decrease_lyCart);

            btn_increase_lyCart = itemView.findViewById(R.id.btn_increase_lyCart);
            imv_lyCart = itemView.findViewById(R.id.imv_lyCart);

        }
    }
}

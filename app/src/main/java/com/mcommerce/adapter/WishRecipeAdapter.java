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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.interfaces.RecyclerViewItemClickListener;
import com.mcommerce.model.Product;
import com.mcommerce.model.Recipe;
import com.mcommerce.model.User;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.recipe.EachRecipeActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishRecipeAdapter extends  RecyclerView.Adapter<WishRecipeAdapter.WishViewHolder>{

    private final Context context;
    private final Map<String, HashMap<String,?>> wishListR;
    private final int item_layout;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    List<String> wishID;

    public WishRecipeAdapter(Context context, Map<String, HashMap<String, ?>> wishList, int item_layout) {
        this.context = context;
        this.wishListR = wishList;
        this.item_layout = item_layout;
        wishID = new ArrayList<>(wishList.keySet());
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent,false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        String key = wishID.get(position);
        String recipeID = (String) wishListR.get(key).get("id");
        ref.child(Key.RECIPE).child(String.valueOf(recipeID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recipe recipe = snapshot.getValue(Recipe.class);
                Glide.with(context).load(recipe.getRecipeImage()).into(holder.imv_WishR);
                holder.txtName_WishR.setText(recipe.getRecipeName());
                holder.txtDes_WishR.setText(recipe.getRecipeDescription());
                holder.txtTime_WishR.setText(recipe.getRecipeTime()+" phút");
                holder.chkLike_WL.setChecked(true);
                holder.chkLike_WL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!holder.chkLike_WL.isChecked()){
                            ref.child(Key.USER).child(user.getUid()).child(User.LikeRecipe).child(wishID.get(holder.getBindingAdapterPosition())).removeValue();
                            ref.child(Key.RECIPE).child(wishID.get(holder.getBindingAdapterPosition())).child(Recipe.Like).setValue(ServerValue.increment(-1));
                            wishListR.remove(wishID.get(holder.getBindingAdapterPosition()));
                            wishID.remove(wishID.get(holder.getBindingAdapterPosition()));
                            notifyItemRemoved(holder.getBindingAdapterPosition());
                        }
                    }
                });
                holder.setItemClickListener((view, position1) -> {
                    Intent intent = new Intent(context, EachRecipeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constant.SECLECTED_RECIPE,recipe);
                    intent.putExtra(Constant.RECIPE_BUNDLE, bundle);
                    bundle.putSerializable(Constant.ITEMS_INGREDIENT, (Serializable) recipe.getRecipeIngredient());
                    context.startActivity(intent);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return wishListR.size();
    }

    public class WishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imv_WishR;
        TextView txtName_WishR, txtTime_WishR,txtDes_WishR;
        CheckBox chkLike_WL;
        private RecyclerViewItemClickListener itemClickListener;

        public WishViewHolder(@NonNull View itemView) {
            super(itemView);
            chkLike_WL=itemView.findViewById(R.id.chkLike_WL);
            imv_WishR=itemView.findViewById(R.id.imv_WishR);
            txtName_WishR =itemView.findViewById(R.id.txtName_WishR);
            txtTime_WishR =itemView.findViewById(R.id.txtTime_WishR);
            txtDes_WishR=itemView.findViewById(R.id.txtDes_WishR);
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


package com.mcommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.SuggestRecipeActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipeMaterialAdapterRCV extends RecyclerView.Adapter<RecipeMaterialAdapterRCV.ViewHolder>{

    Context context;
    ArrayList<Product> materials;

    public RecipeMaterialAdapterRCV(Context context, ArrayList<Product> materials) {
        this.context = context;
        this.materials = materials;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //nạp giao diện thông qua layout inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_recipe_material_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //biding dữ liệu
        holder.txtRecipeMaterialName.setText(materials.get(position).getProductName());

//        //flexGrow works like the weight attribute in horizontal LinearLayouts
//        ViewGroup.LayoutParams layoutParams=holder.txtRecipeMaterialName.getLayoutParams();
//        if(layoutParams instanceof FlexboxLayoutManager.LayoutParams){
//            FlexboxLayoutManager.LayoutParams flexboxLp= (FlexboxLayoutManager.LayoutParams) holder.txtRecipeMaterialName.getLayoutParams();
//            flexboxLp.setFlexGrow(1.0f);
//        }
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtRecipeMaterialName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRecipeMaterialName=itemView.findViewById(R.id.txtRecipeMaterial);

        }
    }

}

package com.mcommerce.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.nhom8.R;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends RecyclerView.ViewHolder {

    View view;

    public ProductAdapter(@NonNull View itemView) {
        super(itemView);

        view=itemView;
    }

    public void setdetails(Context context,String name, String image, int price ){
        TextView txtProductPrice_allproducts = view.findViewById(R.id.txtProductPrice_allproducts);
        TextView txtProductName_allproducts = view.findViewById(R.id.txtProductName_allproducts);
        ImageView imv_allproducts = view.findViewById(R.id.imv_allproducts);

        txtProductName_allproducts.setText(name);
        txtProductPrice_allproducts.setText(String.valueOf(price));
        Picasso.get().load(image).into(imv_allproducts);
    }
}


/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;

    public productAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public productAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_product_allproducts_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.MyViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        holder.txtProductName_allproducts.setText(product.productName);
        holder.txtProductPrice_allproducts.setText(String.valueOf(product.productPrice));
        holder.imv_allproducts.set

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtProductName_allproducts, txtProductPrice_allproducts;
        ImageView imv_allproducts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName_allproducts = itemView.findViewById(R.id.txtProductName_allproducts);
            txtProductPrice_allproducts = itemView.findViewById(R.id.txtProductPrice_allproducts);
            imv_allproducts= itemView.findViewById(R.id.imv_allproducts);
        }
    }
}

*/

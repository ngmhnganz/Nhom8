package com.mcommerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    public List<Product> productList;
    public void setData(List<Product> list)
    {
        this.productList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_allproducts_layout,parent,false);
        return new ProductViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product== null) {
            return ;
        }
        holder.imvHinh_allproducts.setImageResource(product.getProductImg());
        holder.txtProductName_allproducts.setText(product.getProductName());
        holder.txtProductPrice_allproducts.setText(product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        if(productList != null)
        {
            return productList.size();
        }
        return 0;


    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public ImageView imvHinh_allproducts;
        public TextView txtProductPrice_allproducts, txtProductName_allproducts;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh_allproducts = itemView.findViewById(R.id.imvHinh_allproducts);
            txtProductName_allproducts = itemView.findViewById(R.id.txtProductName_allproducts);
            txtProductPrice_allproducts = itemView.findViewById(R.id.txtProductPrice_allproducts);
        }
    }
}

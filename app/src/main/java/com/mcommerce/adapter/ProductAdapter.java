package com.mcommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mcommerce.interfaces.RecyclerViewItemClickListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.ProductDetailActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context context;
    private List<Product> productList;

    public void setData(Context context, List<Product> list)
    {
        this.context = context;
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
        Glide.with(context).load(product.getProductImg()).into(holder.imvHinh_allproducts);

        holder.txtProductName_allproducts.setText(product.getProductName());
        holder.txtProductPrice_allproducts.setText(String.valueOf(product.getProductPrice()));
        holder.cvitem_allproducts.setLayoutParams(marginValue(holder));

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
    public int getItemCount() {
        if(productList != null)
        {
            return productList.size();
        }
        return 0;


    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imvHinh_allproducts;
        TextView txtProductPrice_allproducts, txtProductName_allproducts;
        CardView cvitem_allproducts;

        private RecyclerViewItemClickListener itemClickListener;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinh_allproducts = itemView.findViewById(R.id.imvHinh_allproducts);
            txtProductName_allproducts = itemView.findViewById(R.id.txtProductName_allproducts);
            txtProductPrice_allproducts = itemView.findViewById(R.id.txtProductPrice_allproducts);
            cvitem_allproducts = itemView.findViewById(R.id.cvitem_allproducts);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(RecyclerViewItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

    private ViewGroup.MarginLayoutParams marginValue(ProductAdapter.ProductViewHolder viewHolder){
        float elevation = viewHolder.cvitem_allproducts.getMaxCardElevation();
        float radius = viewHolder.cvitem_allproducts.getRadius();
        double cos45 = Math.cos(Math.toRadians(45));

        int horizontalPadding = (int) (elevation + (1 - cos45) * radius);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewHolder.cvitem_allproducts.getLayoutParams();
        params.rightMargin = -horizontalPadding;
        params.topMargin = -horizontalPadding;
        return params;
    }
}

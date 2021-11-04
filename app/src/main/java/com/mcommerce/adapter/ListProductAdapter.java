package com.mcommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.List;

public class ListProductAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Product> productList;

    public ListProductAdapter(Activity context, int item_layout, List<Product> productList) {
        this.context = context;
        this.item_layout = item_layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(item_layout,null);
            holder.imvProduct=view.findViewById(R.id.imv_product_listProduct);
            holder.imvbgProduct=view.findViewById(R.id.imvbgProduct_listProduct);
            holder.imvAdd=view.findViewById(R.id.imvAdd_listProduct);
            holder.txtName=view.findViewById(R.id.txtproductName_listProduct);
            holder.txtLike=view.findViewById(R.id.txtLike_listProduct);
            holder.txtPrice=view.findViewById(R.id.txtProductPrice_listProduct);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }


        //biding data
        Product product=productList.get(i);
        holder.imvProduct.setImageResource(product.getProductImg());
        holder.imvbgProduct.setImageResource(product.getProductBackgroundImg());
        holder.imvAdd.setImageResource(product.getProductAddItem());
        holder.txtName.setText(product.getProductName());
        holder.txtLike.setText(String.valueOf(product.getProductLike())+" anh em đã thích");
        holder.txtPrice.setText(String.valueOf(product.getProductPrice()));
        return view;
    }

    public static class ViewHolder{
        ImageView imvProduct,imvbgProduct,imvAdd;
        TextView txtName,txtPrice,txtLike;
    }
}

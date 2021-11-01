package com.mcommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.RecipeActivity;

import java.util.List;

public class RecipeMaterialAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Product> materialList;

    public RecipeMaterialAdapter(Activity context, int item_layout, List<Product> productList) {
        this.context = context;
        this.item_layout = item_layout;
        this.materialList = productList;
    }

    @Override
    public int getCount() {
        return materialList.size();
    }

    @Override
    public Object getItem(int i) {
        return materialList.get(i);
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
            view=inflater.inflate(item_layout, null);

            holder.txtMaterials=view.findViewById(R.id.txtRecipeMaterial);
            holder.imvMaterialBackGround=view.findViewById(R.id.imvRecipeMaterialBackground);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }


        //biding data
        Product product=materialList.get(i);
        holder.txtMaterials.setText(product.getProductName());
        holder.imvMaterialBackGround.setImageResource(product.getProductBackgroundImg());

        return view;
    }

    public static class ViewHolder{
        TextView txtMaterials;
        ImageView imvMaterialBackGround;
    }
}

package com.mcommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.Category;
import com.mcommerce.nhom8.R;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.CategoryViewHolder> {

    public Context context;
    public List<Category> categoryList;

    public categoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Category> list)
    {
        this.categoryList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_allproducts_layout,parent,false);

        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if(category == null)
        {
            return;
        }

        holder.txtNameCategory_allproducts.setText(category.getCategoryName());
        holder.txtSeeMore_allproducts.setText("Xem tất cả");
        holder.rcvCategory_allproducts.;

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.se(category.getProducts());


    }

    @Override
    public int getItemCount() {
        if(categoryList != null)
        {
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNameCategory_allproducts;
        public TextView txtSeeMore_allproducts;
        public RecyclerView rcvCategory_allproducts;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameCategory_allproducts = itemView.findViewById(R.id.txtNameCategory_allproducts);
            txtSeeMore_allproducts = itemView.findViewById(R.id.txtSeeMore_allproducts);
            rcvCategory_allproducts  = itemView.findViewById(R.id.rcvCategory_allproducts);
        }
    }
}

package com.mcommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.Category;
import com.mcommerce.nhom8.ListProductActivity;
import com.mcommerce.nhom8.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context) {
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
        holder.txtSeeMore_allproducts.setText(category.getCategotySeeMore());

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcvProduct_allproducts.setLayoutManager(linearLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setData(category.getProducts());
        holder.rcvProduct_allproducts.setAdapter(productAdapter);

        holder.txtSeeMore_allproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListProduct=new Intent(context, ListProductActivity.class);
                 context.startActivity(intentListProduct);
            }
        });


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

        TextView txtNameCategory_allproducts;
        TextView txtSeeMore_allproducts;
        RecyclerView rcvProduct_allproducts;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameCategory_allproducts = itemView.findViewById(R.id.txtNameCategory_allproducts);
            txtSeeMore_allproducts = itemView.findViewById(R.id.txtSeeMore_allproducts);
            rcvProduct_allproducts  = itemView.findViewById(R.id.rcvProduct_allproducts);
        }
    }
}

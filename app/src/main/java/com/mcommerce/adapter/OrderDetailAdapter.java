package com.mcommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.HashMap;
import java.util.List;

public class OrderDetailAdapter  extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private Context context;
    private List<OrderModel> orderList;
    private List<Product> productList;

    public OrderDetailAdapter(Context context, List<OrderModel> orderModelList, List<Product> productList) {
        this.context = context;
        this.orderList = orderModelList;
        this.productList = productList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_orderdetail_layout,parent,false);
        return new OrderDetailViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        Product product =productList.get(position);
        OrderModel order = orderList.get(position);

        if(product==null && order==null)
        {
            return;
        }

        holder.txtProductPrice_orderdetail.setText(String.valueOf(product.getProductPrice()));
        holder.txtProductName_orderdetail.setText(product.getProductName());
        // chỗ này nghi sai
        holder.txtProductQuantity_orderdetail.setText(order.getItemOrder().get(""));



    }



    @Override
    public int getItemCount() {
        if(productList != null)
        {
            return productList.size();
        }
        return 0;
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        TextView    txtProductQuantity_orderdetail,
                    txtProductName_orderdetail,
                    txtProductPrice_orderdetail;
        public OrderDetailViewHolder(View view) {
            super(view);
            txtProductQuantity_orderdetail = view.findViewById(R.id.txtProductQuantity_orderdetail);
            txtProductName_orderdetail = view.findViewById(R.id.txtProductName_orderdetail);
            txtProductPrice_orderdetail = view.findViewById(R.id.txtProductPrice_orderdetail);
        }
    }
}

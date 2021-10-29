package com.mcommerce.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OderViewHolder> {

    private List<OrderModel> orderList;

    public void setData(List<OrderModel> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history_order_item,parent,false);

        return new OderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderViewHolder holder, int position) {
        OrderModel order = orderList.get(position);

        if (order == null){
            return;
        }

        List<Product> products = order.getItemOrder();
        Product product = products.get(1);

        holder.imv_iHistoryOrder.setImageResource(product.getProductImg());
        holder.txtID_iHistoryOrder.setText(order.getIdOrder());
        holder.txtDes_iHistoryOrder.setText(order.getPriceOrder()+" "+String.valueOf(products.size())+"sản phẩm "+ order.getPaymentOrder());
        holder.txtDate_iHistoryOrder.setText(order.getDateOrder());
        holder.txtAddress_iHistoryOrder.setText(order.getAddOrder());

        switch(order.getStatusOrder()) {
            case OrderModel.THANH_CONG:
                holder.txtStatus_iHistoryOrder.setText("Hoàn thành");
                holder.imvStatus_iHistoryOrder.setImageResource(R.drawable.ic_check_circle_fill);
                break;
            case OrderModel.DA_HUY:
                holder.txtStatus_iHistoryOrder.setText("Đã hủy");
                holder.imvStatus_iHistoryOrder.setImageResource(R.drawable.ic_x_circle_fill);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OderViewHolder extends RecyclerView.ViewHolder {

        TextView txtID_iHistoryOrder, txtDate_iHistoryOrder, txtAddress_iHistoryOrder, txtDes_iHistoryOrder, txtStatus_iHistoryOrder;
        ImageView imv_iHistoryOrder, imvStatus_iHistoryOrder;
        Button btnDatLai_iHistoryOrder;

        public OderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddress_iHistoryOrder = itemView.findViewById(R.id.txtAddress_iHistoryOrder);
            txtDate_iHistoryOrder = itemView.findViewById(R.id.txtDate_iHistoryOrder);
            txtDes_iHistoryOrder = itemView.findViewById(R.id.txtDes_iHistoryOrder);
            txtID_iHistoryOrder = itemView.findViewById(R.id.txtID_iHistoryOrder);
            txtStatus_iHistoryOrder = itemView.findViewById(R.id.txtStatus_iHistoryOrder);

            imv_iHistoryOrder = itemView.findViewById(R.id.imv_iHistoryOrder);
            imvStatus_iHistoryOrder = itemView.findViewById(R.id.imvStatus_iHistoryOrder);

            btnDatLai_iHistoryOrder = itemView.findViewById(R.id.btnDatLai_iHistoryOrder);


        }
    }
}
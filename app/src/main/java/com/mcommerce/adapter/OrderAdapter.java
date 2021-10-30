package com.mcommerce.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mcommerce.model.OrderModel;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OderViewHolder> {

    private Context context;
    int item_layout;
    private List<OrderModel> orderList;

    public OrderAdapter(Context context, int item_layout, List<OrderModel> orderList) {
        this.context = context;
        this.item_layout = item_layout;
        this.orderList = orderList;
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


        holder.txtID_iHistoryOrder.setText(order.getIdOrder());
        holder.txtDes_iHistoryOrder.setText(String.valueOf(order.getPriceOrder())+" "+String.valueOf(order.getItemOrder().size())+"sản phẩm "+ order.getPaymentOrder());
        holder.txtDate_iHistoryOrder.setText(order.getDateOrder());
        holder.txtAddress_iHistoryOrder.setText(order.getAddOrder());
        Glide.with(context).load(order.getImgOrder()).into(holder.imv_iHistoryOrder);

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
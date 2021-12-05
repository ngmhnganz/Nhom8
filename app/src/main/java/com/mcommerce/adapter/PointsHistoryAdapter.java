package com.mcommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.PointsHistoryModel;
import com.mcommerce.nhom8.R;

import java.util.List;

public class PointsHistoryAdapter extends RecyclerView.Adapter<PointsHistoryAdapter.ViewHolder> {

    Context context;
    List<PointsHistoryModel> pointsHistoryModelList;

    public  PointsHistoryAdapter(Context context, List<PointsHistoryModel> pointsHistoryModelList) {
        this.context = context;
        this. pointsHistoryModelList = pointsHistoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_points_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (pointsHistoryModelList != null && pointsHistoryModelList.size() >0 ){
            PointsHistoryModel model = pointsHistoryModelList.get(position);
            holder.txtID_iPointsHistory.setText(model.getIdOrder());
            holder.txtDate_iPointsHistory.setText(model.getDateOrder());
            holder.txtPoints_iPointsHistory.setText(model.getPoints());
        }else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return pointsHistoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate_iPointsHistory,
                txtID_iPointsHistory,
                txtPoints_iPointsHistory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate_iPointsHistory = itemView.findViewById(R.id.txtDate_iPointsHistory);
            txtID_iPointsHistory = itemView.findViewById(R.id.txtID_iPointsHistory);
            txtPoints_iPointsHistory = itemView.findViewById(R.id.txtPoints_iPointsHistory);
        }
    }
}

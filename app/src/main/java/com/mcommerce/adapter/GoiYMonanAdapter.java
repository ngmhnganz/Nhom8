package com.mcommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.GoiYMonanModel;
import com.mcommerce.nhom8.R;

import java.util.List;

public class GoiYMonanAdapter extends RecyclerView.Adapter<GoiYMonanAdapter.ViewHolder>{

    private List<GoiYMonanModel> goiYMonanModelList;
    private Activity context;

    public GoiYMonanAdapter(List<GoiYMonanModel> goiYMonanModelList, Activity context) {
        this.goiYMonanModelList = goiYMonanModelList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return goiYMonanModelList.size();
    }

    @NonNull
    @Override
    public GoiYMonanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_suggest_recipe,parent,false);
        return new ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView txtTen, txtDes, txtTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imv_LyGoiMonan);
            txtTen = itemView.findViewById(R.id.txtTenMonan_LyGoiYMonan);
            txtDes = itemView.findViewById(R.id.txtDesMonan_LyGoiYMonan);
            txtTime = itemView.findViewById(R.id.txtTimeMonan_LyGoiYMonan);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imv.setImageResource(goiYMonanModelList.get(position).getImgMonan());
        holder.txtDes.setText(goiYMonanModelList.get(position).getDesMonan());
        holder.txtTen.setText(goiYMonanModelList.get(position).getNameMonan());
        holder.txtTime.setText(goiYMonanModelList.get(position).getTimeMonan());

    }
}

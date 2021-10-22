package com.mcommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.GoiYComboModel;
import com.mcommerce.nhom8.R;

import java.util.List;

public class GoiYComboAdapter extends RecyclerView.Adapter<GoiYComboAdapter.ViewHolder> {

    private List<GoiYComboModel> goiYComboModelList;
    private Activity context;

    public GoiYComboAdapter(List<GoiYComboModel> goiYComboModelList, Activity context) {
        this.goiYComboModelList = goiYComboModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public GoiYComboAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.goi_y_combo_layout,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView txtName, txtDes, txtPrice;
        CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imv_LyGoiYCombo);
            txtName = itemView.findViewById(R.id.txtNameCombo_LyGoiYCombo);
            txtDes = itemView.findViewById(R.id.txtDesCombo_LyGoiYCombo);
            txtPrice = itemView.findViewById(R.id.txtPriceCombo_LyGoiYCombo);
            cv = itemView.findViewById(R.id.cv_lyGoiyCombo);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtPrice.setText(goiYComboModelList.get(position).getPriceCombo()+"");
        holder.txtDes.setText(goiYComboModelList.get(position).getDesCombo());
        holder.txtName.setText(goiYComboModelList.get(position).getNameCombo());
        holder.imv.setImageResource(goiYComboModelList.get(position).getImgCombo());
        holder.cv.setLayoutParams(marginValue(holder));
    }

    private ViewGroup.MarginLayoutParams marginValue(ViewHolder viewHolder){
        float elevation = viewHolder.cv.getMaxCardElevation();
        float radius = viewHolder.cv.getRadius();
        double cos45 = Math.cos(Math.toRadians(45));

        int horizontalPadding = (int) (elevation + (1 - cos45) * radius);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewHolder.cv.getLayoutParams();
        params.rightMargin = -horizontalPadding;
        return params;
    }

    @Override
    public int getItemCount() {
        return goiYComboModelList.size();
    }
}

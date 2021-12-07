package com.mcommerce.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mcommerce.model.DiaPhuong;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.List;


public class DiaPhuongAdapter extends ArrayAdapter<DiaPhuong> {

    public DiaPhuongAdapter(@NonNull Context context, int resource, @NonNull List<DiaPhuong> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null ){
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView txt = convertView.findViewById(android.R.id.text1);
        DiaPhuong diaPhuong = getItem(position);
        txt.setText(diaPhuong.toString());
        return super.getView(position, convertView, parent);
    }

}

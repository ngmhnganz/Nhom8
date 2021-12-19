package com.mcommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcommerce.model.Ingredient;
import com.mcommerce.nhom8.R;

import java.util.List;
import java.util.zip.Inflater;

public class SearchIngredientResultAdapter extends BaseAdapter {

    List<Ingredient> ingredientList ;
    Context context;

    public SearchIngredientResultAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ingredientList.get(position).getId();
    }

    private class ViewHolder {
        TextView txt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
            // Locate the TextViews in listview_item.xml
            holder.txt = (TextView) convertView.findViewById((android.R.id.text1));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt.setText(ingredientList.get(position).getShortname());
        return convertView;

    }


}

package com.mcommerce.nhom8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Product;

import java.util.ArrayList;

public class FragmentProductLikeList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_product_likelist,container,false);
        return view;

    }

}
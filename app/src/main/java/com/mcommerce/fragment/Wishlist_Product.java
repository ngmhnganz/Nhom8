package com.mcommerce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mcommerce.nhom8.R;

public class Wishlist_Product extends Fragment {
    CheckBox chkLike_WL;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_wishlist_product,container,false);
        chkLike_WL=view.findViewById(R.id.chkLike_WL);
        return view;
    }
}

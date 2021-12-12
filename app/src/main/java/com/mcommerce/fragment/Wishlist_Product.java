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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.nhom8.R;

public class Wishlist_Product extends Fragment {
    CheckBox chkLike_WL;
    ImageView imvProduct_Wish;
    TextView txtName_WishP, txtLike_WishP,txtPrice_WishP;
    ListView  lvwish_product;
    ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_wishlist_product,container,false);
        chkLike_WL=view.findViewById(R.id.chkLike_WL);
        imvProduct_Wish=view.findViewById(R.id.imvProduct_Wish);
        txtName_WishP=view.findViewById(R.id.imvProduct_Wish);
        txtLike_WishP=view.findViewById(R.id.txtLike_WishP);
        txtPrice_WishP=view.findViewById(R.id.txtPrice_WishP);
        lvwish_product=view.findViewById(R.id.lvwish_product);
        return view;
    }
}

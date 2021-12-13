package com.mcommerce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcommerce.adapter.OrderAdapter;
import com.mcommerce.adapter.ProductAdapter;
import com.mcommerce.model.Order;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.MainActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.nhom8.order.CartActivity;
import com.mcommerce.nhom8.wishlist.WishList;

import java.util.ArrayList;

public class Wishlist_Product extends Fragment {
    CheckBox chkLike_WL;
    ImageView imvProduct_Wish;
    TextView txtName_WishP, txtLike_WishP,txtPrice_WishP;
    RecyclerView rcv_wish_product;
    View view;
    ProductAdapter adapter;
    ArrayList<Product> productLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_wishlist_product,container,false);
        listViews();
        addData();
//        addEvents();
        return view;
    }

    private void listViews() {
        chkLike_WL=view.findViewById(R.id.chkLike_WL);
        imvProduct_Wish=view.findViewById(R.id.imvProduct_Wish);
        txtName_WishP=view.findViewById(R.id.imvProduct_Wish);
        txtLike_WishP=view.findViewById(R.id.txtLike_WishP);
        txtPrice_WishP=view.findViewById(R.id.txtPrice_WishP);
        rcv_wish_product=view.findViewById(R.id.rcv_wish_product);

    }
//Nạp data...
    private void addData() {
//        products=getResources().getStringArray(R.array.)
//        adapter = new ProductAdapter(getContext(),R.layout.item_wishproduct,productLists ,ProductAdapter.WISH);
//        rcv_wish_product.setAdapter(adapter);
    }
// xủ lý nút Tim
//    private void addEvents() {
//        chkLike_WL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
}

package com.mcommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mcommerce.interfaces.RecyclerViewItemClickListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.product.ProductDetailActivity;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public static final int CATEGORY = 1;
    public static final int PRODUCT =2;
    public static final int WISH =3;

    private Context context;
    private List<Product> productList, productListBase;
    private int type;

    public void setData(Context context, List<Product> list, int type)
    {
        this.context = context;
        this.productList = list;
        this.type = type;
        this.productListBase = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (type){
            case CATEGORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_allproducts_layout,parent,false);
                break;
            case PRODUCT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listproduct_layout,parent,false);
                break;
          case WISH:
              view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishproduct,parent,false);
              break;
        }

        return new ProductViewHolder(view) ;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product== null) {
            return ;
        }

        switch (type){
            case PRODUCT:
                Glide.with(context).load(product.getProductImg()).into(holder.imvProduct_listProduct);

                holder.txtproductName_listProduct.setText(product.getProductName());
                holder.txtProductPrice_listProduct.setText(String.valueOf(product.getProductPrice()));
                holder.txtLike_listProduct.setText(product.getProductLike()+" anh em đã thích :))");
                holder.btnAdd_listProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Constant.SELECTED_PRODUCTED,product);
                        intent.putExtra(Constant.PRODUCT_BUNDLE, bundle);
                        context.startActivity(intent);
                    }
                });
                break;

            case CATEGORY:
                Glide.with(context).load(product.getProductImg()).into(holder.imvHinh_allproducts);
                holder.txtProductName_allproducts.setText(product.getProductName());
                holder.txtProductPrice_allproducts.setText(String.valueOf(product.getProductPrice()));
                holder.cvitem_allproducts.setLayoutParams(marginValue(holder));
                break;

            case WISH:
              Glide.with(context).load(product.getProductImg()).into(holder.imvProduct_Wish);
              holder.txtName_WishP.setText(product.getProductName());
              holder.txtPrice_WishP.setText(product.getProductPrice());
              holder.txtLike_WishP.setText(product.getProductLike()+" người đã thích");
              holder.chkLike_WL.isChecked();
                break;
        }

        holder.setItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.SELECTED_PRODUCTED,product);
                intent.putExtra(Constant.PRODUCT_BUNDLE, bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(productList != null)
        {
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewItemClickListener itemClickListener;

        ImageView  imvHinh_allproducts;
        TextView    txtProductPrice_allproducts,
                    txtProductName_allproducts;
        CardView    cvitem_allproducts;

        ImageView   imvProduct_listProduct;
        TextView    txtproductName_listProduct,
                    txtProductPrice_listProduct,
                    txtLike_listProduct;
        Button btnAdd_listProduct;

        //WishList
        ImageView imvProduct_Wish;
        TextView txtName_WishP, txtLike_WishP,txtPrice_WishP;
        CheckBox chkLike_WL;

        // khai biến các view bên itemlayout của likeproduct
        //ImageView hinhanh

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case CATEGORY:
                    imvHinh_allproducts = itemView.findViewById(R.id.imvHinh_allproducts);
                    txtProductName_allproducts = itemView.findViewById(R.id.txtProductName_allproducts);
                    txtProductPrice_allproducts = itemView.findViewById(R.id.txtProductPrice_allproducts);
                    cvitem_allproducts = itemView.findViewById(R.id.cvitem_allproducts);
                    break;
                case PRODUCT:
                    imvProduct_listProduct = itemView.findViewById(R.id.imvProduct_listProduct);
                    txtproductName_listProduct = itemView.findViewById(R.id.txtproductName_listProduct);
                    txtProductPrice_listProduct = itemView.findViewById(R.id.txtProductPrice_listProduct);
                    txtLike_listProduct = itemView.findViewById(R.id.txtLike_listProduct);
                    btnAdd_listProduct = itemView.findViewById(R.id.btnAdd_listProduct);
                    break;
                case WISH:
                    chkLike_WL=itemView.findViewById(R.id.chkLike_WL);
                    imvProduct_Wish=itemView.findViewById(R.id.imvProduct_Wish);
                    txtName_WishP=itemView.findViewById(R.id.txtName_WishP);
                    txtLike_WishP=itemView.findViewById(R.id.txtLike_WishP);
                    txtPrice_WishP=itemView.findViewById(R.id.txtPrice_WishP);

//                bindingview các view bên itemlayout của likeproduct
//                hinhanh = itemView.findnViewByID();
                break;
            }

            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(RecyclerViewItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

    private ViewGroup.MarginLayoutParams marginValue(ProductAdapter.ProductViewHolder viewHolder){
        float elevation = viewHolder.cvitem_allproducts.getMaxCardElevation();
        float radius = viewHolder.cvitem_allproducts.getRadius();
        double cos45 = Math.cos(Math.toRadians(45));

        int horizontalPadding = (int) (elevation + (1 - cos45) * radius);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewHolder.cvitem_allproducts.getLayoutParams();
        params.rightMargin = -horizontalPadding;
        params.topMargin = -horizontalPadding;
        return params;
    }
}

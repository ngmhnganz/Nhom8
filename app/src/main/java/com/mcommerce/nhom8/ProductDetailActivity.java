package com.mcommerce.nhom8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcommerce.model.Product;
import com.mcommerce.util.Constant;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imvProduct_productDetail;
    TextView    txtTag_productDetail,
                txtName_productDetail,
                txtDescription_productDetail,
                txtDetail_productDetail,
                txtPrice_productDetail;

    ImageButton btnBack_productDetail,
            btnBackOuter_productDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        linkview();
        getData();
        addEvent();
    }

    private void addEvent() {
        btnBack_productDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBackOuter_productDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra(Constant.PRODUCT_BUNDLE);
        Product product = bundle.getParcelable(Constant.SELECTED_PRODUCTED);
        txtTag_productDetail.setText(product.getProductType());
        txtName_productDetail.setText(product.getProductName());
        txtPrice_productDetail.setText(product.getProductPrice()+" đ");
        txtDescription_productDetail.setText(product.getProductDescription());
        txtDetail_productDetail.setText(product.getProductDetail());
        Glide.with(ProductDetailActivity.this).load(product.getProductImg()).into(imvProduct_productDetail);

    }

    private void linkview() {
        txtTag_productDetail = findViewById(R.id.txtTag_productDetail);
        txtName_productDetail = findViewById(R.id.txtName_productDetail);
        txtDescription_productDetail = findViewById(R.id.txtDescription_productDetail);
        txtDetail_productDetail = findViewById(R.id.txtDetail_productDetail);
        txtPrice_productDetail = findViewById(R.id.txtPrice_productDetail);

        imvProduct_productDetail = findViewById(R.id.imvProduct_productDetail);

        btnBack_productDetail = findViewById(R.id.btnBack_productDetail);
        btnBackOuter_productDetail = findViewById(R.id.btnBackOuter_productDetail);

    }
}
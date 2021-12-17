package com.mcommerce.nhom8.product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.mcommerce.model.Product;
import com.mcommerce.nhom8.R;
import com.mcommerce.util.Constant;

import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imvProduct_productDetail;
    private TextView    txtTag_productDetail,
                txtName_productDetail,
                txtDescription_productDetail,
                txtDetail_productDetail,
                txtPrice_productDetail;
    
    private EditText edtQuantity_aProductDetail;

    private ImageButton btnBack_productDetail,
            btnBackOuter_productDetail,
            btn_minus,
            btn_plus;

    private CheckBox chkLike1, chkLike2;

    private Button btnAddProduct_productDetail;

    private Product product ;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User/"+user.getUid()+"/userCart");
    private DatabaseReference Likeref = FirebaseDatabase.getInstance().getReference("User/"+user.getUid()+"/userLikeProduct");
    private String btnText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        linkview();
        getData();
        loadData();
        addEvent();
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
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);

        edtQuantity_aProductDetail = findViewById(R.id.edtQuantity_aProductDetail);

        btnAddProduct_productDetail = findViewById(R.id.btnAddProduct_productDetail);
        btnText = btnAddProduct_productDetail.getText().toString();
        chkLike1 = findViewById(R.id.chkLike1);
        chkLike2 = findViewById(R.id.chkLike2);
    }

    private void getData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra(Constant.PRODUCT_BUNDLE);
        product =bundle.getParcelable(Constant.SELECTED_PRODUCTED);
        txtTag_productDetail.setText(product.getProductType());
        txtName_productDetail.setText(product.getProductName());
        txtPrice_productDetail.setText(product.getProductPrice()+" đ");
        txtDescription_productDetail.setText(product.getProductDescription());
        txtDetail_productDetail.setText(product.getProductDetail());
        Glide.with(ProductDetailActivity.this).load(product.getProductImg()).into(imvProduct_productDetail);

    }

    private void loadData() {
        ref.child("id"+product.getProductID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long quantiy;
                // nếu khách hàng đã từng thêm món này vào cart
                if (snapshot.getValue()!=null){
                    // nội dung nút là cập nhật
                    btnText = "Cập nhật giỏ hàng";
                    HashMap<String, Object> result = (HashMap<String, Object>) snapshot.getValue();
                    quantiy = (long) result.get("quantity");
                    edtQuantity_aProductDetail.setText(quantiy+"");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProductDetailActivity.this, "Lỗi",Toast.LENGTH_SHORT);
            }
        });

        Likeref.child("id"+product.getProductID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!=null){
                   chkLike1.setChecked(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProductDetailActivity.this, "Lỗi",Toast.LENGTH_SHORT);
            }
        });
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

        edtQuantity_aProductDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtQuantity_aProductDetail.getText().toString().equals("0")){
                    btn_minus.setEnabled(false);
                }
                else {
                    btn_minus.setEnabled(true);
                }
                int unitPrice = product.getProductPrice();
                int quantity = Integer.parseInt(edtQuantity_aProductDetail.getText().toString());
                btnAddProduct_productDetail.setText(btnText+"- "+unitPrice*quantity+" đ");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_minus.setOnClickListener(v -> {
            edtQuantity_aProductDetail.setText((Integer.parseInt((edtQuantity_aProductDetail.getText().toString()))-1)+"");
        });
        btn_plus.setOnClickListener(v -> {
            edtQuantity_aProductDetail.setText((Integer.parseInt((edtQuantity_aProductDetail.getText().toString()))+1)+"");
        });

        if (user == null) {
            return;
        } else {

        }

        btnAddProduct_productDetail.setOnClickListener(v -> {
            //nếu edt =0, gán nút = Thêm vào giỏ hàng, remove item khỏi firebase nếu có, btn có nội dung thêm vào giỏ hàng
            int productID = (int) product.getProductID();
            if (edtQuantity_aProductDetail.getText().toString().equals("0")) {
                btnText = "Thêm vào giỏ hàng";
                btnAddProduct_productDetail.setText(btnText);
                ref.child(String.valueOf(productID)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProductDetailActivity.this,"Sản phẩm đã được xóa khỏi giỏ hàng",Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                // nếu thêm vào giỏ hàng thành công
                // đổi từ "thêm vào" thành "cập nhật" ( nếu có)
                // thông báo cho người dùng đã thành công
                int quantity = Integer.parseInt(edtQuantity_aProductDetail.getText().toString());
                ref.child("id"+productID).child("quantity").setValue(quantity);
                ref.child("id"+productID).child("name").setValue(product.getProductName());
                ref.child("id"+productID).child("id").setValue(productID);
                ref.child("id"+productID).child("price").setValue(product.getProductPrice());
                Toast.makeText(ProductDetailActivity.this, "Giỏ hàng của bạn đã được cập nhật", Toast.LENGTH_SHORT).show();
                btnText = btnAddProduct_productDetail.getText().toString();
                btnText = btnText.replace("Thêm vào giỏ hàng", "Cập nhật giỏ hàng");
                btnAddProduct_productDetail.setText(btnText);

                // set lại btnText thành nội dung ( do lệnh get dòng 138 có thể sẽ lấy luôn nội dung giá tiền)
                btnText = "Cập nhật giỏ hàng";


            }

        });

        chkLike1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int productID = (int) product.getProductID();
                if (isChecked) {
                    Likeref.child("id"+productID).child("name").setValue(product.getProductName());
                    Likeref.child("id"+productID).child("id").setValue(productID);
                    Likeref.child("id"+productID).child("price").setValue(product.getProductPrice());
                    Likeref.child("id"+productID).child("thumb").setValue(product.getProductImg());
                }
                else {
                    Likeref.child("id"+productID).removeValue();
                }
            }
        });

    }
}
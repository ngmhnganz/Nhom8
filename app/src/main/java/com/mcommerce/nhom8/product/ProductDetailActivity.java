package com.mcommerce.nhom8.product;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.mcommerce.nhom8.auth.LoginActivity;
import com.mcommerce.util.Constant;
import com.mcommerce.util.Key;

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
            btnBackOuter_productDetail;
    private Button btn_minus, btn_plus;

    private CheckBox chkLike1, chkLike2;

    private Button btnAddProduct_productDetail;

    private Product product ;
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference cartRef, Likeref, productRef;
    private String btnText;
    long unitPrice, quantity;



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
        txtPrice_productDetail.setText(product.getProductPrice()+" ??");
        txtDescription_productDetail.setText(product.getProductDescription());
        txtDetail_productDetail.setText(product.getProductDetail());
        Glide.with(ProductDetailActivity.this).load(product.getProductImg()).into(imvProduct_productDetail);

    }

    private void loadData() {
        if (user != null){
            cartRef = FirebaseDatabase.getInstance().getReference("User/"+user.getUid()+"/userCart");
            Likeref = FirebaseDatabase.getInstance().getReference("User/"+user.getUid()+"/userLikeProduct");
            cartRef.child("id"+product.getProductID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long quantiy;
                    if (snapshot.getValue()!=null){
                        btnText = "C???p nh???t gi??? h??ng";
                        HashMap<String, Object> result = (HashMap<String, Object>) snapshot.getValue();
                        quantiy = (long) result.get("quantity");
                        edtQuantity_aProductDetail.setText(quantiy+"");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ProductDetailActivity.this, "L???i",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ProductDetailActivity.this, "L???i",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    View.OnClickListener xuLyGioHang = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //n???u edt =0, g??n n??t = Th??m v??o gi??? h??ng, remove item kh???i firebase n???u c??, btn c?? n???i dung th??m v??o gi??? h??ng
            int productID = (int) product.getProductID();
            if (edtQuantity_aProductDetail.getText().toString().equals("0")) {
                btnText = "Th??m v??o gi??? h??ng";
                btnAddProduct_productDetail.setText(btnText);
                cartRef.child("id"+product.getProductID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(ProductDetailActivity.this,"S???n ph???m ???? ???????c x??a kh???i gi??? h??ng",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ProductDetailActivity.this,"Ch???n s??? l?????ng ????",Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                // n???u th??m v??o gi??? h??ng th??nh c??ng
                // ?????i t??? "th??m v??o" th??nh "c???p nh???t" ( n???u c??)
                // th??ng b??o cho ng?????i d??ng ???? th??nh c??ng
                long quantity = Long.parseLong(edtQuantity_aProductDetail.getText().toString());
                cartRef.child("id"+productID).child("quantity").setValue(quantity);
                cartRef.child("id"+productID).child("name").setValue(product.getProductName());
                cartRef.child("id"+productID).child("id").setValue(productID);
                cartRef.child("id"+productID).child("price").setValue(product.getProductPrice());
                Toast.makeText(ProductDetailActivity.this, "Gi??? h??ng c???a b???n ???? ???????c c???p nh???t", Toast.LENGTH_SHORT).show();
                btnText = btnAddProduct_productDetail.getText().toString();
                btnText = btnText.replace("Th??m v??o gi??? h??ng", "C???p nh???t gi??? h??ng");
                btnAddProduct_productDetail.setText(btnText);
                // set l???i btnText th??nh n???i dung ( do l???nh get d??ng 138 c?? th??? s??? l???y lu??n n???i dung gi?? ti???n)
                btnText = "C???p nh???t gi??? h??ng";
            }
        }
    };


    View.OnClickListener xuLyYeuThich = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int productID = (int) product.getProductID();
            productRef = FirebaseDatabase.getInstance().getReference(Key.PRODUCT+"/"+productID+"/"+Product.Like);
            if (chkLike1.isChecked()) {

                Likeref.child("id"+productID).child("name").setValue(product.getProductName());
                Likeref.child("id"+productID).child("id").setValue(productID);
                Likeref.child("id"+productID).child("price").setValue(product.getProductPrice());
                Likeref.child("id"+productID).child("thumb").setValue(product.getProductImg());
                productRef.setValue(ServerValue.increment(1));
            }
            else {
                Likeref.child("id"+productID).removeValue();
                productRef.setValue(ServerValue.increment(-1));
            }
        }
    };
    View.OnClickListener yeuCauDangNhap = v -> yeuCauDangNhap();
    private void yeuCauDangNhap() {
        Dialog requestUser = new Dialog(ProductDetailActivity.this);
        requestUser.setContentView(R.layout.dialog_request_user);
        requestUser.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnDangNhap=requestUser.findViewById(R.id.btnDangNhap);
        Button btnDong=requestUser.findViewById(R.id.btnDong);

        btnDong.setOnClickListener(l -> requestUser.dismiss());
        btnDangNhap.setOnClickListener(l -> {
            startActivity(new Intent(ProductDetailActivity.this, LoginActivity.class));
            finish();
        });
        requestUser.show();
    }

    private void addEvent() {

        btnBack_productDetail.setOnClickListener(v -> finish());

        btnBackOuter_productDetail.setOnClickListener(v -> finish());

        edtQuantity_aProductDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtQuantity_aProductDetail.getText().toString().equals("0") && !edtQuantity_aProductDetail.getText().toString().isEmpty()) {
                    btn_minus.setEnabled(true);
                    btnAddProduct_productDetail.setEnabled(true);
                    unitPrice = product.getProductPrice();
                    if (s.toString().length() <= 10){
                        quantity = Long.parseLong(edtQuantity_aProductDetail.getText().toString());
                        btnAddProduct_productDetail.setText(btnText+"- "+unitPrice*quantity+" ??");
                    }
                }
                else if (s.toString().isEmpty()){
                    btnAddProduct_productDetail.setEnabled(false);
                    btnAddProduct_productDetail.setText(btnText);
                }
                else {
                    btn_minus.setEnabled(false);
                    btnAddProduct_productDetail.setEnabled(true);
                    btnAddProduct_productDetail.setText(btnText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_minus.setOnClickListener(v -> {
            if (!edtQuantity_aProductDetail.getText().toString().isEmpty()){
                edtQuantity_aProductDetail.setText((Long.parseLong((edtQuantity_aProductDetail.getText().toString()))-1)+"");
            }
        });

        btn_plus.setOnClickListener(v -> {
            if (!edtQuantity_aProductDetail.getText().toString().isEmpty()){
                if (!edtQuantity_aProductDetail.getText().toString().equals("9999999999"))
                    edtQuantity_aProductDetail.setText((Long.parseLong((edtQuantity_aProductDetail.getText().toString()))+1)+"");
            }
        });

        if (user != null) {
            btnAddProduct_productDetail.setOnClickListener(xuLyGioHang);
            chkLike1.setOnClickListener(xuLyYeuThich);
        }
        else {
            btnAddProduct_productDetail.setOnClickListener(yeuCauDangNhap);
            chkLike1.setOnClickListener(yeuCauDangNhap);
        }
    }
}
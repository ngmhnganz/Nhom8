package com.mcommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String productName;
    private String productImg;
    private String productDescription;
    private String productDetail;
    private String productLabel;
    private long productID;
    private String productType;
    private String productshortName;
    private long productPrice;
    private int productQuantity;
    private int productLike;

    public static final String  NGUYEN_lIEU = "Nguyên liệu",
            DUNG_CU = "Dụng cụ",
            COMBO = "Combo";
    public static final String Label = "productLabel";
    public static final String Name = "productName";
    public static final String ShortName = "productShortName";
    public static final String ID = "productID";
    public static final String Type = "productType";
    public static final String Like = "productLike";
    public static final String Image = "productImg";


    protected Product(Parcel in) {
        productName = in.readString();
        productImg = in.readString();
        productDescription = in.readString();
        productDetail = in.readString();
        productID = in.readLong();
        productType = in.readString();
        productshortName = in.readString();
        productLabel = in.readString();
        productPrice = in.readLong();
        productQuantity = in.readInt();
        productLike = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductshortName() {
        return productshortName;
    }

    public void setProductshortName(String productshortName) {
        this.productshortName = productshortName;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductLike() {
        return productLike;
    }

    public void setProductLike(int productLike) {
        this.productLike = productLike;
    }

    public Product() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productImg);
        dest.writeString(productDescription);
        dest.writeString(productDetail);
        dest.writeLong(productID);
        dest.writeString(productType);
        dest.writeString(productshortName);
        dest.writeString(productLabel);
        dest.writeLong(productPrice);
        dest.writeInt(productQuantity);
        dest.writeInt(productLike);
    }
}

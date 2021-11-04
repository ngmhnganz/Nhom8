package com.mcommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    public static final String  NGUYEN_lIEU = "Nguyên liệu",
                                DUNG_CU = "Dụng cụ",
                                COMBO = "Combo";
    private String productName;
    private String productImg;
    private String productDescription;
    private String productDetail;
    private String productID;
    private String productType;
    private String productshortName;
    private int productPrice;
    private int productQuantity;
    private int productLike;

    protected Product(Parcel in) {
        productName = in.readString();
        productImg = in.readString();
        productDescription = in.readString();
        productDetail = in.readString();
        productID = in.readString();
        productType = in.readString();
        productshortName = in.readString();
        productPrice = in.readInt();
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

    public Product(String productName, String productImg, String productDescription, String productDetail, Integer productPrice, int productQuantity, Integer productLike, String productID, String productType) {
        this.productName = productName;
        this.productImg = productImg;
        this.productDescription = productDescription;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productLike = productLike;
        this.productID = productID;
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
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
        dest.writeString(productID);
        dest.writeString(productType);
        dest.writeString(productshortName);
        dest.writeInt(productPrice);
        dest.writeInt(productQuantity);
        dest.writeInt(productLike);
    }
}

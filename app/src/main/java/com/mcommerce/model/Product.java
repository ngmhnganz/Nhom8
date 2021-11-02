package com.mcommerce.model;

public class Product {
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
}

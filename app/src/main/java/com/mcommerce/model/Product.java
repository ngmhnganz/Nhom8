package com.mcommerce.model;

public class Product {
    private String productName;
    private String productImg;
    private String productDescription;
    private String productDetail;
    private String productPrice;
    private String productQuantity;
    private String productLike;


    public Product(String productName, String productImg, String productDescription, String productDetail, String productPrice, String productQuantity, String productLike) {
        this.productName = productName;
        this.productImg = productImg;
        this.productDescription = productDescription;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productLike = productLike;
    }

    public Product(){

    }

    // sẽ xóa nếu đổ dl thật về cho trang AllProducts (Thư)
   /* public Product(String productName, String productImg, int productPrice) {
        this.productName = productName;
        this.productImg = productImg;
        this.productPrice = productPrice;
    }*/


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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductLike() {
        return productLike;
    }

    public void setProductLike(String productLike) {
        this.productLike = productLike;
    }

}

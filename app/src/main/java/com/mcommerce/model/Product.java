package com.mcommerce.model;

public class Product {
    public String productName;
    public int productImg;
    public String productDescription;
    public String productDetail;
    public int productPrice;
    public int productQuantity;
    public int productLike;

    //thêm constructer chỉ có product name va bg, pong làm recipe


    public Product(String productName, int productBackgroundImg) {
        this.productName = productName;
        this.productBackgroundImg = productBackgroundImg;
    }

    //thêm thuộc tính cho list product của pong
    public int productBackgroundImg;
    public int productAddItem;

    public int getProductBackgroundImg() {
        return productBackgroundImg;
    }

    public void setProductBackgroundImg(int productBackgroundImg) {
        this.productBackgroundImg = productBackgroundImg;
    }

    public int getProductAddItem() {
        return productAddItem;
    }

    public void setProductAddItem(int productAddItem) {
        this.productAddItem = productAddItem;
    }

    public Product(String productName, int productImg, int productPrice, int productLike, int productBackgroundImg, int productAddItem) {
        this.productName = productName;
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.productLike = productLike;
        this.productBackgroundImg = productBackgroundImg;
        this.productAddItem = productAddItem;
    }
    //pong end

    public Product(String productName, int productImg, String productDescription, String productDetail, int productPrice, int productQuantity, int productLike) {
        this.productName = productName;
        this.productImg = productImg;
        this.productDescription = productDescription;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productLike = productLike;
    }

    // sẽ xóa nếu đổ dl thật về cho trang AllProducts (Thư)
    public Product(String productName, int productImg, int productPrice) {
        this.productName = productName;
        this.productImg = productImg;
        this.productPrice = productPrice;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
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

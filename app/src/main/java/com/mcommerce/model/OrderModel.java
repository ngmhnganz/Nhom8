package com.mcommerce.model;

public class OrderModel {

    String orderDes, orderPrice, orderAddress, orderDate;
    int orderImage;

    public OrderModel(String orderDes, String orderPrice, String orderAddress, String orderDate, int orderImage) {
        this.orderDes = orderDes;
        this.orderPrice = orderPrice;
        this.orderAddress = orderAddress;
        this.orderDate = orderDate;
        this.orderImage = orderImage;
    }

    public String getOrderDes() {
        return orderDes;
    }

    public void setOrderDes(String orderDes) {
        this.orderDes = orderDes;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }
}

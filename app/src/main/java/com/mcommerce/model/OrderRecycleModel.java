package com.mcommerce.model;

import java.util.List;

public class OrderRecycleModel {
    List<OrderModel> orders;

    public OrderRecycleModel(List<OrderModel> orders) {
        this.orders = orders;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}

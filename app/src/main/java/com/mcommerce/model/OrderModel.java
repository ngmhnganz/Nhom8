package com.mcommerce.model;

import java.util.List;

public class OrderModel {

    public static final int DAT_HANG_THANH_CONG = 1;
    public static final int XAC_NHAN = 2;
    public static final int CHUAN_BI = 3;
    public static final int DONG_GOI = 4;
    public static final int VAN_CHUYEN = 5;
    public static final int THANH_CONG = 6;


    String idOrder, dateOrder, addOrder, paymentOrder;
    List<String> itemOrder;
    int priceOrder, statusOrder;

    public OrderModel(String idOrder, String dateOrder, String addOrder, String paymentOrder, List<String> itemOrder, int priceOrder, int statusOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.addOrder = addOrder;
        this.paymentOrder = paymentOrder;
        this.itemOrder = itemOrder;
        this.priceOrder = priceOrder;
        this.statusOrder = statusOrder;
    }


    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getAddOrder() {
        return addOrder;
    }

    public void setAddOrder(String addOrder) {
        this.addOrder = addOrder;
    }

    public String getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public List<String> getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(List<String> itemOrder) {
        this.itemOrder = itemOrder;
    }

    public int getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(int priceOrder) {
        this.priceOrder = priceOrder;
    }

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }
}

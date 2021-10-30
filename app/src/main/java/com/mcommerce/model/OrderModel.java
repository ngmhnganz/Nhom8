package com.mcommerce.model;

import java.util.HashMap;
import java.util.List;

public class OrderModel {

    public static final String DAT_HANG_THANH_CONG = "1";
    public static final String XAC_NHAN = "2";
    public static final String CHUAN_BI = "3";
    public static final String DONG_GOI = "4";
    public static final String VAN_CHUYEN = "5";
    public static final String THANH_CONG = "6";
    public static final String DA_HUY = "0";


    private String idOrder, dateOrder, addOrder, paymentOrder, statusOrder, imgOrder;
    private int priceOrder;

    HashMap<String,Integer> itemOrder;

    public String getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(String imgOrder) {
        this.imgOrder = imgOrder;
    }

    public OrderModel(String idOrder, String dateOrder, String addOrder, String paymentOrder, HashMap<String,Integer> itemOrder, int priceOrder, String statusOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.addOrder = addOrder;
        this.paymentOrder = paymentOrder;
        this.itemOrder = itemOrder;
        this.priceOrder = priceOrder;
        this.statusOrder = statusOrder;
    }

    public OrderModel() {
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

    public HashMap<String,Integer> getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(HashMap<String,Integer> itemOrder) {
        this.itemOrder = itemOrder;
    }

    public int getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(int priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
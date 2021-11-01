package com.mcommerce.model;

import java.util.HashMap;
import java.util.List;

public class OrderModel {


    public static final int THANH_CONG = 0;
    public static final int DA_HUY = 1;
    public static final int DAT_HANG_THANH_CONG = 2;
    public static final int XAC_NHAN = 3;
    public static final int CHUAN_BI =4;
    public static final int DONG_GOI = 5;
    public static final int VAN_CHUYEN = 6;


    private String  idOrder,
            dateOrder,
            addOrder,
            paymentOrder,
            imgOrder;
    private int     priceOrder,
            statusOrder;
    private long dateLongOder;

    public long getDateLongOder() {
        return dateLongOder;
    }

    public void setDateLongOder(long dateLongOder) {
        this.dateLongOder = dateLongOder;
    }

    HashMap<String,Integer> itemOrder;

    public String getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(String imgOrder) {
        this.imgOrder = imgOrder;
    }

    public OrderModel(String idOrder, String dateOrder, String addOrder, String paymentOrder, HashMap<String,Integer> itemOrder, int priceOrder, int statusOrder) {
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

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }
}
package com.mcommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

public class OrderModel implements Parcelable {

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
                    imgOrder,
                    statusStringOrder;
    private int     priceOrder,
                    statusOrder,
                    discountOrder,
                    shippingFeeOrder;
    private long dateLongOder;
    private HashMap<String,Integer> itemOrder;


    protected OrderModel(Parcel in) {
        idOrder = in.readString();
        dateOrder = in.readString();
        addOrder = in.readString();
        paymentOrder = in.readString();
        imgOrder = in.readString();
        priceOrder = in.readInt();
        statusOrder = in.readInt();
        dateLongOder = in.readLong();
        discountOrder = in.readInt();
        shippingFeeOrder = in.readInt();
        statusStringOrder = in.readString();
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };


    //region Constructor
    public OrderModel(String idOrder, String dateOrder, String addOrder, String paymentOrder, HashMap<String,Integer> itemOrder, int priceOrder, int statusOrder, int discountOrder, int shippingFeeOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.addOrder = addOrder;
        this.paymentOrder = paymentOrder;
        this.itemOrder = itemOrder;
        this.priceOrder = priceOrder;
        this.statusOrder = statusOrder;
        this.discountOrder = discountOrder;
        this.shippingFeeOrder = shippingFeeOrder;

    }
    public OrderModel() {
    }

    //endregion

    //region Getter và Setter


    public void setStatusStringOrder(String statusStringOrder) {
        this.statusStringOrder = statusStringOrder;
    }

    public String getStatusStringOrder() {

        return statusStringOrder;
    }

    public int getShippingFeeOrder() {
        return shippingFeeOrder;
    }

    public void setShippingFeeOrder(int shippingFeeOrder) {
        this.shippingFeeOrder = shippingFeeOrder;
    }

    public int getDiscountOrder() {
        return discountOrder;
    }

    public void setDiscountOrder(int discountOrder) {
        this.discountOrder = discountOrder;
    }

    public long getDateLongOder() {
        return dateLongOder;
    }

    public void setDateLongOder(long dateLongOder) {
        this.dateLongOder = dateLongOder;
    }

    public String getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(String imgOrder) {
        this.imgOrder = imgOrder;
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


    //endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idOrder);
        parcel.writeString(dateOrder);
        parcel.writeString(addOrder);
        parcel.writeString(paymentOrder);
        parcel.writeString(imgOrder);
        parcel.writeInt(priceOrder);
        parcel.writeInt(statusOrder);
        parcel.writeLong(dateLongOder);
        parcel.writeInt(discountOrder);
        parcel.writeInt(shippingFeeOrder);
        parcel.writeString(statusStringOrder);
    }
}
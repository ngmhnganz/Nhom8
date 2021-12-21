package com.mcommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Order implements Parcelable {


    public static final String CASH = "Tiền mặt";
    public static final String Status = "statusOrder";

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
                    statusStringOrder,
                    uidOrder,
                    customerName,
                    customerPhone;
    private long    priceOrder,
                    discountOrder,
                    shippingFeeOrder,
                    rewardOrder,
                    totalOrder;
    private int statusOrder;
    private long dateLongOrder;
    private Map<String, HashMap<String,?>> itemOrder;


    protected Order(Parcel in) {
        idOrder = in.readString();
        dateOrder = in.readString();
        addOrder = in.readString();
        paymentOrder = in.readString();
        imgOrder = in.readString();
        priceOrder = in.readLong();
        statusOrder = in.readInt();
        dateLongOrder = in.readLong();
        discountOrder = in.readLong();
        shippingFeeOrder = in.readLong();
        statusStringOrder = in.readString();
        uidOrder = in.readString();
        customerName = in.readString();
        customerPhone = in.readString();
        rewardOrder = in.readLong();
        totalOrder = in.readLong();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };


    //region Constructor
    public Order() {
    }

    //endregion

    //region Getter và Setter


    public long getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(long totalOrder) {
        this.totalOrder = totalOrder;
    }

    public long getRewardOrder() {
        return rewardOrder;
    }

    public void setRewardOrder(long rewardOrder) {
        this.rewardOrder = rewardOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getUidOrder() {
        return uidOrder;
    }

    public void setUidOrder(String uidOrder) {
        this.uidOrder = uidOrder;
    }

    public void setStatusStringOrder(String statusStringOrder) {
        this.statusStringOrder = statusStringOrder;
    }

    public String getStatusStringOrder() {

        return statusStringOrder;
    }

    public long getShippingFeeOrder() {
        return shippingFeeOrder;
    }

    public void setShippingFeeOrder(long shippingFeeOrder) {
        this.shippingFeeOrder = shippingFeeOrder;
    }

    public long getDiscountOrder() {
        return discountOrder;
    }

    public void setDiscountOrder(long discountOrder) {
        this.discountOrder = discountOrder;
    }

    public long getDateLongOrder() {
        return dateLongOrder;
    }

    public void setDateLongOrder(long dateLongOrder) {
        this.dateLongOrder = dateLongOrder;
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

    public Map<String, HashMap<String,?>> getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Map<String, HashMap<String,?>> itemOrder) {
        this.itemOrder = itemOrder;
    }

    public long getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(long priceOrder) {
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
        parcel.writeLong(priceOrder);
        parcel.writeInt(statusOrder);
        parcel.writeLong(dateLongOrder);
        parcel.writeLong(discountOrder);
        parcel.writeLong(shippingFeeOrder);
        parcel.writeString(statusStringOrder);
        parcel.writeString(uidOrder);
        parcel.writeString(customerPhone);
        parcel.writeString(customerName);
        parcel.writeLong(rewardOrder);
        parcel.writeLong(totalOrder);
    }
}
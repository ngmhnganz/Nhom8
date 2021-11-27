package com.mcommerce.model;

import java.util.Map;

public class User {
    private String userID;
    private String userName;
    private String userBirthday;
    private String userGender;
    private String userPhone;
    private String userEmail;
    private String userOrder;
    private String userDefaultAddress;
    private Map<String, Integer> userCart;

    public String getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(String userOrder) {
        this.userOrder = userOrder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDefaultAddress() {
        return userDefaultAddress;
    }

    public void setUserDefaultAddress(String userDefaultAddress) {
        this.userDefaultAddress = userDefaultAddress;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, Integer> getUserCart() {
        return userCart;
    }

    public void setUserCart(Map<String, Integer> userCart) {
        this.userCart = userCart;
    }

    public User() {
    }

}

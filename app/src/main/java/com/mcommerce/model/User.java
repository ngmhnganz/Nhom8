package com.mcommerce.model;

public class User {
    private String userID = null;
    private String userName = null;
    private String userBirthday = null;
    private String userGender = null;
    private String userPhone = null;
    private String userEmail = null;
    private String userOrder = null;
    private String userDefaultAddress = null;

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

    public User() {
    }
}

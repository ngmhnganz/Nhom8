package com.mcommerce.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    public  static final String Cart = "userCart";
    public  static final String Order = "userOrder";
    private String userID;
    private String userName;
    private String userBirthday;
    private String userGender;
    private long userPoint;

    private String userAddress;
    private Map<String, HashMap<String, ?>> userCart;
    private Map<String, HashMap<String, ?>> userLikeProduct;
    private Map<String, HashMap<String, ?>> userLikeRecipe;
    private Map<String, HashMap<String, ?>> userOrder;

    public Map<String, HashMap<String, ?>> getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Map<String, HashMap<String, ?>> userOrder) {
        this.userOrder = userOrder;
    }

    public Map<String, HashMap<String, ?>> getUserLikeProduct() {
        return userLikeProduct;
    }

    public void setUserLikeProduct(Map<String, HashMap<String, ?>> userLikeProduct) {
        this.userLikeProduct = userLikeProduct;
    }

    public Map<String, HashMap<String, ?>> getUserLikeRecipe() {
        return userLikeRecipe;
    }

    public void setUserLikeRecipe(Map<String, HashMap<String, ?>> userLikeRecipe) {
        this.userLikeRecipe = userLikeRecipe;
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

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public long getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(long userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, HashMap<String, ?>> getUserCart() {
        return userCart;
    }

    public void setUserCart(Map<String, HashMap<String, ?>> userCart) {
        this.userCart = userCart;
    }

    public User() {
    }

}

package com.mcommerce.model;

public class GoiYMonanModel {
    int imgMonan;
    String nameMonan, desMonan, timeMonan;

    public GoiYMonanModel(int imgMonan, String nameMonan, String txtDesMonan, String timeMonan) {
        this.imgMonan = imgMonan;
        this.nameMonan = nameMonan;
        this.desMonan = txtDesMonan;
        this.timeMonan = timeMonan;
    }

    public String getTimeMonan() {
        return timeMonan;
    }

    public void setTimeMonan(String timeMonan) {
        this.timeMonan = timeMonan;
    }

    public int getImgMonan() {
        return imgMonan;
    }

    public void setImgMonan(int imgMonan) {
        this.imgMonan = imgMonan;
    }

    public String getNameMonan() {
        return nameMonan;
    }

    public void setNameMonan(String nameMonan) {
        this.nameMonan = nameMonan;
    }

    public String getDesMonan() {
        return desMonan;
    }

    public void setDesMonan(String desMonan) {
        this.desMonan = desMonan;
    }
}

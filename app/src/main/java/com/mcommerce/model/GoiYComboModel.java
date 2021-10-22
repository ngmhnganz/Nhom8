package com.mcommerce.model;

public class GoiYComboModel {
    int priceCombo, imgCombo;
    String nameCombo, desCombo;

    public GoiYComboModel(int priceCombo, int imgCombo, String nameCombo, String desCombo) {
        this.priceCombo = priceCombo;
        this.imgCombo = imgCombo;
        this.nameCombo = nameCombo;
        this.desCombo = desCombo;
    }

    public int getPriceCombo() {
        return priceCombo;
    }

    public void setPriceCombo(int priceCombo) {
        this.priceCombo = priceCombo;
    }

    public int getImgCombo() {
        return imgCombo;
    }

    public void setImgCombo(int imgCombo) {
        this.imgCombo = imgCombo;
    }

    public String getNameCombo() {
        return nameCombo;
    }

    public void setNameCombo(String nameCombo) {
        this.nameCombo = nameCombo;
    }

    public String getDesCombo() {
        return desCombo;
    }

    public void setDesCombo(String desCombo) {
        this.desCombo = desCombo;
    }
}

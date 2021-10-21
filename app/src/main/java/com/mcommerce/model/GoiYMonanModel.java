package com.mcommerce.model;

public class GoiYMonanModel {
    int imv;
    String txtTenMonan, txtDesMonan, txtTimeMonan;

    public GoiYMonanModel(int imv, String txtTenMonan, String txtDesMonan, String txtTimeMonan) {
        this.imv = imv;
        this.txtTenMonan = txtTenMonan;
        this.txtDesMonan = txtDesMonan;
        this.txtTimeMonan = txtTimeMonan;
    }

    public String getTxtTimeMonan() {
        return txtTimeMonan;
    }

    public void setTxtTimeMonan(String txtTimeMonan) {
        this.txtTimeMonan = txtTimeMonan;
    }

    public int getImv() {
        return imv;
    }

    public void setImv(int imv) {
        this.imv = imv;
    }

    public String getTxtTenMonan() {
        return txtTenMonan;
    }

    public void setTxtTenMonan(String txtTenMonan) {
        this.txtTenMonan = txtTenMonan;
    }

    public String getTxtDesMonan() {
        return txtDesMonan;
    }

    public void setTxtDesMonan(String txtDesMonan) {
        this.txtDesMonan = txtDesMonan;
    }
}

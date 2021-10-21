package com.mcommerce.model;

public class BannerMainModel {
    int imvBanner;
    String desBanner;

    public BannerMainModel(int imvBanner, String desBanner) {
        this.imvBanner = imvBanner;
        this.desBanner = desBanner;
    }

    public int getImvBanner() {
        return imvBanner;
    }

    public void setImvBanner(int imvBanner) {
        this.imvBanner = imvBanner;
    }

    public String getDesBanner() {
        return desBanner;
    }

    public void setDesBanner(String desBanner) {
        this.desBanner = desBanner;
    }


}

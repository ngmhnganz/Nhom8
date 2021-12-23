package com.mcommerce.model;

public class PointsHistoryModel {
    private String  idOrder,
                    dateOrder;
    private int points;


    public PointsHistoryModel(String idOrder, String dateOrder, int points) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.points = points;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public int getPoints() {
        return points;
    }
}

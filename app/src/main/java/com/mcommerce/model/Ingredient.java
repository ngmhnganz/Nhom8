package com.mcommerce.model;

public class Ingredient {
    public static final String LABEL_BOT ="Bột";
    public static final String LABEL_SUAKEM ="Sữa-Kem";
    public static final String LABEL_BO ="Bơ";
    public static final String LABEL_KHAC ="Khác";
    private long id;
    private String name, label, shortname;

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Ingredient() {
    }

    public Ingredient(long id, String name, String label, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.label = label;
    }
}

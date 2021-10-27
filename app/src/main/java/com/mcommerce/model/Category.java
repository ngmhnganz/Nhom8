package com.mcommerce.model;

import java.util.List;

public class Category {
    public String categoryName;
    public String categotySeeMore;
    public List <Product> products;

    public Category(String categoryName, String categotySeeMore, List<Product> products) {
        this.categoryName = categoryName;
        this.categotySeeMore = categotySeeMore;
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategotySeeMore() {
        return categotySeeMore;
    }

    public void setCategotySeeMore(String categotySeeMore) {
        this.categotySeeMore = categotySeeMore;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

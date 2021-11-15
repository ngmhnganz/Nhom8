package com.mcommerce.model;

public class Recipe {
    private String recipeName;
    private int recipeRation;
    private int recipeLevel;
    private int recipeLike;
    private String recipeDesciption;
    private int recipeImage;

    public Recipe(String recipeName, int recipeLike, int recipeImage) {
        this.recipeName = recipeName;
        this.recipeLike = recipeLike;
        this.recipeImage = recipeImage;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipeRation() {
        return recipeRation;
    }

    public void setRecipeRation(int recipeRation) {
        this.recipeRation = recipeRation;
    }

    public int getRecipeLevel() {
        return recipeLevel;
    }

    public void setRecipeLevel(int recipeLevel) {
        this.recipeLevel = recipeLevel;
    }

    public int getRecipeLike() {
        return recipeLike;
    }

    public void setRecipeLike(int recipeLike) {
        this.recipeLike = recipeLike;
    }

    public String getRecipeDesciption() {
        return recipeDesciption;
    }

    public void setRecipeDesciption(String recipeDesciption) {
        this.recipeDesciption = recipeDesciption;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(int recipeImage) {
        this.recipeImage = recipeImage;
    }
}

package com.mcommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Recipe implements Parcelable {
    private String recipeID;
    private long recipeTime;
    private String recipeName;
    private long recipeRation;
    private String recipeLevel;
    private long recipeLike;
    private String recipeDescription;
    private String recipeShortDescription;
    private String recipeImage;
    private Map<String, HashMap<String,?>> recipeIngredient;

    public String getRecipeShortDescription() {
        return recipeShortDescription;
    }

    public void setRecipeShortDescription(String recipeShortDescription) {
        this.recipeShortDescription = recipeShortDescription;
    }

    public Map<String, HashMap<String, ?>> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(Map<String, HashMap<String, ?>> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    protected Recipe(Parcel in) {
        recipeID = in.readString();
        recipeTime = in.readLong();
        recipeName = in.readString();
        recipeRation = in.readLong();
        recipeLevel = in.readString();
        recipeLike = in.readLong();
        recipeDescription = in.readString();
        recipeImage = in.readString();
        recipeShortDescription = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public long getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(long recipeTime) {
        this.recipeTime = recipeTime;
    }

    public Recipe() {
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public long getRecipeRation() {
        return recipeRation;
    }

    public void setRecipeRation(long recipeRation) {
        this.recipeRation = recipeRation;
    }

    public String getRecipeLevel() {
        return recipeLevel;
    }

    public void setRecipeLevel(String recipeLevel) {
        this.recipeLevel = recipeLevel;
    }

    public long getRecipeLike() {
        return recipeLike;
    }

    public void setRecipeLike(long recipeLike) {
        this.recipeLike = recipeLike;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeID);
        dest.writeLong(recipeTime);
        dest.writeString(recipeName);
        dest.writeLong(recipeRation);
        dest.writeString(recipeLevel);
        dest.writeLong(recipeLike);
        dest.writeString(recipeDescription);
        dest.writeString(recipeImage);
        dest.writeString(recipeShortDescription);
    }
}

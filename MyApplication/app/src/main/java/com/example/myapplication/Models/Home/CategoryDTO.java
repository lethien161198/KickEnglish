package com.example.myapplication.Models.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    @SerializedName("categoryType")
    @Expose
    private CategoryType categoryType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parentCategoryId")
    @Expose
    private Integer parentCategoryId;

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}

package com.example.myapplication.Models.Authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriptionPlan {
    @SerializedName("abbreviationKey")
    @Expose
    private String abbreviationKey;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("free")
    @Expose
    private Boolean free;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("lifeTime")
    @Expose
    private Boolean lifeTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("type")
    @Expose
    private Integer type;

    public String getAbbreviationKey() {
        return abbreviationKey;
    }

    public void setAbbreviationKey(String abbreviationKey) {
        this.abbreviationKey = abbreviationKey;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Boolean getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Boolean lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

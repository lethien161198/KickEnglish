package com.example.myapplication.Models.Home;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("audioCount")
    @Expose
    private Integer audioCount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageDetailUrl")
    @Expose
    private String imageDetailUrl;
    @SerializedName("mediumThumbnailUrl")
    @Expose
    private String mediumThumbnailUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("originalThumbnailUrl")
    @Expose
    private String originalThumbnailUrl;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("smallThumbnailUrl")
    @Expose
    private String smallThumbnailUrl;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("videoCount")
    @Expose
    private Integer videoCount;

    public Integer getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(Integer audioCount) {
        this.audioCount = audioCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageDetailUrl() {
        return imageDetailUrl;
    }

    public void setImageDetailUrl(String imageDetailUrl) {
        this.imageDetailUrl = imageDetailUrl;
    }

    public String getMediumThumbnailUrl() {
        return mediumThumbnailUrl;
    }

    public void setMediumThumbnailUrl(String mediumThumbnailUrl) {
        this.mediumThumbnailUrl = mediumThumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalThumbnailUrl() {
        return originalThumbnailUrl;
    }

    public void setOriginalThumbnailUrl(String originalThumbnailUrl) {
        this.originalThumbnailUrl = originalThumbnailUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSmallThumbnailUrl() {
        return smallThumbnailUrl;
    }

    public void setSmallThumbnailUrl(String smallThumbnailUrl) {
        this.smallThumbnailUrl = smallThumbnailUrl;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }
}

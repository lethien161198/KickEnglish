package com.example.myapplication.Models.Pronunciation;

import android.os.Parcelable;

import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.ModelingScene;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MediaDTO implements Serializable {
    @SerializedName("categories")
    @Expose
    private List<CategoryDTO> categories = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("favoriteCount")
    @Expose
    private Integer favoriteCount;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lowQualityUrl")
    @Expose
    private String lowQualityUrl;
    @SerializedName("mediaType")
    @Expose
    private String mediaType;
    @SerializedName("mediumThumbnailUrl")
    @Expose
    private String mediumThumbnailUrl;
    @SerializedName("modelingScenes")
    @Expose
    private List<ModelingScene> modelingScenes = null;
    @SerializedName("originalThumbnailUrl")
    @Expose
    private String originalThumbnailUrl;
    @SerializedName("shadowingContent")
    @Expose
    private String shadowingContent;
    @SerializedName("skillLevel")
    @Expose
    private String skillLevel;
    @SerializedName("smallThumbnailUrl")
    @Expose
    private String smallThumbnailUrl;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("transcriptUrl")
    @Expose
    private String transcriptUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("viewCount")
    @Expose
    private Integer viewCount;
    @SerializedName("vip")
    @Expose
    private Boolean vip;
    @SerializedName("widthHeightRatio")
    @Expose
    private Integer widthHeightRatio;

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
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

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
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

    public String getLowQualityUrl() {
        return lowQualityUrl;
    }

    public void setLowQualityUrl(String lowQualityUrl) {
        this.lowQualityUrl = lowQualityUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediumThumbnailUrl() {
        return mediumThumbnailUrl;
    }

    public void setMediumThumbnailUrl(String mediumThumbnailUrl) {
        this.mediumThumbnailUrl = mediumThumbnailUrl;
    }

    public List<ModelingScene> getModelingScenes() {
        return modelingScenes;
    }

    public void setModelingScenes(List<ModelingScene> modelingScenes) {
        this.modelingScenes = modelingScenes;
    }

    public String getOriginalThumbnailUrl() {
        return originalThumbnailUrl;
    }

    public void setOriginalThumbnailUrl(String originalThumbnailUrl) {
        this.originalThumbnailUrl = originalThumbnailUrl;
    }

    public String getShadowingContent() {
        return shadowingContent;
    }

    public void setShadowingContent(String shadowingContent) {
        this.shadowingContent = shadowingContent;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getSmallThumbnailUrl() {
        return smallThumbnailUrl;
    }

    public void setSmallThumbnailUrl(String smallThumbnailUrl) {
        this.smallThumbnailUrl = smallThumbnailUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranscriptUrl() {
        return transcriptUrl;
    }

    public void setTranscriptUrl(String transcriptUrl) {
        this.transcriptUrl = transcriptUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Integer getWidthHeightRatio() {
        return widthHeightRatio;
    }

    public void setWidthHeightRatio(Integer widthHeightRatio) {
        this.widthHeightRatio = widthHeightRatio;
    }
}

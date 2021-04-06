package com.example.myapplication.Models.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberLessonDTO {
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mediumThumbnailUrl")
    @Expose
    private String mediumThumbnailUrl;
    @SerializedName("originalThumbnailUrl")
    @Expose
    private String originalThumbnailUrl;
    @SerializedName("smallThumbnailUrl")
    @Expose
    private String smallThumbnailUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediumThumbnailUrl() {
        return mediumThumbnailUrl;
    }

    public void setMediumThumbnailUrl(String mediumThumbnailUrl) {
        this.mediumThumbnailUrl = mediumThumbnailUrl;
    }

    public String getOriginalThumbnailUrl() {
        return originalThumbnailUrl;
    }

    public void setOriginalThumbnailUrl(String originalThumbnailUrl) {
        this.originalThumbnailUrl = originalThumbnailUrl;
    }

    public String getSmallThumbnailUrl() {
        return smallThumbnailUrl;
    }

    public void setSmallThumbnailUrl(String smallThumbnailUrl) {
        this.smallThumbnailUrl = smallThumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

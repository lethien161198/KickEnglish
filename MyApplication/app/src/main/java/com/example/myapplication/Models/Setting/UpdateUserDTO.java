package com.example.myapplication.Models.Setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserDTO {
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("nation")
    @Expose
    private String nation;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("selfIntroduce")
    @Expose
    private String selfIntroduce;
    @SerializedName("showNotification")
    @Expose
    private Boolean showNotification;
    @SerializedName("skillLevel")
    @Expose
    private String skillLevel;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce;
    }

    public Boolean getShowNotification() {
        return showNotification;
    }

    public void setShowNotification(Boolean showNotification) {
        this.showNotification = showNotification;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}

package com.example.myapplication.Models.Setting;

import com.example.myapplication.Models.Authorization.SubscriptionPlan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDTO implements Serializable {
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("emailVerified")
    @Expose
    private Boolean emailVerified;
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
    @SerializedName("referCode")
    @Expose
    private String referCode;
    @SerializedName("selfIntroduce")
    @Expose
    private String selfIntroduce;
    @SerializedName("showNotification")
    @Expose
    private Boolean showNotification;
    @SerializedName("skillLevel")
    @Expose
    private String skillLevel;
    @SerializedName("socialSource")
    @Expose
    private String socialSource;
    @SerializedName("subscriptionActiveDate")
    @Expose
    private String subscriptionActiveDate;
    @SerializedName("subscriptionExpiredDate")
    @Expose
    private String subscriptionExpiredDate;
    @SerializedName("subscriptionPlan")
    @Expose
    private SubscriptionPlan subscriptionPlan;
    @SerializedName("username")
    @Expose
    private String username;

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
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

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
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

    public String getSocialSource() {
        return socialSource;
    }

    public void setSocialSource(String socialSource) {
        this.socialSource = socialSource;
    }

    public String getSubscriptionActiveDate() {
        return subscriptionActiveDate;
    }

    public void setSubscriptionActiveDate(String subscriptionActiveDate) {
        this.subscriptionActiveDate = subscriptionActiveDate;
    }

    public String getSubscriptionExpiredDate() {
        return subscriptionExpiredDate;
    }

    public void setSubscriptionExpiredDate(String subscriptionExpiredDate) {
        this.subscriptionExpiredDate = subscriptionExpiredDate;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

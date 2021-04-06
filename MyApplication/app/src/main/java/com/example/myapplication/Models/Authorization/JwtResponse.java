package com.example.myapplication.Models.Authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JwtResponse {
    @SerializedName("canShowTrainingRouteSuggestion")
    @Expose
    private Boolean canShowTrainingRouteSuggestion;
    @SerializedName("canShowTutorialSuggestion")
    @Expose
    private Boolean canShowTutorialSuggestion;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("emailVerified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("firstLogin")
    @Expose
    private Boolean firstLogin;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("referCode")
    @Expose
    private String referCode;
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
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("vipViewCount")
    @Expose
    private Integer vipViewCount;

    public Boolean getCanShowTrainingRouteSuggestion() {
        return canShowTrainingRouteSuggestion;
    }

    public void setCanShowTrainingRouteSuggestion(Boolean canShowTrainingRouteSuggestion) {
        this.canShowTrainingRouteSuggestion = canShowTrainingRouteSuggestion;
    }

    public Boolean getCanShowTutorialSuggestion() {
        return canShowTutorialSuggestion;
    }

    public void setCanShowTutorialSuggestion(Boolean canShowTutorialSuggestion) {
        this.canShowTutorialSuggestion = canShowTutorialSuggestion;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getVipViewCount() {
        return vipViewCount;
    }

    public void setVipViewCount(Integer vipViewCount) {
        this.vipViewCount = vipViewCount;
    }
}

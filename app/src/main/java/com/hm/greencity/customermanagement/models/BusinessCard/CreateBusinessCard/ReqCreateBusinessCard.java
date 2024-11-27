package com.hm.greencity.customermanagement.models.BusinessCard.CreateBusinessCard;
import com.google.gson.annotations.SerializedName;

public class ReqCreateBusinessCard {
    @SerializedName("Fk_UserID")
    public int fk_UserID;
    @SerializedName("Name")
    public String name;
    @SerializedName("BussinessName")
    public String bussinessName;
    @SerializedName("JobTitle")
    public String jobTitle;
    @SerializedName("Description")
    public String description;
    @SerializedName("MobileNo")
    public int mobileNo;
    @SerializedName("EmailId")
    public String emailId;
    @SerializedName("Address")
    public String address;
    @SerializedName("CoverImage")
    public String coverImage;
    @SerializedName("Profile")
    public String profile;
    @SerializedName("Whatsapp")
    public String whatsapp;
    @SerializedName("Instagram")
    public String instagram;
    @SerializedName("Facebook")
    public String facebook;
    @SerializedName("Linkdin")
    public String linkdin;
    @SerializedName("Telegram")
    public String telegram;

    public ReqCreateBusinessCard(int fk_UserID, String name, String bussinessName, String jobTitle, String description, int mobileNo, String emailId, String address, String coverImage, String profile, String whatsapp, String instagram, String facebook, String linkdin, String telegram) {
        this.fk_UserID = fk_UserID;
        this.name = name;
        this.bussinessName = bussinessName;
        this.jobTitle = jobTitle;
        this.description = description;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.coverImage = coverImage;
        this.profile = profile;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.facebook = facebook;
        this.linkdin = linkdin;
        this.telegram = telegram;
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }



}

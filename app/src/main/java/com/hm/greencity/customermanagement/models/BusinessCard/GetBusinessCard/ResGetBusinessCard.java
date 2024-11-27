package com.hm.greencity.customermanagement.models.BusinessCard.GetBusinessCard;
import com.google.gson.annotations.SerializedName;


public class ResGetBusinessCard {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Fk_UserID")
    public String fk_UserID;
    @SerializedName("Pk_BussinessCardId")
    public String pk_BussinessCardId;
    @SerializedName("Name")
    public String name;
    @SerializedName("BussinessName")
    public String bussinessName;
    @SerializedName("JobTitle")
    public String jobTitle;
    @SerializedName("Description")
    public String description;
    @SerializedName("MobileNo")
    public String mobileNo;
    @SerializedName("EmailId")
    public String emailId;
    @SerializedName("Address")
    public String address;
    @SerializedName("Image")
    public String image;
    @SerializedName("BackgroundImage")
    public String backgroundImage;
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
    @SerializedName("Date")
    public String date;

    public ResGetBusinessCard(String status, String message, String fk_UserID, String pk_BussinessCardId, String name, String bussinessName, String jobTitle, String description, String mobileNo, String emailId, String address, String image, String backgroundImage, String whatsapp, String instagram, String facebook, String linkdin, String telegram, String date) {
        this.status = status;
        this.message = message;
        this.fk_UserID = fk_UserID;
        this.pk_BussinessCardId = pk_BussinessCardId;
        this.name = name;
        this.bussinessName = bussinessName;
        this.jobTitle = jobTitle;
        this.description = description;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.image = image;
        this.backgroundImage = backgroundImage;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.facebook = facebook;
        this.linkdin = linkdin;
        this.telegram = telegram;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(String fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public String getPk_BussinessCardId() {
        return pk_BussinessCardId;
    }

    public void setPk_BussinessCardId(String pk_BussinessCardId) {
        this.pk_BussinessCardId = pk_BussinessCardId;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

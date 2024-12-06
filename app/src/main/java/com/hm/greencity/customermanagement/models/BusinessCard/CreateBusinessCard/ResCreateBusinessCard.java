package com.hm.greencity.customermanagement.models.BusinessCard.CreateBusinessCard;
import com.google.gson.annotations.SerializedName;


public class ResCreateBusinessCard {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Fk_UserID")
    public String fk_UserID;
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
    @SerializedName("ProfilePic")
    public String profilePic;
    @SerializedName("BackgroundImage")
    public String backgroundImage;
    @SerializedName("Whatsapp")
    public String whatsapp;
    @SerializedName("Instagram")
    public Object instagram;
    @SerializedName("Facebook")
    public Object facebook;
    @SerializedName("Linkdin")
    public Object linkdin;
    @SerializedName("Telegram")
    public Object telegram;

    public ResCreateBusinessCard(String status, String message, String fk_UserID, String name, String bussinessName, String jobTitle, String description, String mobileNo, String emailId, String address, String profilePic, String backgroundImage, String whatsapp, Object instagram, Object facebook, Object linkdin, Object telegram) {
        this.status = status;
        this.message = message;
        this.fk_UserID = fk_UserID;
        this.name = name;
        this.bussinessName = bussinessName;
        this.jobTitle = jobTitle;
        this.description = description;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.profilePic = profilePic;
        this.backgroundImage = backgroundImage;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.facebook = facebook;
        this.linkdin = linkdin;
        this.telegram = telegram;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public Object getInstagram() {
        return instagram;
    }

    public void setInstagram(Object instagram) {
        this.instagram = instagram;
    }

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public Object getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(Object linkdin) {
        this.linkdin = linkdin;
    }

    public Object getTelegram() {
        return telegram;
    }

    public void setTelegram(Object telegram) {
        this.telegram = telegram;
    }
}

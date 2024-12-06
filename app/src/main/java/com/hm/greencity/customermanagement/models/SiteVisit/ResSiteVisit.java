package com.hm.greencity.customermanagement.models.SiteVisit;
import com.google.gson.annotations.SerializedName;


public class ResSiteVisit {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Fk_UserID")
    public String fk_UserID;
    @SerializedName("CustomerName")
    public String customerName;
    @SerializedName("Date")
    public String date;
    @SerializedName("SiteID")
    public String siteID;
    @SerializedName("MobileNo")
    public String mobileNo;
    @SerializedName("CustomerNo")
    public String customerNo;
    @SerializedName("Fk_TeamId")
    public String fk_TeamId;

    public ResSiteVisit(String status, String message, String fk_UserID, String customerName, String date, String siteID, String mobileNo, String customerNo, String fk_TeamId) {
        this.status = status;
        this.message = message;
        this.fk_UserID = fk_UserID;
        this.customerName = customerName;
        this.date = date;
        this.siteID = siteID;
        this.mobileNo = mobileNo;
        this.customerNo = customerNo;
        this.fk_TeamId = fk_TeamId;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getFk_TeamId() {
        return fk_TeamId;
    }

    public void setFk_TeamId(String fk_TeamId) {
        this.fk_TeamId = fk_TeamId;
    }



}

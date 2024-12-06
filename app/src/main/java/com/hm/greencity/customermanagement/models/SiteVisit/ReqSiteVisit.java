package com.hm.greencity.customermanagement.models.SiteVisit;
import com.google.gson.annotations.SerializedName;

public class ReqSiteVisit {
    @SerializedName("Fk_UserID")
    public int fk_UserID;
    @SerializedName("SiteID")
    public int siteID;
    @SerializedName("CustomerName")
    public String customerName;
    @SerializedName("Date")
    public String date;
    @SerializedName("MobileNo")
    public long mobileNo;
    @SerializedName("CustomerNo")
    public int customerNo;
    @SerializedName("Fk_TeamId")
    public String fk_TeamId;

    public ReqSiteVisit(int fk_UserID, int siteID, String customerName, String date, long mobileNo, int customerNo, String fk_TeamId) {
        this.fk_UserID = fk_UserID;
        this.siteID = siteID;
        this.customerName = customerName;
        this.date = date;
        this.mobileNo = mobileNo;
        this.customerNo = customerNo;
        this.fk_TeamId = fk_TeamId;

    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
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

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public String getFk_TeamId() {
        return fk_TeamId;
    }

    public void setFk_TeamId(String fk_TeamId) {
        this.fk_TeamId = fk_TeamId;
    }
}

package com.hm.greencity.customermanagement.models.GetSite;

import com.google.gson.annotations.SerializedName;

public class LstVisitor {
    @SerializedName("CustomerName")
    public String customerName;
    @SerializedName("Pk_VisitorId")
    public String pk_VisitorId;
    @SerializedName("VisitDate")
    public String visitDate;
    @SerializedName("SiteName")
    public String siteName;
    @SerializedName("MobileNo")
    public String mobileNo;
    @SerializedName("CustomerNo")
    public String customerNo;

    public LstVisitor(String customerName, String pk_VisitorId, String visitDate, String siteName, String mobileNo, String customerNo) {
        this.customerName = customerName;
        this.pk_VisitorId = pk_VisitorId;
        this.visitDate = visitDate;
        this.siteName = siteName;
        this.mobileNo = mobileNo;
        this.customerNo = customerNo;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPk_VisitorId() {
        return pk_VisitorId;
    }

    public void setPk_VisitorId(String pk_VisitorId) {
        this.pk_VisitorId = pk_VisitorId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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


}

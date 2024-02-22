package com.hm.greencity.customermanagement.models.CustomerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerLst {
    @SerializedName("CustomerPK_ID")
    @Expose
    private String customerPKID;
    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("AssociateID")
    @Expose
    private String associateID;
    @SerializedName("AssociateName")
    @Expose
    private String associateName;
    @SerializedName("isBlocked")
    @Expose
    private String isBlocked;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("PanNo")
    @Expose
    private String panNo;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("JoiningDate")
    @Expose
    private String JoiningDate;
    public String getCustomerPKID() {
        return customerPKID;
    }

    public void setCustomerPKID(String customerPKID) {
        this.customerPKID = customerPKID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAssociateID() {
        return associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }
}

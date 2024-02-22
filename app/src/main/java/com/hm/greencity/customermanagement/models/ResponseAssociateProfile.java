package com.hm.greencity.customermanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAssociateProfile {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    @SerializedName("ProfilePic")
    @Expose
    private String ProfilePic;


    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LoginID")
    @Expose
    private Object loginID;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("BankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("IFSC")
    @Expose
    private String ifsc;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PanNumber")
    @Expose
    private String panNumber;
    @SerializedName("AccountHolderName")
    @Expose
    private String accountHolderName;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getLoginID() {
        return loginID;
    }

    public void setLoginID(Object loginID) {
        this.loginID = loginID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
}

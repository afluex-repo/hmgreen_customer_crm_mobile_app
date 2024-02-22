package com.hm.greencity.customermanagement.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerMyProfile {

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
    @SerializedName("CustomberId")
    @Expose
    private String customberId;
    @SerializedName("PanNumber")
    @Expose
    private String panNumber;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Gender")
    @Expose
    private Object gender;
    @SerializedName("JoiningDate")
    @Expose
    private Object joiningDate;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("IFSC")
    @Expose
    private String ifsc;
    @SerializedName("BankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("BankHolderName")
    @Expose
    private String bankHolderName;
    @SerializedName("GaurdianName")
    @Expose
    private Object gaurdianName;
    @SerializedName("GaurdianRelation")
    @Expose
    private Object gaurdianRelation;

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

    public String getCustomberId() {
        return customberId;
    }

    public void setCustomberId(String customberId) {
        this.customberId = customberId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Object joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        this.bankHolderName = bankHolderName;
    }

    public Object getGaurdianName() {
        return gaurdianName;
    }

    public void setGaurdianName(Object gaurdianName) {
        this.gaurdianName = gaurdianName;
    }

    public Object getGaurdianRelation() {
        return gaurdianRelation;
    }

    public void setGaurdianRelation(Object gaurdianRelation) {
        this.gaurdianRelation = gaurdianRelation;
    }

}
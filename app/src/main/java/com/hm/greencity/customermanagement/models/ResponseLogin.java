package com.hm.greencity.customermanagement.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseLogin {
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

    @SerializedName("UserType")
    @Expose
    private String UserType;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Password")
    @Expose
    private String password;

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }


}
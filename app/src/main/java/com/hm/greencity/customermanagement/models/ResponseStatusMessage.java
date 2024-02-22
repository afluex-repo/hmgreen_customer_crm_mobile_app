package com.hm.greencity.customermanagement.models;

import com.google.gson.annotations.SerializedName;

public class ResponseStatusMessage {

    @SerializedName("Message")
    private String message;

    @SerializedName("ProfilePic")
    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @SerializedName("StatusCode")
    private String statusCode;

    @SerializedName("PK_AddressId")
    private String PK_AddressId;

    public String getMessage(){
        return message;
    }
    public String getAddressID(){
        return PK_AddressId;
    }

    public String getStatusCode(){
        return statusCode;
    }
}
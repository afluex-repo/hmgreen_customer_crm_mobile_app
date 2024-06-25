package com.hm.greencity.customermanagement.models.chatModel;
import com.google.gson.annotations.SerializedName;


public class ResponseChat {
    @SerializedName("StatusCode")
    public String statusCode;
    @SerializedName("Message")
    public String message;

    public ResponseChat(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

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
}

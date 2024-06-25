package com.hm.greencity.customermanagement.models.chatModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getResponseChat {

    @SerializedName("StatusCode")
    private String statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("querylst")
    private List<QueryItem> queryList;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public List<QueryItem> getQueryList() {
        return queryList;
    }
}

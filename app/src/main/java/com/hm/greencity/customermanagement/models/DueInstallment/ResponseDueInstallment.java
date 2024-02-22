package com.hm.greencity.customermanagement.models.DueInstallment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDueInstallment {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstduelist")
    @Expose
    private List<Lstdue> lstduelist = new ArrayList<Lstdue>();

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

    public List<Lstdue> getLstduelist() {
        return lstduelist;
    }

    public void setLstduelist(List<Lstdue> lstduelist) {
        this.lstduelist = lstduelist;
    }

}

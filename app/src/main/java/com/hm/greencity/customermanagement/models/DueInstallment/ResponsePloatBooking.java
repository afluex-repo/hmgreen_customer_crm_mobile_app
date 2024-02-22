package com.hm.greencity.customermanagement.models.DueInstallment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hm.greencity.customermanagement.models.PloatBooking.PloatBookingLst;

import java.util.List;

public class ResponsePloatBooking {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("PloatBookingList")
    @Expose
    private List<PloatBookingLst> ploatBookingList = null;

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

    public List<PloatBookingLst> getPloatBookingList() {
        return ploatBookingList;
    }

    public void setPloatBookingList(List<PloatBookingLst> ploatBookingList) {
        this.ploatBookingList = ploatBookingList;
    }

}

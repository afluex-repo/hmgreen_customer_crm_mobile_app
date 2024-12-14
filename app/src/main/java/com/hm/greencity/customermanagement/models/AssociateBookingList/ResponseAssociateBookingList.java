package com.hm.greencity.customermanagement.models.AssociateBookingList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ResponseAssociateBookingList {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstBooking")
    @Expose
    private List<LstBookingAssociate> lstBooking = new ArrayList<LstBookingAssociate>();

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

    public List<LstBookingAssociate> getLstBooking() {
        return lstBooking;
    }

    public void setLstBooking(List<LstBookingAssociate> lstBooking) {
        this.lstBooking = lstBooking;
    }
}

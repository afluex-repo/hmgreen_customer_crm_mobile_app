package com.hm.greencity.customermanagement.models.AssociateDueInstallment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAssociateDueInstallment {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstduelist")
    @Expose
    private List<Lstdue> lstduelist = new ArrayList<Lstdue>();
    @SerializedName("Monthlylst")
    @Expose
    private List<Monthlylst> monthlylst = new ArrayList<Monthlylst>();
    @SerializedName("TotalMonthlyBooking")
    @Expose
    private String totalMonthlyBooking;

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

    public List<Monthlylst> getMonthlylst() {
        return monthlylst;
    }

    public void setMonthlylst(List<Monthlylst> monthlylst) {
        this.monthlylst = monthlylst;
    }

    public String getTotalMonthlyBooking() {
        return totalMonthlyBooking;
    }

    public void setTotalMonthlyBooking(String totalMonthlyBooking) {
        this.totalMonthlyBooking = totalMonthlyBooking;
    }
}

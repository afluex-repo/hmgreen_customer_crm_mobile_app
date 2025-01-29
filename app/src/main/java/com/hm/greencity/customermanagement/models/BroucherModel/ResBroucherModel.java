package com.hm.greencity.customermanagement.models.BroucherModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResBroucherModel {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("BrochureId")
    public Object brochureId;
    @SerializedName("Brochurelst")
    public ArrayList<Brochurelst> brochurelst;

    public ResBroucherModel(String status, String message, Object brochureId, ArrayList<Brochurelst> brochurelst) {
        this.status = status;
        this.message = message;
        this.brochureId = brochureId;
        this.brochurelst = brochurelst;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBrochureId() {
        return brochureId;
    }

    public void setBrochureId(Object brochureId) {
        this.brochureId = brochureId;
    }

    public ArrayList<Brochurelst> getBrochurelst() {
        return brochurelst;
    }

    public void setBrochurelst(ArrayList<Brochurelst> brochurelst) {
        this.brochurelst = brochurelst;
    }
}

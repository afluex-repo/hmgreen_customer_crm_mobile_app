package com.hm.greencity.customermanagement.models.ResponseList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseSite {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstSite")
    @Expose
    private List<LstSite> lstSite = new ArrayList<LstSite>();
    @SerializedName("lstPhase")
    @Expose
    private List<LstPhase> lstPhase = new ArrayList<LstPhase>();
    @SerializedName("lstBlock")
    @Expose
    private List<LstBlock> lstBlock = new ArrayList<LstBlock>();

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

    public List<LstSite> getLstSite() {
        return lstSite;
    }

    public void setLstSite(List<LstSite> lstSite) {
        this.lstSite = lstSite;
    }

    public List<LstPhase> getLstPhase() {
        return lstPhase;
    }

    public void setLstPhase(List<LstPhase> lstPhase) {
        this.lstPhase = lstPhase;
    }

    public List<LstBlock> getLstBlock() {
        return lstBlock;
    }

    public void setLstBlock(List<LstBlock> lstBlock) {
        this.lstBlock = lstBlock;

    }
}
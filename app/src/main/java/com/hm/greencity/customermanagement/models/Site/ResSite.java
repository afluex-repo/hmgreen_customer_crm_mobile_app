package com.hm.greencity.customermanagement.models.Site;

import com.google.gson.annotations.SerializedName;
import com.hm.greencity.customermanagement.models.ResponseList.LstBlock;
import com.hm.greencity.customermanagement.models.ResponseList.LstPhase;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;

import java.util.ArrayList;

public class ResSite {
    @SerializedName("StatusCode")
    public String statusCode;
    @SerializedName("Message")
    public String message;
    public ArrayList<LstSite> lstSite;
    public ArrayList<LstPhase> lstPhase;
    public ArrayList<LstBlock> lstBlock;

    public ResSite(String statusCode, String message, ArrayList<LstSite> lstSite, ArrayList<LstPhase> lstPhase, ArrayList<LstBlock> lstBlock) {
        this.statusCode = statusCode;
        this.message = message;
        this.lstSite = lstSite;
        this.lstPhase = lstPhase;
        this.lstBlock = lstBlock;
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

    public ArrayList<LstSite> getLstSite() {
        return lstSite;
    }

    public void setLstSite(ArrayList<LstSite> lstSite) {
        this.lstSite = lstSite;
    }

    public ArrayList<LstPhase> getLstPhase() {
        return lstPhase;
    }

    public void setLstPhase(ArrayList<LstPhase> lstPhase) {
        this.lstPhase = lstPhase;
    }

    public ArrayList<LstBlock> getLstBlock() {
        return lstBlock;
    }

    public void setLstBlock(ArrayList<LstBlock> lstBlock) {
        this.lstBlock = lstBlock;
    }
}

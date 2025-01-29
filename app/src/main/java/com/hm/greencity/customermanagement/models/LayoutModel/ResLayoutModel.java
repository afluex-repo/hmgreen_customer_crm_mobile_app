package com.hm.greencity.customermanagement.models.LayoutModel;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;



public class ResLayoutModel {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Pk_LayoutId")
    public Object pk_LayoutId;
    public ArrayList<LstSiteLayout> lstSiteLayout;


    public ResLayoutModel(String status, String message, Object pk_LayoutId, ArrayList<LstSiteLayout> lstSiteLayout) {
        this.status = status;
        this.message = message;
        this.pk_LayoutId = pk_LayoutId;
        this.lstSiteLayout = lstSiteLayout;
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

    public Object getPk_LayoutId() {
        return pk_LayoutId;
    }

    public void setPk_LayoutId(Object pk_LayoutId) {
        this.pk_LayoutId = pk_LayoutId;
    }

    public ArrayList<LstSiteLayout> getLstSiteLayout() {
        return lstSiteLayout;
    }

    public void setLstSiteLayout(ArrayList<LstSiteLayout> lstSiteLayout) {
        this.lstSiteLayout = lstSiteLayout;
    }


}

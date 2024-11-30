package com.hm.greencity.customermanagement.models.GetSite;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResGetSite {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Fk_UserID")
    public String fk_UserID;
    @SerializedName("LstVisitor")
    public ArrayList<LstVisitor> lstVisitor;


    public ResGetSite(String status, String message, String fk_UserID, ArrayList<LstVisitor> lstVisitor) {
        this.status = status;
        this.message = message;
        this.fk_UserID = fk_UserID;
        this.lstVisitor = lstVisitor;
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

    public String getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(String fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public ArrayList<LstVisitor> getLstVisitor() {
        return lstVisitor;
    }

    public void setLstVisitor(ArrayList<LstVisitor> lstVisitor) {
        this.lstVisitor = lstVisitor;
    }
}

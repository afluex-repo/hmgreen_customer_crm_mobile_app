package com.hm.greencity.customermanagement.models.GetSite;
import com.google.gson.annotations.SerializedName;


public class ReqGetSite {
    @SerializedName("Fk_UserID")
    public int fk_UserID;

    public ReqGetSite(String fk_UserID) {
        this.fk_UserID = Integer.parseInt(fk_UserID);
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }




}

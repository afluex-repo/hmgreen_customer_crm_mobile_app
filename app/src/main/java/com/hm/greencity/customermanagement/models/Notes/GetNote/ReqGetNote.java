package com.hm.greencity.customermanagement.models.Notes.GetNote;
import com.google.gson.annotations.SerializedName;


public class ReqGetNote {
    @SerializedName("Fk_UserID")
    public int fk_UserID;

    public ReqGetNote(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }


}

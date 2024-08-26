package com.hm.greencity.customermanagement.models.Notes.CreateNote;
import com.google.gson.annotations.SerializedName;


public class ReqCreateNote {
    @SerializedName("Fk_UserID")
    public int fk_UserID;
    @SerializedName("Notes")
    public String notes;

    public ReqCreateNote(int fk_UserID, String notes) {
        this.fk_UserID = fk_UserID;
        this.notes = notes;
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

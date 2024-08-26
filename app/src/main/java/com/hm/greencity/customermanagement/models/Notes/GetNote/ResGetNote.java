package com.hm.greencity.customermanagement.models.Notes.GetNote;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResGetNote {

    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Fk_UserID")
    public String fk_UserID;
    public ArrayList<LstNotepad> lstNotepad;

    public ResGetNote(String status, String message, String fk_UserID, ArrayList<LstNotepad> lstNotepad) {
        this.status = status;
        this.message = message;
        this.fk_UserID = fk_UserID;
        this.lstNotepad = lstNotepad;
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

    public ArrayList<LstNotepad> getLstNotepad() {
        return lstNotepad;
    }

    public void setLstNotepad(ArrayList<LstNotepad> lstNotepad) {
        this.lstNotepad = lstNotepad;
    }
}

package com.hm.greencity.customermanagement.models.Notes.CreateNote;
import com.google.gson.annotations.SerializedName;

public class ResCreateNote {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Notes")
    public String notes;
    @SerializedName("Fk_UserID")
    public String fk_UserID;

    public ResCreateNote(String status, String message, String notes, String fk_UserID) {
        this.status = status;
        this.message = message;
        this.notes = notes;
        this.fk_UserID = fk_UserID;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(String fk_UserID) {
        this.fk_UserID = fk_UserID;
    }
}

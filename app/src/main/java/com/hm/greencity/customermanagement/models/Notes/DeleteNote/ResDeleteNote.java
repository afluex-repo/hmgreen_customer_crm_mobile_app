package com.hm.greencity.customermanagement.models.Notes.DeleteNote;
import com.google.gson.annotations.SerializedName;


public class ResDeleteNote {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Pk_NoteId")
    public String pk_NoteId;
    @SerializedName("Fk_UserID")
    public String fk_UserID;

    public ResDeleteNote(String status, String message, String pk_NoteId, String fk_UserID) {
        this.status = status;
        this.message = message;
        this.pk_NoteId = pk_NoteId;
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

    public String getPk_NoteId() {
        return pk_NoteId;
    }

    public void setPk_NoteId(String pk_NoteId) {
        this.pk_NoteId = pk_NoteId;
    }

    public String getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(String fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

}

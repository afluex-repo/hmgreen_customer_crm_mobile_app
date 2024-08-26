package com.hm.greencity.customermanagement.models.Notes.DeleteNote;
import com.google.gson.annotations.SerializedName;


public class ReqDeleteNote {
    @SerializedName("Fk_UserID")
    public int fk_UserID;
    @SerializedName("Pk_NoteId")
    public int pk_NoteId;

    public ReqDeleteNote(int fk_UserID, int pk_NoteId) {
        this.fk_UserID = fk_UserID;
        this.pk_NoteId = pk_NoteId;
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public int getPk_NoteId() {
        return pk_NoteId;
    }

    public void setPk_NoteId(int pk_NoteId) {
        this.pk_NoteId = pk_NoteId;
    }
}

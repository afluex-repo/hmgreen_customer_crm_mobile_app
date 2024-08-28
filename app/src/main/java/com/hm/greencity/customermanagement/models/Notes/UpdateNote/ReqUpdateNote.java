package com.hm.greencity.customermanagement.models.Notes.UpdateNote;

import com.google.gson.annotations.SerializedName;

public class ReqUpdateNote {
    @SerializedName("Fk_UserID")
    public Object fk_UserID;
    @SerializedName("Pk_NoteId")
    public Object pk_NoteId;
    @SerializedName("Notes")
    public String notes;

    public ReqUpdateNote(Object fk_UserID, Object pk_NoteId, String notes) {
        this.fk_UserID = fk_UserID;
        this.pk_NoteId = pk_NoteId;
        this.notes = notes;

    }

    public Object getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(Object fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public Object getPk_NoteId() {
        return pk_NoteId;
    }

    public void setPk_NoteId(Object pk_NoteId) {
        this.pk_NoteId = pk_NoteId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

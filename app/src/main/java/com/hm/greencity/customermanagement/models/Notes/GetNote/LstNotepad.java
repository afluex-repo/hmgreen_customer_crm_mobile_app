package com.hm.greencity.customermanagement.models.Notes.GetNote;
import com.google.gson.annotations.SerializedName;


public class LstNotepad {
    @SerializedName("Pk_NoteId")
    public String pk_NoteId;
    @SerializedName("Notes")
    public String notes;
    @SerializedName("Date")
    public String date;

    public LstNotepad(String pk_NoteId, String notes, String date) {
        this.pk_NoteId = pk_NoteId;
        this.notes = notes;
        this.date = date;
    }

    public String getPk_NoteId() {
        return pk_NoteId;
    }

    public void setPk_NoteId(String pk_NoteId) {
        this.pk_NoteId = pk_NoteId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

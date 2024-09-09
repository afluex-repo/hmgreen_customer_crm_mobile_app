package com.hm.greencity.customermanagement.models.Gallery;
import com.google.gson.annotations.SerializedName;


public class Lstgallery {
    @SerializedName("Pk_GalleryId")
    public String pk_GalleryId;
    @SerializedName("Documents")
    public String documents;
    @SerializedName("MediaType")
    public String mediaType;
    @SerializedName("Date")
    public String date;


    public Lstgallery(String pk_GalleryId, String documents, String mediaType, String date) {
        this.pk_GalleryId = pk_GalleryId;
        this.documents = documents;
        this.mediaType = mediaType;
        this.date = date;

    }

    public String getPk_GalleryId() {
        return pk_GalleryId;
    }

    public void setPk_GalleryId(String pk_GalleryId) {
        this.pk_GalleryId = pk_GalleryId;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

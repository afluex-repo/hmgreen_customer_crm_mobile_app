package com.hm.greencity.customermanagement.models.Gallery;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;



public class ResGallery {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    public ArrayList<Lstgallery> lstgallery;

    public ResGallery(String status, String message, ArrayList<Lstgallery> lstgallery) {
        this.status = status;
        this.message = message;
        this.lstgallery = lstgallery;
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

    public ArrayList<Lstgallery> getLstgallery() {
        return lstgallery;
    }

    public void setLstgallery(ArrayList<Lstgallery> lstgallery) {
        this.lstgallery = lstgallery;
    }
}

package com.hm.greencity.customermanagement.models.DeleteBusinessCard;
import com.google.gson.annotations.SerializedName;


public class ResDeleteBusinesscard {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("Pk_BussinessCardId")
    public String pk_BussinessCardId;
    @SerializedName("Fk_UserId")
    public String fk_UserId;

    public ResDeleteBusinesscard(String status, String message, String pk_BussinessCardId, String fk_UserId) {
        this.status = status;
        this.message = message;
        this.pk_BussinessCardId = pk_BussinessCardId;
        this.fk_UserId = fk_UserId;
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

    public String getPk_BussinessCardId() {
        return pk_BussinessCardId;
    }

    public void setPk_BussinessCardId(String pk_BussinessCardId) {
        this.pk_BussinessCardId = pk_BussinessCardId;
    }

    public String getFk_UserId() {
        return fk_UserId;
    }

    public void setFk_UserId(String fk_UserId) {
        this.fk_UserId = fk_UserId;
    }
}

package com.hm.greencity.customermanagement.models.DeleteBusinessCard;
import com.google.gson.annotations.SerializedName;


public class ReqDeleteBusinesscard {
    @SerializedName("Fk_UserID")
    public int fk_UserID;
    @SerializedName("Pk_BussinessCardId")
    public int pk_BussinessCardId;

    public ReqDeleteBusinesscard(int fk_UserID, int pk_BussinessCardId) {
        this.fk_UserID = fk_UserID;
        this.pk_BussinessCardId = pk_BussinessCardId;
    }

    public int getFk_UserID() {
        return fk_UserID;
    }

    public void setFk_UserID(int fk_UserID) {
        this.fk_UserID = fk_UserID;
    }

    public int getPk_BussinessCardId() {
        return pk_BussinessCardId;
    }

    public void setPk_BussinessCardId(int pk_BussinessCardId) {
        this.pk_BussinessCardId = pk_BussinessCardId;
    }
}

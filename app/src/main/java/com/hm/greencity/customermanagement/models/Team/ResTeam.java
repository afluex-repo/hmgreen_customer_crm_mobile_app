package com.hm.greencity.customermanagement.models.Team;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResTeam {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    public ArrayList<LstTeam> lstTeam;

    public ResTeam(String status, String message, ArrayList<LstTeam> lstTeam) {
        this.status = status;
        this.message = message;
        this.lstTeam = lstTeam;
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

    public ArrayList<LstTeam> getLstTeam() {
        return lstTeam;
    }

    public void setLstTeam(ArrayList<LstTeam> lstTeam) {
        this.lstTeam = lstTeam;
    }
}

package com.hm.greencity.customermanagement.models.ResponseList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPhase {
    @SerializedName("PhaseID")
    @Expose
    private String phaseID;
    @SerializedName("PhaseName")
    @Expose
    private String phaseName;
    @SerializedName("PhaseSiteID")
    @Expose
    private String phaseSiteID;

    public String getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(String phaseID) {
        this.phaseID = phaseID;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseSiteID() {
        return phaseSiteID;
    }

    public void setPhaseSiteID(String phaseSiteID) {
        this.phaseSiteID = phaseSiteID;
    }

}

package com.hm.greencity.customermanagement.models.AssociatePlotAvalibility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAssociatePlotAvalibility {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstPlotAvailability")
    @Expose
    private List<AssociateLstAvailability> lstPlotAvailability = new ArrayList<AssociateLstAvailability>();
    @SerializedName("TotalPlot")
    @Expose
    private String totalPlot;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssociateLstAvailability> getLstPlotAvailability() {
        return lstPlotAvailability;
    }

    public void setLstPlotAvailability(List<AssociateLstAvailability> lstPlotAvailability) {
        this.lstPlotAvailability = lstPlotAvailability;
    }

    public String getTotalPlot() {
        return totalPlot;
    }

    public void setTotalPlot(String totalPlot) {
        this.totalPlot = totalPlot;
    }

}

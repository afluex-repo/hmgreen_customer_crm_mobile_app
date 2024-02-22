package com.hm.greencity.customermanagement.models.LedgerReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseLedgerReport {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("PloatDatailList")
    @Expose
    private List<PloatDatail> ploatDatailList = new ArrayList<PloatDatail>();
    @SerializedName("PlotLadgerList")
    @Expose
    private List<PlotLadger> plotLadgerList = new ArrayList<PlotLadger>();
    @SerializedName("TotalPaidAmount")
    @Expose
    private String totalPaidAmount;
    @SerializedName("TotalDueAmount")
    @Expose
    private String totalDueAmount;

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

    public List<PloatDatail> getPloatDatailList() {
        return ploatDatailList;
    }

    public void setPloatDatailList(List<PloatDatail> ploatDatailList) {
        this.ploatDatailList = ploatDatailList;
    }

    public List<PlotLadger> getPlotLadgerList() {
        return plotLadgerList;
    }

    public void setPlotLadgerList(List<PlotLadger> plotLadgerList) {
        this.plotLadgerList = plotLadgerList;
    }

    public String getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(String totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(String totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

}

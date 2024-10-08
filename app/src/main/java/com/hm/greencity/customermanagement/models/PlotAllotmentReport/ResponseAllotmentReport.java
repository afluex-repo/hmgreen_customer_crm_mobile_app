package com.hm.greencity.customermanagement.models.PlotAllotmentReport;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResponseAllotmentReport {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("CustomerLoginId")
    public String customerLoginId;
    @SerializedName("FromDate")
    public Object fromDate;
    @SerializedName("ToDate")
    public Object toDate;
    public ArrayList<LstPlotReport> lstPlotReport;

    public ResponseAllotmentReport(String status, String message, String customerLoginId, Object fromDate, Object toDate, ArrayList<LstPlotReport> lstPlotReport) {
        this.status = status;
        this.message = message;
        this.customerLoginId = customerLoginId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.lstPlotReport = lstPlotReport;

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

    public String getCustomerLoginId() {
        return customerLoginId;
    }

    public void setCustomerLoginId(String customerLoginId) {
        this.customerLoginId = customerLoginId;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public ArrayList<LstPlotReport> getLstPlotReport() {
        return lstPlotReport;
    }

    public void setLstPlotReport(ArrayList<LstPlotReport> lstPlotReport) {
        this.lstPlotReport = lstPlotReport;
    }
}

package com.hm.greencity.customermanagement.models.PlotAllotmentReport;
import com.google.gson.annotations.SerializedName;


public class RequestAllotmentReport {
    @SerializedName("CustomerLoginId")
    public String customerLoginId;
    @SerializedName("FromDate")
    public String fromDate;
    @SerializedName("ToDate")
    public String toDate;

    public RequestAllotmentReport(String customerLoginId, String fromDate, String toDate) {
        this.customerLoginId = customerLoginId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getCustomerLoginId() {
        return customerLoginId;
    }

    public void setCustomerLoginId(String customerLoginId) {
        this.customerLoginId = customerLoginId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


}

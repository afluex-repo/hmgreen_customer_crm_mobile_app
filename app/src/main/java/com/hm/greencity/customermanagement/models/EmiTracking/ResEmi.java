package com.hm.greencity.customermanagement.models.EmiTracking;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResEmi {
    @SerializedName("Status")
    public String status;
    @SerializedName("Message")
    public String message;
    @SerializedName("BookingNumber")
    public String bookingNumber;
    @SerializedName("LoginId")
    public String loginId;
    @SerializedName("Name")
    public String name;
    @SerializedName("PlotDetails")
    public String plotDetails;
    @SerializedName("TotalInstAmount")
    public String totalInstAmount;
    @SerializedName("TotalPaidAmount")
    public String totalPaidAmount;
    @SerializedName("TotalLateAmount")
    public String totalLateAmount;
    public ArrayList<LstEMIReport> lstEMIReports;

    public ResEmi(String status, String message, String bookingNumber, String loginId, String name, String plotDetails, String totalInstAmount, String totalPaidAmount, String totalLateAmount, ArrayList<LstEMIReport> lstEMIReports) {
        this.status = status;
        this.message = message;
        this.bookingNumber = bookingNumber;
        this.loginId = loginId;
        this.name = name;
        this.plotDetails = plotDetails;
        this.totalInstAmount = totalInstAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.totalLateAmount = totalLateAmount;
        this.lstEMIReports = lstEMIReports;
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

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlotDetails() {
        return plotDetails;
    }

    public void setPlotDetails(String plotDetails) {
        this.plotDetails = plotDetails;
    }

    public String getTotalInstAmount() {
        return totalInstAmount;
    }

    public void setTotalInstAmount(String totalInstAmount) {
        this.totalInstAmount = totalInstAmount;
    }

    public String getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(String totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getTotalLateAmount() {
        return totalLateAmount;
    }

    public void setTotalLateAmount(String totalLateAmount) {
        this.totalLateAmount = totalLateAmount;
    }

    public ArrayList<LstEMIReport> getLstEMIReports() {
        return lstEMIReports;
    }

    public void setLstEMIReports(ArrayList<LstEMIReport> lstEMIReports) {
        this.lstEMIReports = lstEMIReports;
    }
}

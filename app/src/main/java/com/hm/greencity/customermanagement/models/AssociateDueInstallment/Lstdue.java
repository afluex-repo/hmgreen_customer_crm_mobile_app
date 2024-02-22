package com.hm.greencity.customermanagement.models.AssociateDueInstallment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstdue {

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @SerializedName("InstallmentDate")
    @Expose
    private String dueDate;


    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("CustomerLoginID")
    @Expose
    private String customerLoginID;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("InstallmentNo")
    @Expose
    private String installmentNo;
    @SerializedName("InstallmentAmount")
    @Expose
    private String installmentAmount;
    @SerializedName("PlotDetails")
    @Expose
    private String plotDetails;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerLoginID() {
        return customerLoginID;
    }

    public void setCustomerLoginID(String customerLoginID) {
        this.customerLoginID = customerLoginID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(String installmentNo) {
        this.installmentNo = installmentNo;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getPlotDetails() {
        return plotDetails;
    }

    public void setPlotDetails(String plotDetails) {
        this.plotDetails = plotDetails;
    }


}

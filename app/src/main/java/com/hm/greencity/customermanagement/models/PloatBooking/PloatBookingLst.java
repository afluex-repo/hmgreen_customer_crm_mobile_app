package com.hm.greencity.customermanagement.models.PloatBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PloatBookingLst {
    @SerializedName("TotalPaid")
    @Expose
    private String TotalPaid;

    @SerializedName("RemainingBalance")
    @Expose
    private String RemainingBalance;

    public String getTotalPaid() {
        return TotalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        TotalPaid = totalPaid;
    }

    public String getRemainingBalance() {
        return RemainingBalance;
    }

    public void setRemainingBalance(String remainingBalance) {
        RemainingBalance = remainingBalance;
    }

    public String getpLCCharge() {
        return pLCCharge;
    }

    public void setpLCCharge(String pLCCharge) {
        this.pLCCharge = pLCCharge;
    }

    @SerializedName("BookingStatus")
    @Expose
    private String bookingStatus;
    @SerializedName("BookingId")
    @Expose
    private String bookingId;
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("LoginID")
    @Expose
    private String loginID;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("AssociateID")
    @Expose
    private String associateID;
    @SerializedName("AssociateLoginID")
    @Expose
    private String associateLoginID;
    @SerializedName("AssociateName")
    @Expose
    private String associateName;
    @SerializedName("PlotInformation")
    @Expose
    private String plotInformation;
    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("BookingAmount")
    @Expose
    private String bookingAmount;
    @SerializedName("PaymentPlanDetails")
    @Expose
    private String paymentPlanDetails;
    @SerializedName("BookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("PaidAmount")
    @Expose
    private String paidAmount;
    @SerializedName("PlotArea")
    @Expose
    private String plotArea;
    @SerializedName("PlotAmount")
    @Expose
    private String plotAmount;
    @SerializedName("NetPlotAmount")
    @Expose
    private String netPlotAmount;
    @SerializedName("PLCCharge")
    @Expose
    private String pLCCharge;
    @SerializedName("PlotRate")
    @Expose
    private String plotRate;

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAssociateID() {
        return associateID;
    }

    public void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    public String getAssociateLoginID() {
        return associateLoginID;
    }

    public void setAssociateLoginID(String associateLoginID) {
        this.associateLoginID = associateLoginID;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getPlotInformation() {
        return plotInformation;
    }

    public void setPlotInformation(String plotInformation) {
        this.plotInformation = plotInformation;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(String bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getPaymentPlanDetails() {
        return paymentPlanDetails;
    }

    public void setPaymentPlanDetails(String paymentPlanDetails) {
        this.paymentPlanDetails = paymentPlanDetails;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public String getPlotAmount() {
        return plotAmount;
    }

    public void setPlotAmount(String plotAmount) {
        this.plotAmount = plotAmount;
    }

    public String getNetPlotAmount() {
        return netPlotAmount;
    }

    public void setNetPlotAmount(String netPlotAmount) {
        this.netPlotAmount = netPlotAmount;
    }

    public String getPLCCharge() {
        return pLCCharge;
    }

    public void setPLCCharge(String pLCCharge) {
        this.pLCCharge = pLCCharge;
    }

    public String getPlotRate() {
        return plotRate;
    }

    public void setPlotRate(String plotRate) {
        this.plotRate = plotRate;
    }
}

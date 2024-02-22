package com.hm.greencity.customermanagement.models.LedgerReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PloatDatail {
    @SerializedName("PlotID")
    @Expose
    private String plotID;
    @SerializedName("BookingId")
    @Expose
    private String bookingId;
    @SerializedName("PaymentPlanID")
    @Expose
    private String paymentPlanID;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("PlotRate")
    @Expose
    private String plotRate;
    @SerializedName("PlotArea")
    @Expose
    private String plotArea;
    @SerializedName("ActualPlotAmount")
    @Expose
    private String actualPlotAmount;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("BookingAmount")
    @Expose
    private String bookingAmount;
    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("PaymentPlan")
    @Expose
    private String paymentPlan;
    @SerializedName("TotalPaidAmount")
    @Expose
    private String totalPaidAmount;
    @SerializedName("AllotmentDate")
    @Expose
    private String allotmentDate;
    @SerializedName("TotalInstallment")
    @Expose
    private String noofInstallments;
    @SerializedName("InstallmentAmount")
    @Expose
    private String installmentAmount;
    @SerializedName("Balance")
    @Expose
    private String balance;

    public String getPlotID() {
        return plotID;
    }

    public void setPlotID(String plotID) {
        this.plotID = plotID;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentPlanID() {
        return paymentPlanID;
    }

    public void setPaymentPlanID(String paymentPlanID) {
        this.paymentPlanID = paymentPlanID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlotRate() {
        return plotRate;
    }

    public void setPlotRate(String plotRate) {
        this.plotRate = plotRate;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public String getActualPlotAmount() {
        return actualPlotAmount;
    }

    public void setActualPlotAmount(String actualPlotAmount) {
        this.actualPlotAmount = actualPlotAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(String bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(String totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getAllotmentDate() {
        return allotmentDate;
    }

    public void setAllotmentDate(String allotmentDate) {
        this.allotmentDate = allotmentDate;
    }

    public String getNoofInstallments() {
        return noofInstallments;
    }

    public void setNoofInstallments(String noofInstallments) {
        this.noofInstallments = noofInstallments;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}

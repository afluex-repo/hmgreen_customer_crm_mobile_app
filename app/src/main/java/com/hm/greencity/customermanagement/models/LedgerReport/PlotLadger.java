package com.hm.greencity.customermanagement.models.LedgerReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlotLadger {

    @SerializedName("InstallmentNo")
    @Expose
    private String installmentNo;
    @SerializedName("InstallmentDate")
    @Expose
    private String installmentDate;
    @SerializedName("InstallmentAmount")
    @Expose
    private String installmentAmount;
    @SerializedName("PaidAmount")
    @Expose
    private String paidAmount;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("BookingDetailsId")
    @Expose
    private String bookingDetailsId;
    @SerializedName("BookingId")
    @Expose
    private String bookingId;
    @SerializedName("DueAmount")
    @Expose
    private String dueAmount;

    public String getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(String installmentNo) {
        this.installmentNo = installmentNo;
    }

    public String getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(String installmentDate) {
        this.installmentDate = installmentDate;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBookingDetailsId() {
        return bookingDetailsId;
    }

    public void setBookingDetailsId(String bookingDetailsId) {
        this.bookingDetailsId = bookingDetailsId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

}

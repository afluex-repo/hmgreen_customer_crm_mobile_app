package com.hm.greencity.customermanagement.models.EmiTracking;
import com.google.gson.annotations.SerializedName;


public class LstEMIReport {
    @SerializedName("PaidAmount")
    public String paidAmount;
    @SerializedName("InstAmt")
    public String instAmt;
    @SerializedName("PaymentStatus")
    public String paymentStatus;
    @SerializedName("InstallmentNo")
    public String installmentNo;
    @SerializedName("BookingDate")
    public String bookingDate;
    @SerializedName("PaymentDate")
    public String paymentDate;
    @SerializedName("Companay")
    public String companay;
    @SerializedName("AccountHeadId")
    public String accountHeadId;
    @SerializedName("PaymentMode")
    public String paymentMode;
    @SerializedName("Status")
    public String status;
    @SerializedName("LateCharge")
    public int lateCharge;
    @SerializedName("TransactionNumber")
    public String transactionNumber;
    @SerializedName("TransactionDate")
    public String transactionDate;
    @SerializedName("ReceiverBank")
    public String receiverBank;

    public LstEMIReport(String paidAmount, String instAmt, String paymentStatus, String installmentNo, String bookingDate, String paymentDate, String companay, String accountHeadId, String paymentMode, String status, int lateCharge, String transactionNumber, String transactionDate, String receiverBank) {
        this.paidAmount = paidAmount;
        this.instAmt = instAmt;
        this.paymentStatus = paymentStatus;
        this.installmentNo = installmentNo;
        this.bookingDate = bookingDate;
        this.paymentDate = paymentDate;
        this.companay = companay;
        this.accountHeadId = accountHeadId;
        this.paymentMode = paymentMode;
        this.status = status;
        this.lateCharge = lateCharge;
        this.transactionNumber = transactionNumber;
        this.transactionDate = transactionDate;
        this.receiverBank = receiverBank;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getInstAmt() {
        return instAmt;
    }

    public void setInstAmt(String instAmt) {
        this.instAmt = instAmt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(String installmentNo) {
        this.installmentNo = installmentNo;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCompanay() {
        return companay;
    }

    public void setCompanay(String companay) {
        this.companay = companay;
    }

    public String getAccountHeadId() {
        return accountHeadId;
    }

    public void setAccountHeadId(String accountHeadId) {
        this.accountHeadId = accountHeadId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLateCharge() {
        return lateCharge;
    }

    public void setLateCharge(int lateCharge) {
        this.lateCharge = lateCharge;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }
}

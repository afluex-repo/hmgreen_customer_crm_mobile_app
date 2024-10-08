package com.hm.greencity.customermanagement.models.PlotAllotmentReport;
import com.google.gson.annotations.SerializedName;

public class LstPlotReport {
    @SerializedName("PK_BookingDetailsId")
    public String pK_BookingDetailsId;
    @SerializedName("CustomerLoginId")
    public String customerLoginId;
    @SerializedName("CustomerName")
    public String customerName;
    @SerializedName("AssociateID")
    public String associateID;
    @SerializedName("PaymentMode")
    public String paymentMode;
    @SerializedName("PaidAmount")
    public String paidAmount;
    @SerializedName("PaymentDate")
    public String paymentDate;
    @SerializedName("PlotNumber")
    public String plotNumber;
    @SerializedName("BookingNumber")
    public String bookingNumber;
    @SerializedName("AssociateName")
    public String associateName;

    public LstPlotReport(String pK_BookingDetailsId, String customerLoginId, String customerName, String associateID, String paymentMode, String paidAmount, String paymentDate, String plotNumber, String bookingNumber, String associateName) {
        this.pK_BookingDetailsId = pK_BookingDetailsId;
        this.customerLoginId = customerLoginId;
        this.customerName = customerName;
        this.associateID = associateID;
        this.paymentMode = paymentMode;
        this.paidAmount = paidAmount;
        this.paymentDate = paymentDate;
        this.plotNumber = plotNumber;
        this.bookingNumber = bookingNumber;
        this.associateName = associateName;
    }

    public String getpK_BookingDetailsId() {
        return pK_BookingDetailsId;
    }

    public void setpK_BookingDetailsId(String pK_BookingDetailsId) {
        this.pK_BookingDetailsId = pK_BookingDetailsId;
    }

    public String getCustomerLoginId() {
        return customerLoginId;
    }

    public void setCustomerLoginId(String customerLoginId) {
        this.customerLoginId = customerLoginId;
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

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }
}

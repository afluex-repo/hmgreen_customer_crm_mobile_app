package com.hm.greencity.customermanagement.models;

public class ReceiptModel {

    private String CustomerID;
    private String CustomerName;
    private String BusinessPartnerID;
    private String AllotmentDate;
    private String amount;
    private String BookingNumber;
    private String Plot;
    private String Mode;

    public ReceiptModel(String customerID, String customerName, String businessPartnerID, String allotmentDate, String amount, String bookingNumber, String plot, String mode) {
        CustomerID = customerID;  // No need to parse
        CustomerName = customerName;
        BusinessPartnerID = businessPartnerID;
        AllotmentDate = allotmentDate;
        this.amount = amount;
        BookingNumber = bookingNumber;
        Plot = plot;
        Mode = mode;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {  // Change parameter type to String
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getBusinessPartnerID() {
        return BusinessPartnerID;
    }

    public void setBusinessPartnerID(String businessPartnerID) {
        BusinessPartnerID = businessPartnerID;
    }

    public String getAllotmentDate() {
        return AllotmentDate;
    }

    public void setAllotmentDate(String allotmentDate) {
        AllotmentDate = allotmentDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBookingNumber() {
        return BookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        BookingNumber = bookingNumber;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }
}

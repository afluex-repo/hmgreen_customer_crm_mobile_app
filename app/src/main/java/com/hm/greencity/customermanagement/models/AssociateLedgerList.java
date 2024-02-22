package com.hm.greencity.customermanagement.models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateLedgerList {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("PlotID")
    @Expose
    private String plotID;
    @SerializedName("PlotAmount")
    @Expose
    private String plotAmount;
    @SerializedName("ActualPlotRate")
    @Expose
    private String actualPlotRate;
    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("Bookingamount")
    @Expose
    private String bookingamount;
    @SerializedName("PlotRate")
    @Expose
    private String plotRate;
    @SerializedName("TotalAllotmentAmount")
    @Expose
    private Object totalAllotmentAmount;
    @SerializedName("PaidAmount")
    @Expose
    private String paidAmount;
    @SerializedName("PaidAllotmentAmount")
    @Expose
    private String paidAllotmentAmount;
    @SerializedName("BalanceAllotmentAmount")
    @Expose
    private String balanceAllotmentAmount;
    @SerializedName("TotalInstallment")
    @Expose
    private String totalInstallment;
    @SerializedName("InstallmentAmount")
    @Expose
    private String installmentAmount;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("Discount")
    @Expose
    private Object discount;
    @SerializedName("PlotArea")
    @Expose
    private String plotArea;
    @SerializedName("lstLedger")
    @Expose
    private List<LstLedger> lstLedger = null;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlotID() {
        return plotID;
    }

    public void setPlotID(String plotID) {
        this.plotID = plotID;
    }

    public String getPlotAmount() {
        return plotAmount;
    }

    public void setPlotAmount(String plotAmount) {
        this.plotAmount = plotAmount;
    }

    public String getActualPlotRate() {
        return actualPlotRate;
    }

    public void setActualPlotRate(String actualPlotRate) {
        this.actualPlotRate = actualPlotRate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingamount() {
        return bookingamount;
    }

    public void setBookingamount(String bookingamount) {
        this.bookingamount = bookingamount;
    }

    public String getPlotRate() {
        return plotRate;
    }

    public void setPlotRate(String plotRate) {
        this.plotRate = plotRate;
    }

    public Object getTotalAllotmentAmount() {
        return totalAllotmentAmount;
    }

    public void setTotalAllotmentAmount(Object totalAllotmentAmount) {
        this.totalAllotmentAmount = totalAllotmentAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidAllotmentAmount() {
        return paidAllotmentAmount;
    }

    public void setPaidAllotmentAmount(String paidAllotmentAmount) {
        this.paidAllotmentAmount = paidAllotmentAmount;
    }

    public String getBalanceAllotmentAmount() {
        return balanceAllotmentAmount;
    }

    public void setBalanceAllotmentAmount(String balanceAllotmentAmount) {
        this.balanceAllotmentAmount = balanceAllotmentAmount;
    }

    public String getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(String totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public List<LstLedger> getLstLedger() {
        return lstLedger;
    }

    public void setLstLedger(List<LstLedger> lstLedger) {
        this.lstLedger = lstLedger;
    }



    public class LstLedger {

        @SerializedName("BookingDetailsId")
        @Expose
        private String bookingDetailsId;
        @SerializedName("BookingId")
        @Expose
        private String bookingId;
        @SerializedName("InstallmentNo")
        @Expose
        private String installmentNo;
        @SerializedName("InstallmentDate")
        @Expose
        private String installmentDate;
        @SerializedName("PaymentDate")
        @Expose
        private String paymentDate;
        @SerializedName("PaidAmount")
        @Expose
        private String paidAmount;
        @SerializedName("InstAmt")
        @Expose
        private String instAmt;
        @SerializedName("DueAmount")
        @Expose
        private String dueAmount;
        @SerializedName("PaymentModeName")
        @Expose
        private String paymentModeName;

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

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
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

        public String getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(String dueAmount) {
            this.dueAmount = dueAmount;
        }

        public String getPaymentModeName() {
            return paymentModeName;
        }

        public void setPaymentModeName(String paymentModeName) {
            this.paymentModeName = paymentModeName;
        }

    }
}

package com.hm.greencity.customermanagement.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DueInstallmentDashBoard {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstduelist")
    @Expose
    private List<Lstdue> lstduelist = null;

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

    public List<Lstdue> getLstduelist() {
        return lstduelist;
    }

    public void setLstduelist(List<Lstdue> lstduelist) {
        this.lstduelist = lstduelist;
    }



    public class Lstdue {

        @SerializedName("CustomerID")
        @Expose
        private String customerID;


        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        @SerializedName("InstallmentDate")
        @Expose
        private String dueDate;



        @SerializedName("CustomerLoginID")
        @Expose
        private String customerLoginID;
        @SerializedName("CustomerName")
        @Expose
        private String customerName;
        @SerializedName("PlotDetails")
        @Expose
        private String plotDetails;
        @SerializedName("InstallmentNo")
        @Expose
        private String installmentNo;
        @SerializedName("InstallmentAmount")
        @Expose
        private String installmentAmount;

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

        public String getPlotDetails() {
            return plotDetails;
        }

        public void setPlotDetails(String plotDetails) {
            this.plotDetails = plotDetails;
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

    }
}
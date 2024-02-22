package com.hm.greencity.customermanagement.models.AssociateBusinessReport;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAssociateBusinessReport {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("BussinessLst")
    @Expose
    private List<BussinessLst> bussinessLst = null;
    @SerializedName("TotalNetAmount")
    @Expose
    private String totalNetAmount;

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

    public List<BussinessLst> getBussinessLst() {
        return bussinessLst;
    }

    public void setBussinessLst(List<BussinessLst> bussinessLst) {
        this.bussinessLst = bussinessLst;
    }

    public String getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(String totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }








    public class BussinessLst {

        @SerializedName("LoginId")
        @Expose
        private Object loginId;
        @SerializedName("AssociateID")
        @Expose
        private String associateID;
        @SerializedName("CustomerLoginID")
        @Expose
        private String customerLoginID;
        @SerializedName("CustomerId")
        @Expose
        private String customerId;
        @SerializedName("Customername")
        @Expose
        private String customername;
        @SerializedName("AssociateName")
        @Expose
        private String associateName;
        @SerializedName("DisplayName")
        @Expose
        private String displayName;
        @SerializedName("Leg")
        @Expose
        private String leg;
        @SerializedName("PaidAmount")
        @Expose
        private String paidAmount;
        @SerializedName("ClosingDate")
        @Expose
        private String closingDate;
        @SerializedName("NetAmount")
        @Expose
        private String netAmount;
        @SerializedName("SiteName")
        @Expose
        private String siteName;
        @SerializedName("SectorName")
        @Expose
        private String sectorName;
        @SerializedName("BlockName")
        @Expose
        private String blockName;
        @SerializedName("PlotNumber")
        @Expose
        private String plotNumber;
        @SerializedName("PaymentDate")
        @Expose
        private String paymentDate;

        public Object getLoginId() {
            return loginId;
        }

        public void setLoginId(Object loginId) {
            this.loginId = loginId;
        }

        public String getAssociateID() {
            return associateID;
        }

        public void setAssociateID(String associateID) {
            this.associateID = associateID;
        }

        public String getCustomerLoginID() {
            return customerLoginID;
        }

        public void setCustomerLoginID(String customerLoginID) {
            this.customerLoginID = customerLoginID;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getAssociateName() {
            return associateName;
        }

        public void setAssociateName(String associateName) {
            this.associateName = associateName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getLeg() {
            return leg;
        }

        public void setLeg(String leg) {
            this.leg = leg;
        }

        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getClosingDate() {
            return closingDate;
        }

        public void setClosingDate(String closingDate) {
            this.closingDate = closingDate;
        }

        public String getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(String netAmount) {
            this.netAmount = netAmount;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getSectorName() {
            return sectorName;
        }

        public void setSectorName(String sectorName) {
            this.sectorName = sectorName;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public String getPlotNumber() {
            return plotNumber;
        }

        public void setPlotNumber(String plotNumber) {
            this.plotNumber = plotNumber;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }

    }


}
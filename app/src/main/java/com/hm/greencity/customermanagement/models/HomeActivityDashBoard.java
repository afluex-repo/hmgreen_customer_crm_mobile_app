package com.hm.greencity.customermanagement.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeActivityDashBoard {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("dashBoardData")
    @Expose
    private List<DashBoardDatum> dashBoardData = null;

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

    public List<DashBoardDatum> getDashBoardData() {
        return dashBoardData;
    }

    public void setDashBoardData(List<DashBoardDatum> dashBoardData) {
        this.dashBoardData = dashBoardData;
    }



    public class DashBoardDatum {

        @SerializedName("TotalPlotAmount")
        @Expose
        private String totalPlotAmount;
        @SerializedName("TotalPaidAmount")
        @Expose
        private String totalPaidAmount;
        @SerializedName("TotalPending")
        @Expose
        private String totalPending;
        @SerializedName("TotalBooking")
        @Expose
        private String totalBooking;

        @SerializedName("AssociateDetails")
        @Expose
        private String associateDetails;

        public String getAssociateDetails() {
            return associateDetails;
        }

        public void setAssociateDetails(String associateDetails) {
            this.associateDetails = associateDetails;
        }



        public String getTotalPlotAmount() {
            return totalPlotAmount;
        }

        public void setTotalPlotAmount(String totalPlotAmount) {
            this.totalPlotAmount = totalPlotAmount;
        }

        public String getTotalPaidAmount() {
            return totalPaidAmount;
        }

        public void setTotalPaidAmount(String totalPaidAmount) {
            this.totalPaidAmount = totalPaidAmount;
        }

        public String getTotalPending() {
            return totalPending;
        }

        public void setTotalPending(String totalPending) {
            this.totalPending = totalPending;
        }

        public String getTotalBooking() {
            return totalBooking;
        }

        public void setTotalBooking(String totalBooking) {
            this.totalBooking = totalBooking;
        }

    }
}
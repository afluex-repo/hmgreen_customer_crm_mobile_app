package com.hm.greencity.customermanagement.models.AssociateDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAssociateDashboard {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("TotalDownline")
    @Expose
    private String totalDownline;
    @SerializedName("TotalDirects")
    @Expose
    private String totalDirects;
    @SerializedName("PayoutWalletBalance")
    @Expose
    private String payoutWalletBalance;
    @SerializedName("TotalPayout")
    @Expose
    private String totalPayout;
    @SerializedName("TotalDeduction")
    @Expose
    private String totalDeduction;
    @SerializedName("TotalActive")
    @Expose
    private String totalActive;
    @SerializedName("TotalInActive")
    @Expose
    private String totalInActive;
    @SerializedName("TotalPaidPayout")
    @Expose
    private String totalPaidPayout;
    @SerializedName("PaidBusinessLeft")
    @Expose
    private String paidBusinessLeft;
    @SerializedName("PaidBusinessRight")
    @Expose
    private String paidBusinessRight;
    @SerializedName("TotalBusinessLeft")
    @Expose
    private String totalBusinessLeft;
    @SerializedName("TotalBusinessRight")
    @Expose
    private String totalBusinessRight;
    @SerializedName("BussinessBalanceLeft")
    @Expose
    private String bussinessBalanceLeft;
    @SerializedName("BussinessBalanceRight")
    @Expose
    private String bussinessBalanceRight;

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

    public String getTotalDownline() {
        return totalDownline;
    }

    public void setTotalDownline(String totalDownline) {
        this.totalDownline = totalDownline;
    }

    public String getTotalDirects() {
        return totalDirects;
    }

    public void setTotalDirects(String totalDirects) {
        this.totalDirects = totalDirects;
    }

    public String getPayoutWalletBalance() {
        return payoutWalletBalance;
    }

    public void setPayoutWalletBalance(String payoutWalletBalance) {
        this.payoutWalletBalance = payoutWalletBalance;
    }

    public String getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(String totalPayout) {
        this.totalPayout = totalPayout;
    }

    public String getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(String totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public String getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(String totalActive) {
        this.totalActive = totalActive;
    }

    public String getTotalInActive() {
        return totalInActive;
    }

    public void setTotalInActive(String totalInActive) {
        this.totalInActive = totalInActive;
    }

    public String getTotalPaidPayout() {
        return totalPaidPayout;
    }

    public void setTotalPaidPayout(String totalPaidPayout) {
        this.totalPaidPayout = totalPaidPayout;
    }

    public String getPaidBusinessLeft() {
        return paidBusinessLeft;
    }

    public void setPaidBusinessLeft(String paidBusinessLeft) {
        this.paidBusinessLeft = paidBusinessLeft;
    }

    public String getPaidBusinessRight() {
        return paidBusinessRight;
    }

    public void setPaidBusinessRight(String paidBusinessRight) {
        this.paidBusinessRight = paidBusinessRight;
    }

    public String getTotalBusinessLeft() {
        return totalBusinessLeft;
    }

    public void setTotalBusinessLeft(String totalBusinessLeft) {
        this.totalBusinessLeft = totalBusinessLeft;
    }

    public String getTotalBusinessRight() {
        return totalBusinessRight;
    }

    public void setTotalBusinessRight(String totalBusinessRight) {
        this.totalBusinessRight = totalBusinessRight;
    }

    public String getBussinessBalanceLeft() {
        return bussinessBalanceLeft;
    }

    public void setBussinessBalanceLeft(String bussinessBalanceLeft) {
        this.bussinessBalanceLeft = bussinessBalanceLeft;
    }

    public String getBussinessBalanceRight() {
        return bussinessBalanceRight;
    }

    public void setBussinessBalanceRight(String bussinessBalanceRight) {
        this.bussinessBalanceRight = bussinessBalanceRight;
    }

}

package com.hm.greencity.customermanagement.models.AssociateDueInstallment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Monthlylst {
    @SerializedName("TotalBooking")
    @Expose
    private String totalBooking;
    @SerializedName("Month")
    @Expose
    private String month;

    public String getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(String totalBooking) {
        this.totalBooking = totalBooking;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

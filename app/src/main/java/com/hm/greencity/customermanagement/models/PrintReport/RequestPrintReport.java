package com.hm.greencity.customermanagement.models.PrintReport;
import com.google.gson.annotations.SerializedName;


public class RequestPrintReport {
    @SerializedName("PK_BookingDetailsId")
    public int pK_BookingDetailsId;

    public RequestPrintReport(int pK_BookingDetailsId) {
        this.pK_BookingDetailsId = pK_BookingDetailsId;
    }

    public int getpK_BookingDetailsId() {
        return pK_BookingDetailsId;
    }

    public void setpK_BookingDetailsId(int pK_BookingDetailsId) {
        this.pK_BookingDetailsId = pK_BookingDetailsId;
    }
}

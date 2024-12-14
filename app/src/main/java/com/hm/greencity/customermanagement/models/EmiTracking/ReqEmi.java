package com.hm.greencity.customermanagement.models.EmiTracking;
import com.google.gson.annotations.SerializedName;


public class ReqEmi {
    @SerializedName("BookingNumber")
    public String bookingNumber;

    public ReqEmi(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
}

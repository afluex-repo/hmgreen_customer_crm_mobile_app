package com.hm.greencity.customermanagement.models.CustomerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseCustomerList {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("CustomerLst")
    @Expose
    private List<CustomerLst> customerLst = new ArrayList<CustomerLst>();

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

    public List<CustomerLst> getCustomerLst() {
        return customerLst;
    }

    public void setCustomerLst(List<CustomerLst> customerLst) {
        this.customerLst = customerLst;
    }

}

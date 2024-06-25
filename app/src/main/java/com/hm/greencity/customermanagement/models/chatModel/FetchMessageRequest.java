package com.hm.greencity.customermanagement.models.chatModel;
import com.google.gson.annotations.SerializedName;

public class FetchMessageRequest {
    @SerializedName("Fk_EmployeeId")
    private String employeeId;

    public FetchMessageRequest(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}

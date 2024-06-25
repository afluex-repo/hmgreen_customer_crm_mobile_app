package com.hm.greencity.customermanagement.models.chatModel;
import com.google.gson.annotations.SerializedName;


public class getChatRequest {
    public class Root{
        @SerializedName("Fk_EmployeeId")
        public int fk_EmployeeId;

        public Root(int fk_EmployeeId) {
            this.fk_EmployeeId = fk_EmployeeId;
        }
    }

}

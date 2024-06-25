package com.hm.greencity.customermanagement.models.chatModel;
import com.google.gson.annotations.SerializedName;

public class RequestChat {

    @SerializedName("Fk_UserId")
    public int fk_UserId;
    @SerializedName("Title")
    public String title;
    @SerializedName("Description")
    public String description;
    @SerializedName("QueryImage")
    public String queryImage;

    public RequestChat(int fk_UserId, String title, String description, String queryImage) {
        this.fk_UserId = fk_UserId;
        this.title = title;
        this.description = description;
        this.queryImage = queryImage;
    }

    public int getFk_UserId() {
        return fk_UserId;
    }

    public void setFk_UserId(int fk_UserId) {
        this.fk_UserId = fk_UserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueryImage() {
        return queryImage;
    }

    public void setQueryImage(String queryImage) {
        this.queryImage = queryImage;
    }
}

package com.hm.greencity.customermanagement.models.chatModel;

import com.google.gson.annotations.SerializedName;

public class QueryItem {
    @SerializedName("Title")
    private String title;

    @SerializedName("Description")
    private String description;

    @SerializedName("Images")
    private String images;

    @SerializedName("AddedDate")
    private String addedDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }

    public String getAddedDate() {
        return addedDate;
    }
}

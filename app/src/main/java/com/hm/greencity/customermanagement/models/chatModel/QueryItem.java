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

    @SerializedName("ReplyMessage")
    public String replyMessage;
    @SerializedName("ReplyDate")
    public String replyDate;

    @SerializedName("ReplyImage")
    public String replyImage;

    public QueryItem(String title, String description, String images, String addedDate, String replyMessage, String replyDate, String replyImage) {
        this.title = title;
        this.description = description;
        this.images = images;
        this.addedDate = addedDate;
        this.replyMessage = replyMessage;
        this.replyDate = replyDate;
        this.replyImage = replyImage;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyImage() {
        return replyImage;
    }

    public void setReplyImage(String replyImage) {
        this.replyImage = replyImage;
    }

}

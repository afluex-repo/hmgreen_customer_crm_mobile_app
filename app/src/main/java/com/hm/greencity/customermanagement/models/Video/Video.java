package com.hm.greencity.customermanagement.models.Video;

public class Video {
    private String title;
    private String url;
    private String thumbnailUrl;

    public Video(String title, String url, String thumbnailUrl) {
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
